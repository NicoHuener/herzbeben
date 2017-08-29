package de.meetme;

import de.meetme.db.PersonDao;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeetMeApplication extends Application<MeetMeConfiguration> {
    private static final Logger log = LoggerFactory.getLogger(MeetMeApplication.class);

    // Add here new data classes in order to register them at hibernate bundle
    private static final Class<?>[] entities = {de.meetme.data.Person.class};

    /**
     * Create
     */
    private final HibernateBundle<MeetMeConfiguration> hibernate = new HibernateBundle<MeetMeConfiguration>(de.meetme.data.Person.class,entities) {
        @Override
        public DataSourceFactory getDataSourceFactory(MeetMeConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "meetme";
    }

    @Override
    public void initialize(Bootstrap<MeetMeConfiguration> bootstrap) {
        log.debug("initialize");

        // register Dropwizard Hibernate bundle
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(MeetMeConfiguration configuration, Environment environment) {
        log.debug("run");

        // add here new data classes, DAOs and services
        final PersonDao dao = new PersonDao(hibernate.getSessionFactory());
        de.meetme.api.PersonService personService = new de.meetme.api.PersonService(dao, hibernate.getSessionFactory());
        environment.jersey().register(personService);

        // start h2 in server mode to connect remotely
        startDbServer(configuration.getDbPort());

        // create initial data in database
        initDb(configuration, environment);
    }

    private void initDb(MeetMeConfiguration configuration, Environment environment) {
        try {
            Connection con = hibernate.getDataSourceFactory(configuration).build(environment.metrics(), "DataSource").getConnection();
            PreparedStatement stmt = con.prepareStatement("insert into person (id, firstname, name, email) values (1, 'pvn1', 'pn1', 'email1')");
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Start H2 db as server.
     * You can connect remotly using this URL:
     * jdbc:h2:tcp://localhost:9092/~/meetmedb
     * User: sa
     * Pwd: <KEEP EMPTY>
     *
     * WARNING: Server is NOT secured. Don't use in production!!!!!
     */
    private void startDbServer(String dbPort) {
        try {
            Server.createTcpServer("-tcpPort", dbPort, "-tcpAllowOthers").start();
            log.warn("WARNING: H2 Server started. Only for testing allowed! Don't start on production system!!!!!");
        } catch (SQLException e) {
            log.error("Could not start db server: " + e);
        }
    }

    private static void stopDbServer(String dbPort) {
        try {
            Server.shutdownTcpServer("tcp://localhost:" + dbPort, "", true, true);
        } catch (SQLException e) {
            log.error("Could not shutdown db server: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        new MeetMeApplication().run(args);
    }

}

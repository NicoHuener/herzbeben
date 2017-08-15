package de.photowalk;

import de.photowalk.data.Person;
import de.photowalk.db.PersonDao;
import de.photowalk.health.TemplateHealthCheck;
import de.photowalk.api.PersonService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.h2.tools.Server;

import java.sql.SQLException;

public class PhotoWalkApplication extends Application<PhotoWalkConfiguration> {
    private static final String DB_PORT = "9092";
    private static final Logger log = LoggerFactory.getLogger(PhotoWalkApplication.class);

    private final HibernateBundle<PhotoWalkConfiguration> hibernate = new HibernateBundle<PhotoWalkConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(PhotoWalkConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "photowalk";
    }

    @Override
    public void initialize(Bootstrap<PhotoWalkConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        startDbServer();
    }

    @Override
    public void run(PhotoWalkConfiguration configuration, Environment environment) {
        final PersonDao dao = new PersonDao(hibernate.getSessionFactory());
        environment.jersey().register(new PersonService(dao));

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
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
    private void startDbServer() {
        try {
            Server.createTcpServer("-tcpPort", DB_PORT, "-tcpAllowOthers").start();
            log.warn("WARNING: H2 Server started. Only for testing allowed! Don't start on production system!!!!!");
        } catch (SQLException e) {
            log.error("Could not start db server: " + e);
        }
    }

    private static void stopDbServer() {
        try {
            Server.shutdownTcpServer("tcp://localhost:" + DB_PORT, "", true, true);
        } catch (SQLException e) {
            log.error("Could not shutdown db server: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        new PhotoWalkApplication().run(args);
    }

}

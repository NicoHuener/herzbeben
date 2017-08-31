import de.meetme.data.Person
import de.meetme.db.PersonDao
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry
import spock.lang.Specification

class PersonDaoTest extends Specification {

    SessionFactory sessionFactory
    PersonDao personDao

    void getSessionFactory() {
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.url", "jdbc:h2:mem:testDb");
        config.setProperty("hibernate.connection.username", "sa");
        config.setProperty("hibernate.connection.password", "");
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.current_session_context_class", "thread");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.hbm2ddl.auto", "create");

        config.addAnnotatedClass(Person)

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        sessionFactory = config.buildSessionFactory(serviceRegistry);
    }


    def setup() {
        getSessionFactory()
        personDao = new PersonDao(sessionFactory)
    }

    def cleanup() {
        sessionFactory.close()
    }

    def testCreate() {
        setup:
        def session = sessionFactory.getCurrentSession()

        when:
        def transaction = session.beginTransaction()
        personDao.persist(new Person())
        def result = personDao.getAll()
        transaction.commit()

        then:
        result

        cleanup:
        session.close()
    }

    def testAll() {
        setup:
        def session = sessionFactory.getCurrentSession()
        def transaction = session.beginTransaction()
        personDao.persist(new Person(0, "fn1", "n1", "e1"))
        personDao.persist(new Person(0, "fn2", "n2", "e2"))
        personDao.persist(new Person(0, "fn3", "n3", "e3"))
        transaction.commit()
        session.close()

        session = sessionFactory.getCurrentSession()

        when:
        transaction = session.beginTransaction()
        def result = personDao.getAll()
        transaction.commit()

        then:
        result
        result.size() == 3

        cleanup:
        session.close()
    }

    def testByName() {
        setup:
        def session = sessionFactory.getCurrentSession()
        def transaction = session.beginTransaction()
        personDao.persist(new Person(0, "fn1", "n1", "e1"))
        personDao.persist(new Person(0, "fn2", "n2", "e2"))
        personDao.persist(new Person(0, "fn3", "n3", "e3"))
        transaction.commit()
        session.close()

        session = sessionFactory.getCurrentSession()

        when:
        transaction = session.beginTransaction()
        def result = personDao.byName("n2")
        transaction.commit()

        then:
        result
        result.size() == 1
        result[0].id == 2
        result[0].name == "n2"

        cleanup:
        session.close()
    }


    def testGet() {
        setup:
        def session = sessionFactory.getCurrentSession()
        def transaction = session.beginTransaction()
        Person created = personDao.persist(new Person(0,"fn1", "name1", "email1"))

        when:
        def result = personDao.get(created.getId())
        transaction.commit()

        then:
        result.name == "name1"
        result.id == 1

        cleanup:
        session.close()
    }

    def testDelete() {
        setup:
        def session = sessionFactory.getCurrentSession()
        def transaction = session.beginTransaction()
        Person created = personDao.persist(new Person(0,"fn1", "name1", "email1"))

        when:
        personDao.remove(created)
        def result = personDao.get(created.getId())
        transaction.commit()

        then:
        ! result

        cleanup:
        session.close()
    }
}
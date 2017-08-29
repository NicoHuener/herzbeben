package de.meetme.api;


import com.codahale.metrics.annotation.Timed;
import de.meetme.data.Person;
import de.meetme.db.PersonDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonService {
    private final PersonDao dao;
    private final SessionFactory sessionFactory;

    public PersonService(PersonDao dao, SessionFactory sessionFactory) {
        this.dao = dao;
        this.sessionFactory = sessionFactory;
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Person getPerson(@PathParam("id") long id) {
        dao.persist(new Person(1, "vorname", "name", "email"));
        return dao.get(id);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @UnitOfWork
    public void removePerson(@PathParam("id") long id) {
        Person person = dao.get(id);
        dao.remove(person);
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Person> getPersons() {
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        Transaction transaction = session.beginTransaction();
        List<Person> persons = dao.getAll();
        transaction.commit();
        session.close();
        return persons;
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    @UnitOfWork
    public Person addPerson(Person person) {
        return dao.persist(person);
    }
}

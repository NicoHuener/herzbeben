package de.meetme.api;


import de.meetme.data.Person;
import de.meetme.db.PersonDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person") // Part of the URL to identify this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonService {
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonDao dao;

    public PersonService(PersonDao dao) {
        this.dao = dao;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public Person getPerson(@PathParam("id") long id) {
        return dao.get(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN) // We return plain text, no JSON
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public void removePerson(@PathParam("id") long id) {
        Person person = dao.get(id);
        dao.remove(person);
    }

    @GET
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Person> getPersons() {
        return  dao.getAll();
    }

    @POST
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public Person createPerson(Person person) throws Exception {
        log.debug("Create Person: " + person);
        return dao.persist(person);
    }

    @PUT
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public void updatePerson(Person person) throws Exception {
        log.debug("Update Person: " + person);
        dao.update(person);
    }
}

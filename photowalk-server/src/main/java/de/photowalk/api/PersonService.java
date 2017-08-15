package de.photowalk.api;


import com.codahale.metrics.annotation.Timed;
import de.photowalk.data.Person;
import de.photowalk.db.PersonDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonService {
    private final PersonDao dao;

    public PersonService(PersonDao dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") long id) {
        return dao.get(id);
    }

    @GET
    @Timed
    @Path("/remove/{person}")
    @Produces(MediaType.TEXT_PLAIN)
    public void removePerson(@PathParam("person") Person person) {
        dao.remove(person);
    }

    @GET
    @Timed
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPersons() {
        return dao.findAll();
    }

    @POST
    @Timed
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    public Person addPerson(Person person) {
        return dao.persist(person);
    }
}

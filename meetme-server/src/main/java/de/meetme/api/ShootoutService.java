package de.meetme.api;


import de.meetme.data.Person;
import de.meetme.data.Shootout;
import de.meetme.db.PersonDao;
import de.meetme.db.ShootoutDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shootout") // Part of the URL to identify this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShootoutService {
    private static final Logger log = LoggerFactory.getLogger(ShootoutService.class);

    private final ShootoutDao shootoutDao;
    private PersonDao personDao;

    public ShootoutService(ShootoutDao dao, PersonDao personDao) {
        this.shootoutDao = dao;
        this.personDao = personDao;
    }

  /*  @POST
    //@Path("/{shootoutname}&{person}")
    @Path("/create")
    @UnitOfWork //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    //public Shootout createShootout(@PathParam("shootoutname") String shootoutName, @PathParam("person") Person person) throws Exception {
        public Shootout createShootout(Shootout shootout) throws Exception {
        log.debug("Create Shootout from: " + shootout);
        //Shootout shootout = new Shootout(shootoutName,person);
        return shootoutDao.persist(shootout);
    }*/

    @POST
    @Path("/{shootoutname}&{userid}")
    @UnitOfWork //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    //public Shootout createShootout(@PathParam("shootoutname") String shootoutName, @PathParam("person") Person person) throws Exception {
    public Shootout createShootout(@PathParam("shootoutname") String shootoutName, @PathParam("userid") long userId) throws Exception {
        log.debug("Create Shootout from: " + userId);
        Person person = personDao.get(userId);
        Shootout shootout = new Shootout(shootoutName,person);
        return shootoutDao.persist(shootout);
    }

  /*  @GET
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Shootout> getShootoutByUserid(Person person) throws Exception {
        log.debug("Get Shootout from Person: " + person.getEmail());

        return shootoutDao.getShootoutByPerson(person);
    }*/

    @GET
    @Path("/{id}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Shootout> getShootoutByUserid(@PathParam("id") long id) throws Exception {
        log.debug("Get Shootout from Person: " + id);

        return shootoutDao.getShootoutByPersonid(id);
    }

}

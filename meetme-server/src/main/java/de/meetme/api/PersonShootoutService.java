package de.meetme.api;

import de.meetme.data.Person;
import de.meetme.data.PersonShootout;
import de.meetme.data.Photo;
import de.meetme.data.Shootout;
import de.meetme.db.PersonDao;
import de.meetme.db.PersonShootoutDao;
import de.meetme.db.ShootoutDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.Timestamp;
import java.util.List;

@Path("/personshootout") // Part of the URL to identify this resource
public class PersonShootoutService {

    private static final Logger log = LoggerFactory.getLogger(PersonShootoutService.class);
    private final PersonShootoutDao dao;
    private PersonDao personDao;
    private ShootoutDao shootoutDao;

    public PersonShootoutService(PersonShootoutDao dao,ShootoutDao shootoutDao) {
        this.dao = dao;
        this.shootoutDao = shootoutDao;
    }

    /*@POST
    @Path("/{shootoutId}&{userId}&{timestamp}&{category}")
    @UnitOfWork
    public PersonShootout createPersonShootout(@PathParam("shootoutId")long shootoutId, @PathParam("userId")long userId, @PathParam("timestamp") String timestamp, @PathParam("category")String category){
     Shootout shootout = shootoutDao.get(shootoutId);
     Person person = personDao.get(userId);
        PersonShootout personshootout = new PersonShootout(person,shootout,timestamp,category);
        return personshootout;
    }*/

    @POST
    @Path("/{shootoutId}&{userId}&{category}&{date}")
    @UnitOfWork
    public PersonShootout createPersonShootout(@PathParam("shootoutId")long shootoutId, @PathParam("userId")long userId, @PathParam("category")String category, @PathParam("date")String date){
        Shootout shootout = shootoutDao.get(shootoutId);
        Person person = personDao.get(userId);
        PersonShootout personshootout = new PersonShootout(person,shootout,category,date);
        return personshootout;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/participants/{shootout}")
    @UnitOfWork
    public List<Person> getParticipantsByShootout(@PathParam("shootout") long shootoutId) throws Exception {
        log.debug("Get Participants from Shootout: " + shootoutId);
        Shootout shootout = shootoutDao.get(shootoutId);

        return dao.getPersonsFromShootout(shootout);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    @UnitOfWork
    public List<PersonShootout> getTimestampsForShootouts(@PathParam("userId") long userId) throws Exception {
        log.debug("Get Timestamps from Shootouts: " + userId);

        return dao.getTimeFromShootout(userId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bestShootouts")
    @UnitOfWork
    public List<PersonShootout> getBestShootoutsAll() throws Exception {

        return dao.getBestShootoutsAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bestShootouts/{userId}")
    @UnitOfWork
    public List<PersonShootout> getBestShootoutsByPerson(@PathParam("userId") long userId) throws Exception {
        log.debug("Get best Shootouts from person: " + userId);


        return dao.getBestShootoutsByPerson(userId);
    }

    @GET
    @Path("/date")
    @UnitOfWork
    public List<Shootout> getShootoutsByDate(String date) throws Exception {
        log.debug("Get Shootouts by Date: " + date);

        return dao.getShootoutsByDate(date);
    }

}

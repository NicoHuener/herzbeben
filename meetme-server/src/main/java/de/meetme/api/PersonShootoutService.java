package de.meetme.api;

import de.meetme.data.Bean.PersonShootoutCategoriesBean;
import de.meetme.data.Bean.PersonShootoutShootoutBean;
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

    public PersonShootoutService(PersonShootoutDao dao,ShootoutDao shootoutDao, PersonDao persondao) {
        this.dao = dao;
        this.shootoutDao = shootoutDao;
        this.personDao = persondao;
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
    @Path("/{userId}&{shootoutId}&{date}&{category}")
    @UnitOfWork
    public PersonShootout createPersonShootout(@PathParam("userId")long userId,@PathParam("shootoutId")long shootoutId,@PathParam("date")String date, @PathParam("category")String category){
        Shootout shootout = shootoutDao.get(shootoutId);
        Person person = personDao.get(userId);
        PersonShootout personshootout = new PersonShootout(person,shootout,date,category);
        return dao.persist(personshootout);
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
    @Path("/bestShootouts")
    @UnitOfWork
    public List<PersonShootoutShootoutBean> getBestShootoutsAll() throws Exception {

        return dao.countparticipantsbyshootout();
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bestShootouts/{userId}")
    @UnitOfWork
    public List<PersonShootout> getBestShootoutsByPerson(@PathParam("userId") long userId) throws Exception {
        log.debug("Get best Shootouts from person: " + userId);


        return dao.getBestShootoutsByPerson(userId);
    }*/

    @GET
    @Path("/date")
    @UnitOfWork
    public List<Shootout> getShootoutsByDate(String date) throws Exception {
        log.debug("Get Shootouts by Date: " + date);

        return dao.getShootoutsByDate(date);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/countCategories")
    @UnitOfWork
    public List<PersonShootout> countCategoriesAll() throws Exception {

        return dao.countCategoriesAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/countCategories/{userId}")
    @UnitOfWork
    public List<PersonShootout> countCategoriesAll(@PathParam("userId") long userId) throws Exception {
        log.debug("Count categories by user: " + userId);
        return dao.countCategoriesByUser(userId);
    }


    //wie viele shootouts hat ein user am gegebenen Datum erstellt?
    @GET
    @Path("/countShootoutsByDateAndPerson/{userId}&{date}")
    @UnitOfWork
    public int countShootoutsByDateAndPerson(@PathParam("userId") long userId, @PathParam("date") String date) throws Exception {
        log.debug("Count shootouts by date by user: " + userId);
        return dao.countShootoutsByDateAndPerson(userId, date);
    }

    @GET
    @Path("/countShootoutsByDate/{date}")
    @UnitOfWork
    public int countShootoutsByDate(@PathParam("date") String date) throws Exception {
        log.debug("Count shootouts by date by user: " + date);
        return dao.countShootoutsByDate(date);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/personCategoryCount/{personId}")
    @UnitOfWork
    public List<PersonShootoutCategoriesBean> personCategoryCount(@PathParam("personId") long personId) throws Exception {
        log.debug("Count shootouts by date by user: " + personId);
        return dao.personCategoryCount(personId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categoryCount")
    @UnitOfWork
    public List<PersonShootoutCategoriesBean> categoryCount() throws Exception {
        log.debug("Count shootouts all user:");
        return dao.categoryCount();
    }


}

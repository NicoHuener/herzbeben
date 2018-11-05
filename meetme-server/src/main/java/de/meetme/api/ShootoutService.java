package de.meetme.api;


import de.meetme.data.Person;
import de.meetme.data.Photo;
import de.meetme.data.Rank;
import de.meetme.data.Shootout;
import de.meetme.db.PersonDao;
import de.meetme.db.PhotoDao;
import de.meetme.db.RankDao;
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
    private RankDao rankDao;
    private PhotoDao photoDao;

    public ShootoutService(ShootoutDao shootoutDao, PersonDao personDao, RankDao rankDao,PhotoDao photoDao) {
        this.shootoutDao = shootoutDao;
        this.personDao = personDao;
        this.rankDao = rankDao;
        this.photoDao = photoDao;
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
    @Path("/{shootoutname}&{userid}&{category}")
    @UnitOfWork //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    //public Shootout createShootout(@PathParam("shootoutname") String shootoutName, @PathParam("person") Person person) throws Exception {
    public Shootout createShootout(@PathParam("shootoutname") String shootoutName, @PathParam("userid") long userId,@PathParam("category") String category) throws Exception {
        log.debug("Create Shootout from: " + userId);
        Person person = personDao.get(userId);
        Shootout shootout = new Shootout(shootoutName,person,category);
        createRanksForShootout(shootout,person);
        return shootoutDao.persist(shootout);
    }

    private void createRanksForShootout(Shootout shootout,Person person){
        List<Photo> photoList = photoDao.getPhotoFromPerson(person.getId());
        for(Photo photo:photoList){
            rankDao.persist(new Rank(shootout,0,photo));
        }
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Shootout> getShootoutByUserid(@PathParam("id") long id) throws Exception {
        log.debug("Get Shootout from Person: " + id);

        return shootoutDao.getShootoutByPersonid(id);
    }

    @GET
    @Path("shootoutid/{id}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Shootout> getShootoutByShootoutid(@PathParam("id") long id) throws Exception {
        log.debug("Get Shootout by ID: " + id);

        return shootoutDao.getShootoutByShootoutid(id);
    }

    @GET
    @Path("/categoryFromShootout/{id}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public String getCategoryFromShootout(@PathParam("id") long id) throws Exception {
        log.debug("Get Shootout from Person: " + id);
        Shootout sh = shootoutDao.get(id);
        String shootout = sh.getCategory();
        return shootout;
    }

    @GET
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Shootout> getShootouts() {
        return  shootoutDao.getAll();
    }


    //wie viele Shootouts hat eine Person jemals erstellt
    @GET
    @Path("countShooouts/{userId}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Shootout> countShootoutsByUser(@PathParam("userId") long userId) throws Exception {
        log.debug("Count Shootouts from Person: " + userId);

        return shootoutDao.countShootoutsByUser(userId);
    }

}

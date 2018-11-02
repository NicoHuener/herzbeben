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

   /* @POST
    @Path("/{shootout}&{persons}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public Shootout addParticipantsToShootout(@PathParam("shootout") Shootout shootout, @PathParam("persons") List<Person> persons) throws Exception {
        log.debug("Create Teilnehmer am Shootout : " + shootout.getName());

        for(Person person:persons){
            dao.persist(new PersonShootout(person,shootout));
        }
        return shootout;
    }*/

    /*------------------------------------------------------------------------------------------------
    Noch zu bearbeiten: shootout über shootoutid suchen, nicht über shootoutname
    @POST
    @Path("/{shootoutname}&{userid}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public Shootout addParticipantToShootout(@PathParam("shootoutname") String shootoutname, @PathParam("userid") List<Integer> userids) throws Exception {
        log.debug("Create Teilnehmer am Shootout : " + shootoutname);

        for(int i = 0; i< userids.size(); i++){
            int userid = userids.get(i);
            Person person = personDao.get(userid);
            Shootout shootout = shootoutDao.getShootoutbyName(shootoutname);
            dao.persist(new PersonShootout(person,shootout));
        }
        return shootoutDao.persist(shootout);
    }
    --------------------------------------------------------------------------------------------------*/




    @GET
    @Path("/{shootout}")
    @UnitOfWork
    public List<Person> getParticipantsByShootout(@PathParam("shootout") long shootoutId) throws Exception {
        log.debug("Get Participants from Shootout: " + shootoutId);
        Shootout shootout = shootoutDao.get(shootoutId);

        return dao.getPersonsFromShootout(shootout);
    }

    @GET
    @Path("/timestamp")
    @UnitOfWork
    public List<Shootout> getShootoutsByDate(String timestamp) throws Exception {
        log.debug("Get Shootouts by Date: " + timestamp);
        Shootout shootout = shootoutDao.get(timestamp);

        return dao.getShootoutsByDate(timestamp);
    }

}

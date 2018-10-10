package de.meetme.api;

import de.meetme.data.Person;
import de.meetme.data.PersonShootout;
import de.meetme.data.Photo;
import de.meetme.data.Shootout;
import de.meetme.db.PersonShootoutDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/personshootout") // Part of the URL to identify this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonShootoutService {

    private static final Logger log = LoggerFactory.getLogger(PersonShootoutService.class);
    private final PersonShootoutDao dao;

    public PersonShootoutService(PersonShootoutDao dao) {
        this.dao = dao;
    }

    @POST
    @Path("/{shootout}&{persons}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public Shootout addParticipantsToShootout(@PathParam("shootout") Shootout shootout, @PathParam("persons") List<Person> persons) throws Exception {
        log.debug("Create Teilnehmer am Shootout : " + shootout.getName());

        for(Person person:persons){
            dao.persist(new PersonShootout(person,shootout));
        }
        return shootout;
    }

    @GET
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Person> getParticipantsByShootout(Shootout shootout) throws Exception {
        log.debug("Get Participants from Shootout: " + shootout.getName());

        return dao.getPersonsFromShootout(shootout);
    }

}

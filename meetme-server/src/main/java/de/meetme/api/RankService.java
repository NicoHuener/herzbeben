package de.meetme.api;

import de.meetme.data.Person;
import de.meetme.data.Photo;
import de.meetme.data.Rank;
import de.meetme.data.Shootout;
import de.meetme.db.PhotoDao;
import de.meetme.db.RankDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rank") // Part of the URL to identify this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RankService {

    private static final Logger log = LoggerFactory.getLogger(de.meetme.api.RankService.class);
    private final RankDao dao;

    public RankService(RankDao dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Rank> getPhotosByUserid(Shootout shootout) throws Exception {
        log.debug("Get Rank from Shootout: " + shootout.getName());

        return dao.getRankFromPerson(shootout);
    }


}

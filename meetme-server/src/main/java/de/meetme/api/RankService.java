package de.meetme.api;

import de.meetme.data.Rank;
import de.meetme.db.RankDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
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

    @PUT
    @Path("/points/{points}&{rankid}")
    @UnitOfWork
    public void updatepoints(@PathParam("points")int points,@PathParam("rankid")long rankId) {
        log.debug("Update points: " + rankId);
        dao.updatepoints(points, rankId);
    }

   @GET
   @Path("/shootout/{shootoutId}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Rank> getRanksbyShootoutId(@PathParam("shootoutId")long shootoutId) throws Exception {
        log.debug("Get Rank from Shootout: " + shootoutId);
       return dao.getRankFromShootout(shootoutId);
    }


}

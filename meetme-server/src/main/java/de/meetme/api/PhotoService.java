package de.meetme.api;

import de.meetme.data.Person;
import de.meetme.data.Photo;
import de.meetme.db.PhotoDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/photo") // Part of the URL to identify this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
    public class PhotoService {

    private static final Logger log = LoggerFactory.getLogger(de.meetme.api.PhotoService.class);
    private final PhotoDao dao;

    public PhotoService(PhotoDao dao) {
        this.dao = dao;
    }


    @GET
    @Path("/{id}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Photo> getPhotosByUserid(@PathParam("id") long id) throws Exception {
        log.debug("Get Photos from Person: " + id);

        return dao.getPhotoFromPerson(id);
    }

    @GET
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Photo> getPhotos() {
        return  dao.getAll();
    }
}
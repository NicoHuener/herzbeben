package de.meetme.api;

import de.meetme.data.Person;
import de.meetme.data.Photo;
import de.meetme.db.PersonDao;
import de.meetme.db.PhotoDao;
import io.dropwizard.hibernate.UnitOfWork;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.io.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/photo") // Part of the URL to identify this resource
    public class PhotoService {

    private static final Logger log = LoggerFactory.getLogger(de.meetme.api.PhotoService.class);
    private final PhotoDao dao;
    private final PersonDao persondao;
    public PhotoService(PhotoDao dao, PersonDao persondao) {
        this.dao = dao;
        this.persondao = persondao;
    }

    @PUT
    @Path("/wins/{photoid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public void updatewins( @PathParam("photoid")long photoid) throws Exception{
        log.debug("Update Wins: " + photoid);
        dao.updatewins(photoid);
    }


    @PUT
    @Path("/clicks/{clicks}&{photoid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public void updateclicks(@PathParam("clicks")int clicks,@PathParam("photoid")long photoid){
        log.debug("Update clicks: " + photoid);
        dao.updateclicks(clicks,photoid);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public void updatePhoto(Photo photo) throws Exception {
        log.debug("Update Photo: " + photo);
        dao.update(photo);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Photo> getPhotosByUserid(@PathParam("id") long id) throws Exception {
        log.debug("Get Photos from Person: " + id);

        return dao.getPhotoFromPerson(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/category")
    @UnitOfWork
    //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Photo> getPhotosbycategory() throws Exception {

        return dao.getPhotosbycategory();
    }

    //Liefert Liste sortiert nach clicks
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bestpictures")
    @UnitOfWork
    public List<Photo> getbestpictures(){
        return dao.getclickssorted();
    }

    //Liefert Liste soritert nach wins
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bestphotographers")
    @UnitOfWork
    public List<Photo> getbestphotographers(){
        return dao.getwinssorted();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public List<Photo> getPhotos() {
        return  dao.getAll();
    }

    @POST
    @Path("/upload/picdata")
    @UnitOfWork  //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    public Photo createPhoto(Photo photo) throws Exception {
        log.debug("Create Photo: " + photo);
        return dao.persist(photo);
    }

    @POST
    @Path("/upload/picdata/{userid}/{title}/{clicks}/{wins}/{picture}/{category}")
    @UnitOfWork //  be transaction aware (This tag automatically creates a database transaction with begin/commit or rollback in case of an error
    //public Shootout createShootout(@PathParam("shootoutname") String shootoutName, @PathParam("person") Person person) throws Exception {
    public Photo createPhoto( @PathParam("userid") long userId,@PathParam("title") String title,@PathParam("clicks") int clicks,@PathParam("wins") int wins,@PathParam("picture") String picture,@PathParam("category") String category) throws Exception {
        log.debug("Create Photo from: " + userId);
        Person person = persondao.get(userId);
        Photo photo = new Photo(person,title,clicks,wins,picture,category);
        return dao.persist(photo);
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response uploadfile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

       OutputStream out = null;
       String fileName = fileDetail.getFileName();
       String filePath = "./src/main/uploads/" + fileName;


       try {
           //OutputStream out = null;
           File fileToUpload = new File(filePath);
           out = new FileOutputStream(fileToUpload);
           int read;
           byte[] bytes = new byte[2048];

           while ((read = uploadedInputStream.read(bytes)) != -1) {
               out.write(bytes, 0, read);
           }
       } catch (IOException e) {
           e.printStackTrace();
           return Response.status(500).build();
       } /*finally {
           try {
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
               return Response.status(500).build();
           }
       }*/
       return Response.status(200).build();
   }
}


package de.meetme.db;

import de.meetme.data.Person;
import de.meetme.data.Photo;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PhotoDao extends AbstractDao<Photo> {

    public PhotoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    /*public List<de.meetme.data.Photo> getPhotoById(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Photo>getResultList();
    }*/

    public List<Photo> getPhotoFromPerson(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where person_id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Photo>getResultList();
    }

    public List<Photo> getPhotosbycategory() {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() +  " order by category";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        return q.<de.meetme.data.Photo>getResultList();
    }
    public List<Photo> getPhotosfromcat(String category) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() +  " where category = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        q.setParameter( 1, category );
        return q.<de.meetme.data.Photo>getResultList();
    }

    public List<Photo> getpointsinsgesammt() {
        String sqlQuery = "select photo_id,sum(points) from " + getEntityClass().getSimpleName() +  " group by photo_id";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        return q.<de.meetme.data.Photo>getResultList();
    }

    public List<Photo> getpointsinsg(long photoId) {
        String sqlQuery = "select photo_id,sum(points) from " + getEntityClass().getSimpleName() +  " where photo_id = ? group by photo_id";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        q.setParameter( 1, photoId );
        return q.<de.meetme.data.Photo>getResultList();
    }


    /*public List<Photo> getPhotosByCategory(String category) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where category = " + category;
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        return q.<de.meetme.data.Photo>getResultList();
    }*/

    public List<Photo> getclickssorted() {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " order by clicks";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        return q.<de.meetme.data.Photo>getResultList();
    }

    public List<Photo> getwinssorted(){
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " order by wins";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        return q.<de.meetme.data.Photo>getResultList();
    }

    /* public List<Photo>  updatewins(long id){
            String sql= "select wins from" +getEntityClass().getSimpleName() +"where photo_id = ?";
            Query a = currentSession().createNativeQuery(sql, de.meetme.data.Photo.class);
            a.setParameter( 1, id );
            int newwins = Integer.parseInt(sql);
            newwins = newwins +1;
            String sqlQuery = "update table" + getEntityClass().getSimpleName() + "set wins =" + newwins + "where photo_id = ?";
            Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
            q.setParameter( 1, id );
            return q.<de.meetme.data.Photo>getResultList();
        }
        */
   public void updatewins(long id){
       //List<Photo> ph= getPhotoById(id);
       Photo pic = get(id);
       int newwins = pic.getWins()+1;

       String sqlQuery = "update " + getEntityClass().getSimpleName() + " set wins = " + newwins + " where id = ?";

       Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
       q.setParameter( 1, id );
       q.executeUpdate();
   }

    public void updateclicks(int clicks,long id){
        Photo pic = get(id);
        int newclicks = pic.getClicks()+clicks;

        String sqlQuery = "update " + getEntityClass().getSimpleName() + " set clicks = " + newclicks + " where id = ?";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        q.setParameter( 1, id );
        q.executeUpdate();
    }

    //public void createpicindb()
}

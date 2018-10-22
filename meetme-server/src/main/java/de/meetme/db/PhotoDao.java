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

}

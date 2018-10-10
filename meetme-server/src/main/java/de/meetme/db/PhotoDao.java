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

    public List<Photo> getPhotoFromPerson(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where person_id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Photo.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Photo>getResultList();
    }


}

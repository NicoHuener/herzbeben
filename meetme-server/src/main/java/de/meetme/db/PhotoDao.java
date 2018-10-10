package de.meetme.db;

import de.meetme.data.Photo;
import org.hibernate.SessionFactory;

public class PhotoDao extends AbstractDao<Photo> {

    public PhotoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}

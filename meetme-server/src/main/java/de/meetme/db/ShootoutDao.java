package de.meetme.db;

import de.meetme.data.Person;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ShootoutDao extends AbstractDao<Shootout> {

    public ShootoutDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Shootout> getShootoutByPerson(Person person) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where person_id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        q.setParameter( 1, person.getId() );
        return q.<de.meetme.data.Shootout>getResultList();
    }
}

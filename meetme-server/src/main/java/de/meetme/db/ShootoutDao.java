package de.meetme.db;

import de.meetme.data.Person;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
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

    public List<Shootout> getShootoutByPersonid(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where person_id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Shootout>getResultList();
    }

    public List<Shootout> getShootoutByShootoutid(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Shootout>getResultList();
    }

    public List<de.meetme.data.Shootout> getShootoutbyName(String name) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where name = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        q.setParameter( 1, name );
        return q.<de.meetme.data.Shootout>getResultList();
    }


    //wie viele shootouts hat ein user jemals erstellt
    public List<Shootout> countShootoutsByUser(long id) {
        String sqlQuery = "select person_id, count(*) as count from " + getEntityClass().getSimpleName() + " where person_id = ? \n" +
                "GROUP BY person_id";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Shootout>getResultList();
    }
}

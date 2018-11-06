package de.meetme.db;


import de.meetme.data.Person;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;



public class PersonDao extends AbstractDao<Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Person> byName(String name) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where username = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, Person.class);
        q.setParameter( 1, name );
        return q.<Person>getResultList();
    }

    public void updateTreffer(long personId,int treffer,int keinTreffer){
        String sqlQuery = "Update " + getEntityClass().getSimpleName() +
                " set treffer = ? , keintreffer = ?" +
                " where id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, Person.class);
        q.setParameter( 1, treffer );
        q.setParameter( 2, keinTreffer);
        q.setParameter( 3, personId);
        q.executeUpdate();
    }
}
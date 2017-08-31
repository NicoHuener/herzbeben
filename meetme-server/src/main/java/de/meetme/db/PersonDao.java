package de.meetme.db;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;



public class PersonDao extends AbstractDao<de.meetme.data.Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<de.meetme.data.Person> byName(String name) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where name = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Person.class);
        q.setParameter( 1, name );
        return q.<de.meetme.data.Person>getResultList();
    }

}
package de.photowalk.db;


import de.photowalk.data.Person;
import org.hibernate.SessionFactory;

import java.util.List;


public class PersonDao extends AbstractDaoWrapper<Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Person> findAll() {
        return list(namedQuery("select p from person p"));
    }

}
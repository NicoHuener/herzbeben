package de.meetme.db;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@SqlResultSetMapping(name="PersonMapping",
        entities={
                @EntityResult(entityClass = de.meetme.data.Person.class, fields = {
                        @FieldResult(name="id", column="id"),
                        @FieldResult(name="name", column="name"),
                        @FieldResult(name="vorName", column="vorname")
                })
        },
        columns={
                @ColumnResult(name="durationInSec")
        }
)

public class PersonDao extends AbstractDao<de.meetme.data.Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<de.meetme.data.Person> byName(String name) {
        String sqlQuery = "select * from person where name = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Person.class);
        q.setParameter( 1, name );
        return q.<de.meetme.data.Person>getResultList();
    }

    public List<de.meetme.data.Person> getAll() {
        String sqlQuery = "select * from person";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Person.class);
        return q.<de.meetme.data.Person>getResultList();
    }
}
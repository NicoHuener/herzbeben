package de.meetme.db;

import de.meetme.data.Person;
import de.meetme.data.PersonShootout;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PersonShootoutDao extends AbstractDao<PersonShootout> {

    public PersonShootoutDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Person> getPersonsFromShootout(Shootout shootout) {
        String sqlQuery = "SELECT * FROM Person p\n" +
                "inner join PERSONSHOOTOUT ps on ps.person_id = p.ID\n" +
                "inner join shootout s on s.id = ps.shootout_id\n" +
                "where s.id = ?";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Person.class);
        q.setParameter( 1, shootout.getId() );
        return q.<de.meetme.data.Person>getResultList();
    }

}

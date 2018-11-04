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

    public List<PersonShootout> getTimeFromShootout(long personId) {
        String sqlQuery = "SELECT timestamp,shootout_id FROM Person p\n" +
                "inner join PERSONSHOOTOUT ps on ps.person_id = p.ID\n" +
                "inner join shootout s on s.id = ps.shootout_id\n" +
                "where p.id = ?";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.PersonShootout.class);
        q.setParameter( 1, personId );
        return q.<de.meetme.data.Person>getResultList();
    }


        // Gibt alle Shootouts aus, die am selben Tag von Usern durchlaufen wurden. Z.B. für Datenanalyse "Welches ist das beliebteste shootout am Tag x gewesen"
    public List<Shootout> getShootoutsByDate(String timestamp) {
        String sqlQuery = "SELECT * FROM Shootout s\n" +
                "inner join PERSONSHOOTOUT ps on ps.shootout_id = s.ID\n" +
                "where ps.timestamp = " + timestamp;

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        return q.<de.meetme.data.Person>getResultList();
    }



}

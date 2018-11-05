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
    public List<Shootout> getShootoutsByDate(String date) {
        String sqlQuery = "SELECT * FROM Shootout s\n" +
                "inner join PERSONSHOOTOUT ps on ps.shootout_id = s.ID\n" +
                "where ps.date = " + date;

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Shootout.class);
        return q.<de.meetme.data.Person>getResultList();
    }

    public List<PersonShootout> getBestShootoutsAll() {
        String sqlQuery = "SELECT shootout_id, category, count(*) as count FROM PersonShootout\n" +
                "group by shootout_id";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.PersonShootout.class);
        return q.<de.meetme.data.Person>getResultList();
    }

    public List<PersonShootout> getBestShootoutsByPerson(long personId) {
        String sqlQuery = "SELECT person_id, shootout_id, category, count(*) as count FROM PersonShootout p\n" +
                "where p.id = ?\n" +
                "group by shootout_id";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.PersonShootout.class);
        q.setParameter( 1, personId );
        return q.<de.meetme.data.Person>getResultList();
    }

    public List<PersonShootout> countCategoriesAll() {
        String sqlQuery = "SELECT category, count(*) as count FROM PersonShootout p\n" +
                "group by category";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.PersonShootout.class);
        return q.<de.meetme.data.Person>getResultList();
    }

    public List<PersonShootout> countCategoriesByUser(long personId) {
        String sqlQuery = "SELECT person_id, category, count(*) as count FROM PersonShootout\n" +
                "WHERE person_id = " + personId + "\n" +
                "group by category";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.PersonShootout.class);
        q.setParameter( 1, personId );
        return q.<de.meetme.data.Person>getResultList();
    }

    //wie viele Shootouts hat eine person heute bearbeitet?
    public List<PersonShootout> countShootoutsByDate(long personID, String date) {
        String sqlQuery = "SELECT person_id, count(*) as count FROM PersonShootout \n" +
                "WHERE date = " + date + " AND person_id = " + personID + "\n" +
                "GROUP BY date";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.PersonShootout.class);
        return q.<de.meetme.data.Person>getResultList();
    }





}

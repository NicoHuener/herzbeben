package de.meetme.db;

import de.meetme.data.Bean.PersonShootoutCategoriesBean;
import de.meetme.data.Bean.PersonShootoutShootoutBean;
import de.meetme.data.Person;
import de.meetme.data.PersonShootout;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import java.util.ArrayList;
import java.util.List;

public class PersonShootoutDao extends AbstractDao<PersonShootout> {
private ShootoutDao shootoutdao;

    public PersonShootoutDao(SessionFactory sessionFactory,ShootoutDao shootoutdao) {
        super(sessionFactory);
        this.shootoutdao = shootoutdao;
    }

    public List<Person> getPersonsFromShootout(Shootout shootout) {
        String sqlQuery = "SELECT * FROM Person p\n" +
                "inner join PERSONSHOOTOUT ps on ps.person_id = p.ID\n" +
                "inner join shootout s on s.id = ps.shootout_id\n" +
                "where s.id = ?";

        Query q = currentSession().createNativeQuery(sqlQuery, Person.class);
        q.setParameter( 1, shootout.getId() );
        return q.<Person>getResultList();
    }



        // Gibt alle Shootouts aus, die am selben Tag von Usern durchlaufen wurden. Z.B. für Datenanalyse "Welches ist das beliebteste shootout am Tag x gewesen"
    public List<Shootout> getShootoutsByDate(String date) {
        String sqlQuery = "SELECT * FROM Shootout s\n" +
                "inner join PERSONSHOOTOUT ps on ps.shootout_id = s.ID\n" +
                "where ps.date = " + date;

        Query q = currentSession().createNativeQuery(sqlQuery, Shootout.class);
        return q.<Shootout>getResultList();
    }
// error: column id not found
    public List<PersonShootout> getBestShootoutsAll() {
        String sqlQuery = "SELECT shootout_id, category, count(*) as count FROM PersonShootout\n" +
                "group by shootout_id";

        Query q = currentSession().createNativeQuery(sqlQuery, PersonShootout.class);
        return q.<PersonShootout>getResultList();
    }

    public List<PersonShootout> getBestShootoutsByPerson(long personId) {
        String sqlQuery = "SELECT person_id, shootout_id, category, count(*) as count FROM PersonShootout p\n" +
                "where p.id = ?\n" +
                "group by shootout_id";

        Query q = currentSession().createNativeQuery(sqlQuery, PersonShootout.class);
        q.setParameter( 1, personId );
        return q.<PersonShootout>getResultList();
    }

    public List<PersonShootout> countCategoriesAll() {
        String sqlQuery = "SELECT category, count(*) as count FROM PersonShootout p\n" +
                "group by category";

        Query q = currentSession().createNativeQuery(sqlQuery, PersonShootout.class);
        return q.<PersonShootout>getResultList();
    }

    public List<PersonShootout> countCategoriesByUser(long personId) {
        String sqlQuery = "SELECT person_id, category, count(*) as count FROM PersonShootout\n" +
                "WHERE person_id = " + personId + "\n" +
                "group by category";

        Query q = currentSession().createNativeQuery(sqlQuery, PersonShootout.class);
        q.setParameter( 1, personId );
        return q.<PersonShootout>getResultList();
    }

    //wie viele Shootouts hat eine person heute bearbeitet?
    public int countShootoutsByDateAndPerson(long personID, String date) {
        String sqlQuery = "SELECT person_id FROM PersonShootout \n" +
                "WHERE date ='" + date +"' AND person_id = " + personID;

        Query query = currentSession().createSQLQuery(sqlQuery);
        List<Object[]> rows = query.list();
        return rows.size();
    }

    public int countShootoutsByDate(String date) {
        String sqlQuery = "SELECT person_id FROM PersonShootout \n" +
                "WHERE date ='" + date +"'";

        Query query = currentSession().createSQLQuery(sqlQuery);
        List<Object[]> rows = query.list();
        return rows.size();
    }

    public List<PersonShootoutCategoriesBean> personCategoryCount(long userId) {
        List<PersonShootoutCategoriesBean> beans = new ArrayList<>();

        String sqlQuery = "select count(*) as gesamtanzahl, CATEGORY as category from PERSONSHOOTOUT \n" +
                "where  PERSON_ID ='"+userId+"' \n" +
                "group by CATEGORY";

        Query query = currentSession().createSQLQuery(sqlQuery)
                .addScalar("gesamtanzahl", new IntegerType())
                .addScalar("category", new StringType());

        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            beans.add(new PersonShootoutCategoriesBean(row[1].toString(),Integer.parseInt(row[0].toString())));
        }
        return beans;
    }


    public List<PersonShootoutCategoriesBean> categoryCount() {
        List<PersonShootoutCategoriesBean> beans = new ArrayList<>();

        String sqlQuery = "select count(*) as gesamtanzahl, CATEGORY as category from PERSONSHOOTOUT \n" +
                "group by CATEGORY";

        Query query = currentSession().createSQLQuery(sqlQuery)
                .addScalar("gesamtanzahl", new IntegerType())
                .addScalar("category", new StringType());

        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            beans.add(new PersonShootoutCategoriesBean(row[1].toString(),Integer.parseInt(row[0].toString())));
        }
        return beans;
    }
    public List<PersonShootoutShootoutBean>countparticipantsbyshootout(){
        String sqlQuery= "Select shootout_id as shootoutId, count(*) as anzahl from PersonShootout group by shootout_id ";
   List<PersonShootoutShootoutBean> personshootoutlist = new ArrayList<>();
   Query query = currentSession().createSQLQuery(sqlQuery)
           .addScalar("shootout_id", new LongType())
           .addScalar("anzahl", new LongType());

   List<Object[]>rows = query.list();
   for (Object[] row: rows){
    Shootout shootout =shootoutdao.get(Long.parseLong(row[0].toString()));
       int anzahl = Integer.parseInt(row[1].toString());
       personshootoutlist.add(new PersonShootoutShootoutBean(shootout,anzahl));}
       return personshootoutlist;

}
}


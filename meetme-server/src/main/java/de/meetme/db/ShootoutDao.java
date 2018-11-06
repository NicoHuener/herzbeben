package de.meetme.db;

import de.meetme.data.Bean.ShootoutBean;
import de.meetme.data.Person;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShootoutDao extends AbstractDao<Shootout> {
private PersonDao personDao;
    public ShootoutDao(SessionFactory sessionFactory,PersonDao personDao) {
        super(sessionFactory);
        this.personDao =personDao;
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
    public List<ShootoutBean> countShootoutsByUser(long id) {
        List<ShootoutBean> beans = new ArrayList<>();
        String sqlQuery = "select person_id as personId, Count(*) as anzahl from Shootout where person_id ='"+id+"' group by person_id" ;

        Query query = currentSession().createSQLQuery(sqlQuery)
                .addScalar("personId",new LongType())
                .addScalar("anzahl", new IntegerType());

        List<Object[]>rows =query.list();
        for (Object[]row:rows){
            Person person =personDao.get(Long.parseLong(row[0].toString()));
            int anzahl = Integer.parseInt(row[1].toString());
            beans.add(new ShootoutBean(person, anzahl));
        }
        return beans;
    }
}

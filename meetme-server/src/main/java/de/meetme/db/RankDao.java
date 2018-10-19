package de.meetme.db;

import de.meetme.data.Rank;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RankDao  extends AbstractDao<Rank> {

        public RankDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Rank> getRankFromShootout(Shootout shootout) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where shootout_id = ? order by points";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, shootout.getId() );
        return q.<de.meetme.data.Rank>getResultList();
    }

}

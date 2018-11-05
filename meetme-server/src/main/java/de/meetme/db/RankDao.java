package de.meetme.db;

import de.meetme.data.Photo;
import de.meetme.data.Rank;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RankDao  extends AbstractDao<Rank> {

        public RankDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void updatepoints(int points,long shootoutid,long photoid){
        Rank rank = getRankFromShootoutandPhoto(shootoutid, photoid).get(0);
        int newpoints = rank.getPoints()+ points;
        long rankId = rank.getId();
        String sqlQuery = "update " + getEntityClass().getSimpleName() + " set points = " + newpoints + " where id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, rankId );
        q.executeUpdate();
    }
    public List<Rank> getpointsinsgesammt() {
        String sqlQuery = "select photo_id,sum(points) from " + getEntityClass().getSimpleName() +  " group by photo_id";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        return q.<de.meetme.data.Photo>getResultList();
    }

    public List<Rank> getpointsinsg(long photoId) {
        String sqlQuery = "select photo_id,sum(points) from " + getEntityClass().getSimpleName() +  " where photo_id = ? group by photo_id";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, photoId );
        return q.<de.meetme.data.Photo>getResultList();
    }
    public List<Rank> getWinner(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where shootout_id = ? order by points";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Shootout>getResultList();
    }

    /*public void updatepoints(int points,long rankid){
        Rank rank = get(rankId);
        int newpoints = rank.getPoints()+ points;

        String sqlQuery = "update " + getEntityClass().getSimpleName() + " set points = " + newpoints + " where id = ?";

        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, rankId );
        q.executeUpdate();
    }*/

   public List<Rank> getRankFromShootoutandPhoto(long shootoutId, long photoId) {
        String sql = "select* from " + getEntityClass().getSimpleName() +  " where shootout_id = ? and photo_id = ?";
        Query q2 = currentSession().createNativeQuery(sql, de.meetme.data.Rank.class);
        q2.setParameter( 1, shootoutId );
        q2.setParameter( 2, photoId );
        return q2.<de.meetme.data.Rank>getResultList();
    }

    public List<Rank> getRankFromShootout(long shootoutId) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where shootout_id = ? order by points";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, shootoutId );
        return q.<de.meetme.data.Rank>getResultList();
    }

}

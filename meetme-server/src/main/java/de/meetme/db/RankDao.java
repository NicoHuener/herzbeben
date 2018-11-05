package de.meetme.db;

import de.meetme.data.Bean.BestPhotoBean;
import de.meetme.data.Photo;
import de.meetme.data.Rank;
import de.meetme.data.Shootout;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import java.util.ArrayList;
import java.util.List;

public class RankDao  extends AbstractDao<Rank> {

    private ShootoutDao shootoutDao;
    private PhotoDao photoDao;

    public RankDao(SessionFactory sessionFactory, ShootoutDao shootoutDao, PhotoDao photoDao) {
        super(sessionFactory);
        this.shootoutDao = shootoutDao;
        this.photoDao = photoDao;
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
    public List<BestPhotoBean> getpointsinsgesammt() {
        String sqlQuery = "select photo_id as photoID,sum(points) as points from Rank group by photo_id";
        List <BestPhotoBean> rankList = new ArrayList<>();

        Query query = currentSession().createSQLQuery(sqlQuery)
                .addScalar("photoID", new LongType())
                .addScalar("points", new IntegerType());

        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            Photo photo = photoDao.get(Long.parseLong(row[0].toString()));
            int points = Integer.parseInt(row[1].toString());
            rankList.add(new BestPhotoBean(photo,points));
        }
        return rankList;
    }

    public List<Rank> getpoints(long photoId) {
        String sqlQuery = "select shootout_id as shootoutID, photo_id as photoID,sum(points) as points from Rank where photo_id ='"+photoId+"'";
        List <Rank> rankList = new ArrayList<>();

        Query query = currentSession().createSQLQuery(sqlQuery)
                .addScalar("shootoutID", new LongType())
                .addScalar("photoID", new LongType())
                .addScalar("points", new IntegerType());

        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            Shootout shootout = shootoutDao.get(Long.parseLong(row[0].toString()));
            Photo photo = photoDao.get(Long.parseLong(row[1].toString()));
            int points = Integer.parseInt(row[2].toString());
            rankList.add(new Rank(shootout,points,photo));
        }
        return rankList;
    }
    public List<Rank> getWinner(long id) {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where shootout_id = ? order by points desc";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, id );
        return q.<de.meetme.data.Rank>getResultList();
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
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where shootout_id = ? order by points desc";
        Query q = currentSession().createNativeQuery(sqlQuery, de.meetme.data.Rank.class);
        q.setParameter( 1, shootoutId );
        return q.<de.meetme.data.Rank>getResultList();
    }

}


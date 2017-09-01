package de.meetme.db;

import de.meetme.data.PersistentObject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;


abstract public class AbstractDao<T extends PersistentObject> extends AbstractDAO<T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractDao.class);

    AbstractDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T get(Serializable id) {
        log.debug("Get " + id);
        String sqlQuery = "select * from " + getEntityClass().getSimpleName() + " where id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, getEntityClass());
        q.setParameter( 1, id );
        List<T> result = q.getResultList();
        if(result.isEmpty()) {
            return null;
        }
        return (T)q.getResultList().get(0);
    }

    @Override
    public T persist(T entity) throws HibernateException {
        return super.persist(entity);
    }

    public void remove(T entity) {
        log.debug("Remove " + entity);
        String sqlQuery = "delete from " + getEntityClass().getSimpleName() + " where id = ?";
        Query q = currentSession().createNativeQuery(sqlQuery, getEntityClass());
        q.setParameter(1, entity.getId());
        q.executeUpdate();
    }

    public void update(T entity) {
        log.debug("Update " + entity);
        persist(entity);
    }

    public List<T> getAll() {
        String sqlQuery = "select * from " + getEntityClass().getSimpleName();
        Query q = currentSession().createNativeQuery(sqlQuery, getEntityClass());
        return q.<T>getResultList();
    }
}

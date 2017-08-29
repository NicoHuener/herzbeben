package de.meetme.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


abstract public class AbstractDao<T> extends AbstractDAO<T> {
    private static final Logger log = LoggerFactory.getLogger(AbstractDao.class);

    AbstractDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T get(Serializable id) {
        return super.get(id);
    }

    @Override
    public T persist(T entity) throws HibernateException {
        return super.persist(entity);
    }

    public void remove(T entity) {
        currentSession().remove(entity);
    }

}

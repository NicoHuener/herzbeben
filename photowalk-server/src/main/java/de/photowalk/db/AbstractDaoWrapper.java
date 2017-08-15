package de.photowalk.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.io.Serializable;

abstract public class AbstractDaoWrapper<T> extends AbstractDAO<T> {

    AbstractDaoWrapper(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void remove(T element) {
        currentSession().remove(element);
    }

    @Override
    public T get(Serializable id) {
        return super.get(id);
    }

    @Override
    public T persist(T entity) throws HibernateException {
        return super.persist(entity);
    }
}

package com.copperarrow.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dbeer on 25/09/16.
 */
public abstract class AbstractDAO<T> implements EntitiesDAO<T>, Serializable {

    private Class<T> entityClass;
    private static String ASCENDING = "ASCENDING";
    private static String DESCENDING = "DESCENDING";

    protected AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public List<T> getAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<T> getRange(long first, long count) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setFirstResult((int) first);
        q.setMaxResults((int) count);
        return q.getResultList();
    }

    @Override
    public int size() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> rt = cq.from(entityClass);
        cq.select((Selection<? extends T>) getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void save(Object entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public List<T> sort(String sortOrder, String sortValue) {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        CriteriaQuery<T> select = criteriaQuery.select(root);
        if (sortOrder.equals(ASCENDING)) {
            criteriaQuery.orderBy(getEntityManager().getCriteriaBuilder().asc(root.get(sortValue)));
        } else if (sortOrder.equals(DESCENDING)) {
            criteriaQuery.orderBy(getEntityManager().getCriteriaBuilder().desc(root.get(sortValue)));
        }
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T load(long id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void update(Object entity) {
        getEntityManager().merge(entity);
    }
}

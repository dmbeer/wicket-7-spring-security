package com.copperarrow.dao;

import java.util.List;

/**
 * Created by dbeer on 25/09/16.
 */
public interface EntitiesDAO<T> {

    List<T> getAll();

    List getRange(final long first, final long count);

    int size();

    void save(T entity);

    List<T> sort(String sortOrder, String sortValue);

    T load(final long id);

    void update(T entity);
}

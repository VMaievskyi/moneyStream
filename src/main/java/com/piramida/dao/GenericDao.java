package com.piramida.dao;

import java.util.List;

public interface GenericDao<T> {

    void deleteAll();

    public List<T> findAll();

    void save(T testAccount);

    void delete(T testAccount);

}

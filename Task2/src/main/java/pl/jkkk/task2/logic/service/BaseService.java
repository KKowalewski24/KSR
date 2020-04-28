package pl.jkkk.task2.logic.service;

import java.util.Collection;
import java.util.List;

public interface BaseService<T> {

    T findById(Integer id);

    List<T> findAll();

    T save(T object);

    List<T> saveAll(Collection<T> collection);

    void deleteById(Integer id);

    void delete(T object);

    void deleteAll();
}

package pl.jkkk.task2.logic.service;

import java.util.List;

public interface BaseService<T> {

    T findById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    void delete(T object);

    void deleteAll();
}

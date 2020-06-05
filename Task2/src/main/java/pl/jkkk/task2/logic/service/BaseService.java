package pl.jkkk.task2.logic.service;

public interface BaseService<T> {

    T findById(Long id);

    void deleteById(Long id);

    void delete(T object);

    void deleteAll();
}

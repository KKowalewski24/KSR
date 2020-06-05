package pl.jkkk.task2.logic.service;

import java.util.List;

public interface WrapperService<T1, T2> extends BaseService<T1> {

    List<T1> findAll();

    T2 findByName(String name);

    T1 save(T1 linguisticQuantifier);

    void deleteByName(String name);
}

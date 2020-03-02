package pl.jkkk.task1.dao;

import pl.jkkk.task1.exception.DaoException;

public interface Dao<T> {

    T read() throws DaoException;

    void write(T object) throws DaoException;
}
    
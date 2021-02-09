package main.java.com.fvv_edu.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById (ID id);
    List<T> getAll();
    T save(T object);
    T update(T oldObject, T updateObject);
    void deleteById(ID id);
}

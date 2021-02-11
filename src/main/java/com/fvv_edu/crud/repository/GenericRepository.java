package main.java.com.fvv_edu.crud.repository;

import main.java.com.fvv_edu.crud.model.GenericModel;
import main.java.com.fvv_edu.crud.model.Skill;

import java.util.List;

public interface GenericRepository<T extends GenericModel, ID extends Number> {
    T getById (ID id);
    List<T> getAll();
    T save(T object);
    T update(T oldObject, T updateObject);
    void deleteById(ID id);
}

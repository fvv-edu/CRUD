package main.java.com.fvv_edu.crud.controller;

import main.java.com.fvv_edu.crud.model.GenericModel;
import main.java.com.fvv_edu.crud.repository.GenericRepository;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractController <T extends GenericModel> implements GenericController <T>{

    public void checkNull (Long id) {
        try {
            if (id == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }
    }

    public void checkArgument (Long id) {
        try {
            if (id < 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
    }

    public void checkNoSuchElement(Long id, List <T> list) { //+
        boolean result = false;
        try {
            for (T x : list) {
                if (x.getId().equals(id)) {
                    result = true;
                    break;
                }else {
                    result = false;
                }
            }
            if (result == false) {
                throw new NoSuchElementException();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }

    public T getById(Long id, GenericRepository repo) {
        List<T> list = repo.getAll();
        boolean result = false;
        checkNull(id);
        checkArgument(id);
        checkNoSuchElement(id, list);
        return (T) repo.getById(id);
    }


}

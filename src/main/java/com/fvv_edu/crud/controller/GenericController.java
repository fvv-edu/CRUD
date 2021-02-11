package main.java.com.fvv_edu.crud.controller;

import main.java.com.fvv_edu.crud.model.GenericModel;

import java.util.List;

public interface GenericController <T extends GenericModel>{

    void checkNull (Long id);
    void checkArgument (Long id);
    void checkNoSuchElement (Long id, List<T> list);


}

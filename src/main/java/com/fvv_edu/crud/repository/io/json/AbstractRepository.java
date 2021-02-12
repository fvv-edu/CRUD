package main.java.com.fvv_edu.crud.repository.io.json;

import com.google.gson.Gson;
import main.java.com.fvv_edu.crud.model.GenericModel;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.GenericRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractRepository <T extends GenericModel,ID extends Number> implements GenericRepository <T,ID> {

    private String choiceFilePath(int i) { // перенести в другой слой
        String filePath;
        if (i == 1) {
            filePath = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\skills.json";
        }else if (i == 2){
            filePath = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\accounts.json";
        }else if (i == 3){
            filePath = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\developers.json";
        }else {
            System.out.println("This path is not found"); // исправить на эксепшн
            filePath = null;
        }
        return filePath;
    }


    public T getById (ID id) {
        T needObject = null;
        for (T x : getAllInternal()) {
            if (x.getId().equals(id)) {
                needObject = x;
                break;
            }
        }
        return needObject;
    }


    public abstract List<T> getAllInternal(); //+



    @Override
    public List<T> getAll() {
        return getAllInternal();
    }


    private String convertForJson(T object) { //+
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString + "\n";
    }


    private T saveInternal(T object) { //+
        String filePath = choiceFilePath(1);
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(convertForJson(object));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return object;
    }


    private T saveInternal(T object, ID id) { //+
        String filePath = choiceFilePath(1);
        List<T> objectList = getAllInternal();
        objectList.set((int)id, object);
        Stream<String> newStream = objectList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(filePath)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return object;
    }

    @Override
    public void deleteById(ID id) {
        String filePath = choiceFilePath(1);
        List<T> objectList = getAllInternal();
        int needIndex = 0;
        for (T x : objectList) {
            if (x.getId().equals(id)) {
                needIndex = objectList.indexOf(x);
            }
        }
        objectList.remove(needIndex);
        Stream<String> newStream = objectList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(filePath)){
            for (String x : forFile) {
                fw.write(x);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }

    @Override
    public T update(T oldObject, T updateObject) {
        List<T> objectList = getAllInternal();
        if (objectList.contains(oldObject) == true) {
            long index = objectList.indexOf(oldObject);
            Long id = index;
            saveInternal(updateObject, (ID) id);
        }
        return updateObject;
    }

    @Override
    public T save(T object) {
        return saveInternal(object);
    }
}

package main.java.com.fvv_edu.crud.repository.io.json;

import com.google.gson.Gson;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.DeveloperRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonDeveloperRepositoryImpl extends AbstractRepository <Developer, Long> implements DeveloperRepository {

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

    public List<Developer> getAllInternal() { //+
        String filePath = choiceFilePath(3);
        String fromSource;
        List <Developer> objects = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(
                new FileReader(filePath))){ //потом исправить параметр
            while ((fromSource = br.readLine()) != null) {
                Developer object = gson.fromJson(fromSource, Developer.class);
                objects.add(object);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return objects;
    }
}

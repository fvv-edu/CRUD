package main.java.com.fvv_edu.crud.repository.io.json;

import com.google.gson.Gson;
import main.java.com.fvv_edu.crud.model.GenericModel;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.SkillRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSkillRepositoryImpl extends AbstractRepository<Skill, Long> implements SkillRepository {

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

    public List<Skill> getAllInternal() { //+
        String filePath = choiceFilePath(1);
        String fromSource;
        List <Skill> objects = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(
                new FileReader(filePath))){ //потом исправить параметр
            while ((fromSource = br.readLine()) != null) {
                Skill object = gson.fromJson(fromSource, Skill.class);
                objects.add(object);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return objects;
    }
}

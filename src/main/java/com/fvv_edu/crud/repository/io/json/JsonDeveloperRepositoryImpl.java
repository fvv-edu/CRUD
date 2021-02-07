package main.java.com.fvv_edu.crud.repository.io.json;

import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.DeveloperRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {

    private List<Developer> developers;
    private String fileName =
            "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\developers.json";
    private Long developerId;
    private Long accountId;
    private String skillIdString;
    private List <Long> skillId;


    private String convertForJson(Developer developer) { //+
        JSONObject object = new JSONObject();
        JSONArray skills = new JSONArray();
        String jsonString = "";
        try {
            skills.put(developer.getSkills());
            object.put("id", developer.getId());
            object.put("account", developer.getAccount());
            object.put("skills", skills);
            System.out.println(object);
            jsonString = object.valueToString(developer);
        } catch (JSONException e) {
            System.out.println(e);
        }
        return jsonString + "\n";
    }

    private Developer saveInternal(Developer developer) { //+
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(convertForJson(developer));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return developer;
    }

    public Developer save(Developer developer) {
        return saveInternal(developer);
    } //+



    @Override
    public Developer getById(Long Long) {
        return null;
    }

    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public Developer update(Developer skill, Developer updateSkill) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}

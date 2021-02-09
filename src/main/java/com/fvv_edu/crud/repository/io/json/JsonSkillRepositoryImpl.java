package main.java.com.fvv_edu.crud.repository.io.json;

import com.google.gson.Gson;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonSkillRepositoryImpl {
    private String fileName = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\skills.json";


    public Skill getById(Long id) { //+
        Skill needSkill = null;
        for (Skill x : getAllInternal()) {
            if (x.getId().equals(id)) {
                needSkill = x;
                break;
            }
        }
        return needSkill;
    }


    private List<Skill> getAllInternal() { //+
        String fromSource;
        List skills = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((fromSource = br.readLine()) != null) {
                Skill skill = gson.fromJson(fromSource, Skill.class);
                skills.add(skill);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skills;
    }


    public List<Skill> getAll() { //+
        return getAllInternal();
    }

    private String convertForJson(Developer developer) { //+
        Gson gson = new Gson();
        String jsonString = gson.toJson(developer);
        return jsonString + "\n";
    }

    private String convertForJson(Skill skill) { //+
        Gson gson = new Gson();
        String jsonString = gson.toJson(skill);
        return jsonString + "\n";
    }


    private Skill saveInternal(Skill skill) { //+
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(convertForJson(skill));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skill;
    }


    private Skill saveInternal(Skill skill, int index) { //+
        List<Skill> skillList = getAllInternal();
        skillList.set(index, skill);
        Stream<String> newStream = skillList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skill;
    }


    public Skill save(Skill skill) {
        return saveInternal(skill);
    }


    public Skill update(Skill skill, Skill updateSkill) { //+
        List<Skill> skillList = getAllInternal();
        if (skillList.contains(skill) == true) {
            int index = skillList.indexOf(skill);
            saveInternal(updateSkill, index);
        }
        return updateSkill;
    }


    public void deleteById(Long id) { //+
        List<Skill> skillList = getAllInternal();
        int needIndex = 0;
        for (Skill x : skillList) {
            if (x.getId().equals(id)) {
                needIndex = skillList.indexOf(x);
            }
        }
        skillList.remove(needIndex);
        Stream<String> newStream = skillList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)){
            for (String x : forFile) {
                fw.write(x);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }
}

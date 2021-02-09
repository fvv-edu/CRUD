package main.java.com.fvv_edu.crud.repository.io.json;

import com.google.gson.*;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.repository.DeveloperRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {

    private String fileName =
            "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\developers.json";

    public Developer getById(Long id) { //+
        Developer needDeveloper = null;
        for (Developer x : getAllInternal()) {
            if (x.getId().equals(id)) {
                needDeveloper = x;
                break;
            }
        }
        return needDeveloper;
    }


    private List<Developer> getAllInternal() { //+
        String fromSource;
        List developers = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((fromSource = br.readLine()) != null) {
                Developer developer = gson.fromJson(fromSource, Developer.class);
                developers.add(developer);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return developers;
    }


    public List<Developer> getAll() { //+
        return getAllInternal();
    }


    private String convertForJson(Developer developer) { //+
        Gson gson = new Gson();
        String jsonString = gson.toJson(developer);
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

    private Developer saveInternal(Developer developer, int index) { //+
        List<Developer> developerList = getAllInternal();
        developerList.set(index, developer);
        Stream<String> newStream = developerList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return developer;
    }


    public Developer save(Developer developer) {   //+
        return saveInternal(developer);
    }


    @Override
    public Developer update(Developer oldDeveloper, Developer updateDeveloper) {
        List<Developer> developerList = getAllInternal();
        if (developerList.contains(oldDeveloper) == true) {
            int index = developerList.indexOf(oldDeveloper);
            saveInternal(updateDeveloper, index);
        }
        return updateDeveloper;
    }


    public void deleteById(Long id) {
        List<Developer> developerList = getAllInternal();
        int needIndex = 0;
        for (Developer x : developerList) {
            if (x.getId().equals(id)) {
                needIndex = developerList.indexOf(x);
            }
        }
        developerList.remove(needIndex);
        Stream<String> newStream = developerList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }
}

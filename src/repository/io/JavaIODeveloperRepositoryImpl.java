package repository.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Developer;
import repository.DeveloperRepository;


public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private List<Developer> developers;
    private String fileName = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\developer.txt";

    public Developer getById(Long id) { //+
        List<Developer> developerList = getAllInternal();
        Developer needDeveloper = null;
        for (Developer x : developerList) {
            if (x.getId().equals(id)) {
                needDeveloper = x;
                break;
            }
        }
        return needDeveloper;
    }


    private List<Developer> getAllInternal() { //+
        String anyDeveloper;
        developers = new ArrayList<Developer>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((anyDeveloper = br.readLine()) != null) {
                Developer developerObj = new Developer(anyDeveloper);
                developers.add(developerObj);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return developers;
    }


    public List<Developer> getAll() { //+
        return getAllInternal();
    }


    private String convertForFile(Developer developer) { //+
        return (developer.getId() + ". " + developer.getName() + "\n" );
    }


    private Developer saveInternal(Developer developer) { //+
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(convertForFile(developer));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return developer;
    }


    private Developer saveInternal(Developer developer, int index) { //+
        List<Developer> developerList = getAllInternal();
        developerList.set(index, developer);
        Stream<String> newStream = developerList.stream().map((n) -> (convertForFile(n)));
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


    public Developer save(Developer developer) {
        return saveInternal(developer);
    }


    public Developer update(Developer developer, Developer updateDeveloper) { //+
        List<Developer> developerList = getAllInternal();
        if (developerList.contains(developer) == true) {
            int index = developerList.indexOf(developer);
            saveInternal(updateDeveloper, index);
        }
        return updateDeveloper;
    }


    public void deleteById(Long id) { //+
        List<Developer> developerList = getAllInternal();
        int needIndex = 0;
        for (Developer x : developerList) {
            if (x.getId().equals(id)) {
                needIndex = developerList.indexOf(x);
            }
        }
        developerList.remove(needIndex);
        Stream<String> newStream = developerList.stream().map((n) -> (convertForFile(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)){
            for (String x : forFile) {
                fw.write(x);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }


    public List<Developer> getDevelopers() {
        return developers;
    }
}

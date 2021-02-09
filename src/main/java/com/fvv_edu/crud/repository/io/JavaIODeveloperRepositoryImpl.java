package main.java.com.fvv_edu.crud.repository.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.AccountRepository;
import main.java.com.fvv_edu.crud.repository.DeveloperRepository;
import main.java.com.fvv_edu.crud.repository.SkillRepository;


public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private List<Developer> developers;
    private String fileName =
            "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\developer.txt";
    private Long developerId;
    private Long accountId;
    private String skillIdString;
    private List<Long> skillId;


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
        String fromSource;
        AccountRepository accountRepo = new JavaIOAccountRepositoryImpl();
        SkillRepository skillRepo = new JavaIOSkillRepositoryImpl();
        developers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {
            while ((fromSource = br.readLine()) != null) {
                developerId = Long.valueOf(fromSource.substring(0, 1));
                accountId = Long.valueOf(fromSource.substring(3, 4));
                skillIdString = fromSource.substring(6);
                skillId = convertForObject();
                Account account = accountRepo.getById(accountId);
                Skill[] skills = new Skill[skillId.size()];
                int i = 0;
                for (Long x : skillId) {
                    skills[i] = skillRepo.getById(x);
                    i++;
                }
                Developer developerObj = new Developer(developerId, account, skills);
                developers.add(developerObj);
            }

        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return developers;
    }


    public List<Developer> getAll() { //+
        return getAllInternal();
    }


    private String convertForFile(Developer developer) { //+
        String idSkillString = "";
        for (Skill x : developer.getSkills()) {
            if (developer.getSkills().indexOf(x) == 0) {
                idSkillString = String.valueOf(x.getId());
            } else {
                idSkillString = idSkillString + ", " + x.getId();
            }
        }
        return (developer.getId() + ". " + developer.getAccount().getId() + "; " + idSkillString + "\n");
    }

    private List<Long> convertForObject() {
        List<Long> skillIdList = new ArrayList<>();
        if (skillIdString.length() >= 3) {
            for (int i = 0; i < skillIdString.length(); ) {
                skillIdList.add(Long.valueOf(skillIdString.substring(i, i + 1)));
                i = i + 3;
            }
        } else {
            skillIdList.add(Long.valueOf(skillIdString.substring(0, 1)));
        }
        return skillIdList;
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


    public Developer update(Developer oldDeveloper, Developer updateDeveloper) { //+
        List<Developer> developerList = getAllInternal();
        if (developerList.contains(oldDeveloper) == true) {
            int index = developerList.indexOf(oldDeveloper);
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
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }
}

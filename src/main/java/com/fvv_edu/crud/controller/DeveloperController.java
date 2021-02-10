package main.java.com.fvv_edu.crud.controller;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.AccountRepository;
import main.java.com.fvv_edu.crud.repository.DeveloperRepository;
import main.java.com.fvv_edu.crud.repository.SkillRepository;
import main.java.com.fvv_edu.crud.repository.io.json.JsonAccountRepositoryImpl;
import main.java.com.fvv_edu.crud.repository.io.json.JsonDeveloperRepositoryImpl;
import main.java.com.fvv_edu.crud.repository.io.json.JsonSkillRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class DeveloperController {
    private DeveloperRepository developerRepo = new JsonDeveloperRepositoryImpl();
    private SkillRepository skillRepo = new JsonSkillRepositoryImpl();
    private AccountRepository accountRepo = new JsonAccountRepositoryImpl();


    public Developer getById (Long id) { //+
        List<Developer> developerList = getAllInternal();
        boolean result = false;
        try {
            if (id == null) {
                throw new NullPointerException();
            }
            if (id < 0) {
                throw new IllegalArgumentException("id must be more 0");
            }
            for (Developer x : developerList) {
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
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
        return developerRepo.getById(id);
    }


    private List<Developer> getAllInternal() {
        return developerRepo.getAll();
    }


    public List<Developer> getAll() { //++
        return developerRepo.getAll();
    }


    public Developer save(Long developerId, Long accountId, List<Long> skillId) { //+
        try {
            if (developerId < 0 || accountId < 0) {
                throw new IllegalArgumentException("id must be more 0");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
        List<Developer> developerList = getAllInternal();
        Account account = accountRepo.getById(accountId);
        Skill[] skills = new Skill[skillId.size()];
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skillRepo.getById((skillId.get(i)));
        }
        Developer developer = new Developer(developerId, account, skills);
        Developer result = null;
        try {
            if (developerList.contains(developer)) {
                throw new IllegalArgumentException("This id or developer is exist");
            }else if (developer.equals(null)) {
                throw new NullPointerException();
            }else
                result = developerRepo.save(developer);
        }catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public Developer update(Long developerId, Long accountId, List<Long> skillId) { //++
        List<Developer> developerList = getAllInternal();
        try {
            if (developerId < 0 || accountId < 0) {
                throw new IllegalArgumentException("id must be more 0");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
        Account account = accountRepo.getById(accountId);
        Developer oldDeveloper = null;
        Skill[] skills = new Skill[skillId.size()];
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skillRepo.getById((skillId.get(i)));
        }
        for (Developer x : developerList) {
            if (x.getId().equals(developerId)) {
                oldDeveloper = x;
                break;
            }
        }
        Developer updateDeveloper = new Developer(developerId, account, skills);
        Developer result = null;
        try {
            if (!developerList.contains(oldDeveloper)) {
               throw new NoSuchElementException("This developer not found");
            }else if (oldDeveloper.getAccount().equals(account)) {
            throw new IllegalArgumentException("This developer is exist");
            }else if (oldDeveloper.equals(null) || updateDeveloper.equals(null)) {
                throw new NullPointerException();
            }else{
                result = developerRepo.update(oldDeveloper, updateDeveloper);
            }
        }catch (NoSuchElementException | IllegalArgumentException  e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public void deleteById(Long id) { //++
        List<Developer> developerList = getAllInternal();
        boolean result = false;
        for (Developer x : developerList) {
            if (x.getId().equals(id)) {
                result = true;
                break;
            }else {
                result = false;
            }
        }
        try {
            if (id == null) {
                throw new NullPointerException();
            }else if (id < 0) {
                throw new IllegalArgumentException("id must be more 0");
            }else if (result == false) {
                throw new NoSuchElementException("This developer not found");
            }else {
                developerRepo.deleteById(id);
            }
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }
}



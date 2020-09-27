package main.java.com.fvv_edu.crud.controller;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.AccountRepository;
import main.java.com.fvv_edu.crud.repository.DeveloperRepository;
import main.java.com.fvv_edu.crud.repository.SkillRepository;
import main.java.com.fvv_edu.crud.repository.io.JavaIOAccountRepositoryImpl;
import main.java.com.fvv_edu.crud.repository.io.JavaIODeveloperRepositoryImpl;
import main.java.com.fvv_edu.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DeveloperController {
    private DeveloperRepository developerRepo = new JavaIODeveloperRepositoryImpl();
    private SkillRepository skillRepo = new JavaIOSkillRepositoryImpl();
    private AccountRepository accountRepo = new JavaIOAccountRepositoryImpl();

    private Developer getByIdInternal (Long id) { //++
        List<Developer> developerList = getAllInternal();
        boolean result = false;
        try {
            if (id == null) {
                throw new NullPointerException();
            }
            if (id < 0) {
                throw new IllegalArgumentException();
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

    public Developer getById (Long id) { //++
        return getByIdInternal(id);
    }


    private List<Developer> getAllInternal() {
        return developerRepo.getAll();
    }


    public List<Developer> getAll() { //++
        return developerRepo.getAll();
    }


    public Developer save(Long developerId, Long accountId, List<Long> skillId) { //+++
        List<Developer> developerList = getAllInternal();
        Account account = accountRepo.getById(accountId);
        Skill[] skills = new Skill[skillId.size()];
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skillRepo.getById((skillId.get(i)));
        }
        Developer developer = new Developer(developerId, account, skills);
        System.out.println(developer);
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
        Account account = accountRepo.getById(accountId);
        Developer oldDeveloper = null;
        Skill[] skills = new Skill[skillId.size()];
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skillRepo.getById((skillId.get(i)));
        }
        System.out.println(developerList.get(0));
        System.out.println(developerList.get(1));
        for (Developer x : developerList) {
            //System.out.println(x);
            if (x.getId().equals(developerId)) {
                oldDeveloper = x;
                break;
            }
        }
        Developer updateDeveloper = new Developer(developerId, account, skills);
        Developer result = null;
        System.out.println(oldDeveloper);
        System.out.println(updateDeveloper);
        try {
            if (oldDeveloper.equals(null) || updateDeveloper.equals(null)) {
                throw new NullPointerException();
            }else if (!developerList.contains(oldDeveloper)) {
               throw new NoSuchElementException();
           } else {
                result = developerRepo.update(oldDeveloper, updateDeveloper);
            }
        }catch (NullPointerException | NoSuchElementException e) {
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
                throw new IllegalArgumentException();
            }else if (result == false) {
                throw new NoSuchElementException();
            }else {
                developerRepo.deleteById(id);
            }
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }
}



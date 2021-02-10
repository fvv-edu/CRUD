package main.java.com.fvv_edu.crud.controller;

import java.util.List;
import java.util.NoSuchElementException;

import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.*;
import main.java.com.fvv_edu.crud.repository.io.json.JsonSkillRepositoryImpl;

public class SkillController {
    private SkillRepository repo = new JsonSkillRepositoryImpl();

    public Skill getById (Long id) { //++
        List<Skill> skillList = getAllInternal();
        boolean result = false;
        try {
            if (id == null) {
                throw new NullPointerException();
            }
            if (id < 0) {
                throw new IllegalArgumentException();
            }
            for (Skill x : skillList) {
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
        return repo.getById(id);
    }


    private List<Skill> getAllInternal() {
        return repo.getAll();
    }


    public List<Skill> getAll() { //+
        return repo.getAll();
    }


    public Skill save(Skill skill) { //++
        List<Skill> skillList = getAllInternal();
        Skill result = null;
        try {
            if (skillList.contains(skill)) {
                throw new IllegalArgumentException("This id or skill is exist");
            }else if (skill.equals(null)) {
                throw new NullPointerException();
            }else
                result = repo.save(skill);
        }catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public Skill update(Long skillId, Skill updateSkill) { //++
        List<Skill> skillList = getAllInternal();
        Skill result = null;
        Skill oldSkill = null;
        boolean isExistName = false;
        for (Skill x : skillList) {
            if (x.getId().equals(skillId)) {
                oldSkill = x;
                continue;
            }else if (x.getName().equals(updateSkill)){
                isExistName = true;
            }
        }
        try {
            if (oldSkill.equals(null) || updateSkill.equals(null)) {
                throw new NullPointerException();
            } else if (!skillList.contains(oldSkill)) {
                throw new NoSuchElementException();
            }else if (isExistName == true) {
                throw new IllegalArgumentException("This name account is exist");
            }else {
                result = repo.update(oldSkill, updateSkill);
            }
        } catch (NullPointerException | NoSuchElementException | IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public void deleteById(Long id) { //++
        List<Skill> skillList = getAllInternal();
        boolean result = false;
        for (Skill x : skillList) {
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
                repo.deleteById(id);
            }
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }
}



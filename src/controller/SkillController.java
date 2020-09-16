package controller;

import java.util.List;
import java.util.NoSuchElementException;

import model.Skill;
import repository.*;
import repository.io.*;

public class SkillController {
    private SkillRepository repo = new JavaIOSkillRepositoryImpl();

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


    public Skill update(Skill skill, Skill updateSkill) { //++
        List<Skill> skillList = getAllInternal();
        Skill result = null;
        try {
            if (skill.equals(null) || updateSkill.equals(null)) {
                throw new NullPointerException();
            }else if (!skillList.contains(skill)) {
               throw new NoSuchElementException();
           } else {
                result = repo.update(skill, updateSkill);
            }
        }catch (NullPointerException | NoSuchElementException e) {
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



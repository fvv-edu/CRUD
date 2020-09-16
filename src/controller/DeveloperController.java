package controller;

import model.Developer;
import repository.DeveloperRepository;
import repository.io.JavaIODeveloperRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class DeveloperController {
    private DeveloperRepository repo = new JavaIODeveloperRepositoryImpl();

    public Developer getById (Long id) { //++
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
        return repo.getById(id);
    }


    private List<Developer> getAllInternal() {
        return repo.getAll();
    }


    public List<Developer> getAll() { //+
        return repo.getAll();
    }


    public Developer save(Developer developer) { //++
        List<Developer> developerList = getAllInternal();
        Developer result = null;
        try {
            if (developerList.contains(developer)) {
                throw new IllegalArgumentException("This id or developer is exist");
            }else if (developer.equals(null)) {
                throw new NullPointerException();
            }else
                result = repo.save(developer);
        }catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public Developer update(Developer developer, Developer updateDeveloper) { //++
        List<Developer> developerList = getAllInternal();
        Developer result = null;
        try {
            if (developer.equals(null) || updateDeveloper.equals(null)) {
                throw new NullPointerException();
            }else if (!developerList.contains(developer)) {
               throw new NoSuchElementException();
           } else {
                result = repo.update(developer, updateDeveloper);
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
                repo.deleteById(id);
            }
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }
}



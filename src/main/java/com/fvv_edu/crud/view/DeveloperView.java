package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.controller.DeveloperController;
import main.java.com.fvv_edu.crud.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    DeveloperController controller = new DeveloperController();
    private Scanner scanner;


    private Long enterDeveloperId () {
        System.out.println("Enter developer id");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        Long id = scanner.nextLong();
        return id;
    }


    private Long enterAccountId () {
        System.out.println("Enter developer account id");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        Long id = scanner.nextLong();
        return id;
    }


    private List enterSkillId () {
        List<Long> skillIdList = new ArrayList<>();
        System.out.println("Enter developer skill id");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        Long id = scanner.nextLong();
        while (id >= 0){
            skillIdList.add(id);
            id = scanner.nextLong();
        }
        return skillIdList;
    }


    public void getDeveloperById() { //+
        Developer developer = controller.getById(enterDeveloperId());
        System.out.println(developer);
    }


    public void getAll() {
        controller.getAll();
    }


    public void save() { //+
        Long developerId = enterDeveloperId();
        Long accountId = enterAccountId();
        List<Long> skillIdList = enterSkillId();
        controller.save(developerId, accountId, skillIdList);
    }


    public void update() { //+
        Long developerId = enterDeveloperId();
        Long updateAccountId = enterAccountId();
        List<Long> skillIdList = enterSkillId();
        controller.update(developerId, updateAccountId, skillIdList);
    }


    public void deleteById() { //+
        Long id = enterDeveloperId();
        controller.deleteById(id);
    }
}

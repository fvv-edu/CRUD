package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.controller.DeveloperController;
import main.java.com.fvv_edu.crud.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    DeveloperController controller = new DeveloperController();

    public void getDeveloperById() { //+
        System.out.println("Enter developer id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        Developer developer = controller.getById(number);
        System.out.println(developer);

    }

    public void getAll() {
        controller.getAll();
    }


    public void save() { //+
        Long developerId;
        Long accountId;
        Long skillId;
        List<Long> skillIdList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter developer id");
        developerId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter developer account id");
        accountId = scanner.nextLong();
        System.out.println("Enter developer skill id");
        skillId = scanner.nextLong();
        while (skillId >= 0){
            skillIdList.add(skillId);
            skillId = scanner.nextLong();
        }
        controller.save(developerId, accountId, skillIdList);
    }


    public void update() { //+
        Long developerId;
        Long updateAccountId;
        Long updateSkillId;
        List<Long> skillIdList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Developer id");
        developerId = scanner.nextLong();
        System.out.println("Enter new account id");
        updateAccountId = scanner.nextLong();
        System.out.println("Enter new skill id");
        updateSkillId = scanner.nextLong();
        while ( updateSkillId >= 0){
            skillIdList.add(updateSkillId);
            updateSkillId = scanner.nextLong();
        }
        controller.update(developerId, updateAccountId, skillIdList);
    }


    public void deleteById() { //+
        System.out.println("Enter developer id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        controller.deleteById(number);
    }
}

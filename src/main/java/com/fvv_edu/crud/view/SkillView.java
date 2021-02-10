package main.java.com.fvv_edu.crud.view;

import java.util.Scanner;
import main.java.com.fvv_edu.crud.controller.SkillController;
import main.java.com.fvv_edu.crud.model.Skill;

public class SkillView {
    SkillController controller = new SkillController();
    private Scanner scanner;

    private Long enterSkillId () {
        System.out.println("Enter skill id");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        Long id = scanner.nextLong();
        return id;
    }


    private String enterSkillName () {
        System.out.println("Enter skill name");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        String skillName = scanner.nextLine();
        return skillName;
    }


    public void getSkillById () { //+
            Skill skill = controller.getById(enterSkillId());
            System.out.println(skill);
    }

    public void getAll() {
        controller.getAll();
    }


    public void save() { //++
        Long id = enterSkillId();
        String name = enterSkillName();
        Skill newSkill = new Skill(id, name);
        controller.save(newSkill);
    }


    public void update() { //++
        Skill oldSkill;
        Long id;
        String updateName;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Skill id");
        id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter updateSkill name");
        updateName = scanner.nextLine();
        Skill updateSkill = new Skill(id, updateName);
        controller.update(id, updateSkill);
    }


    public void deleteById() { //++
        System.out.println("Enter skill id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        controller.deleteById(number);
    }
}

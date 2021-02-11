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


    public void save() { //+
        Long id = enterSkillId();
        String name = enterSkillName();
        Skill newSkill = new Skill(id, name);
        controller.save(newSkill);
    }


    public void update() { //+
        Long id = enterSkillId();
        String updateName = enterSkillName();
        Skill updateSkill = new Skill(id, updateName);
        controller.update(id, updateSkill);
    }


    public void deleteById() { //+
        controller.deleteById(enterSkillId());
    }
}

package view;

import java.util.Scanner;
import controller.SkillController;
import model.Skill;

public class SkillView {
    SkillController controller = new SkillController();

    public void getSkillById () { //++
        System.out.println("Enter skill id");
        try (Scanner scanner = new Scanner(System.in)){
            Long number = scanner.nextLong();
            Skill skill = controller.getById(number);
            System.out.println(skill);
        }
    }

    public void getAll() {
        controller.getAll();
    }


    public void save() { //++
        Long id;
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter skill id");
        id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter skill name");
        name = scanner.nextLine();
        Skill newSkill = new Skill(id, name);
        controller.save(newSkill);
    }


    public void update() { //++
        Skill oldSkill;
        Long id;
        String updateName;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter oldSkill id");
        id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter oldSkill name");
        String oldName = scanner.nextLine();
        oldSkill = new Skill(id, oldName);

        System.out.println("Enter updateSkill name");
        updateName = scanner.nextLine();
        Skill updateSkill = new Skill(id, updateName);
        controller.update(oldSkill, updateSkill);
    }


    public void deleteById() { //++
        System.out.println("Enter skill id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        controller.deleteById(number);
    }
}

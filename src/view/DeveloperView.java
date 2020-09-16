package view;

import controller.DeveloperController;
import model.Developer;

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
        Long id;
        String name;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter developer id");
        id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter developer name");
        name = scanner.nextLine();
        Developer newDeveloper = new Developer(id, name);
        controller.save(newDeveloper);
    }


    public void update() { //+
        Developer oldDeveloper;
        Long id;
        String updateName;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter oldDeveloper id");
        id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter oldDeveloper name");
        String oldName = scanner.nextLine();
        oldDeveloper = new Developer(id, oldName);

        System.out.println("Enter updateDeveloper name");
        updateName = scanner.nextLine();
        Developer updateDeveloper = new Developer(id, updateName);
        controller.update(oldDeveloper, updateDeveloper);
    }


    public void deleteById() { //+
        System.out.println("Enter developer id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        controller.deleteById(number);

    }
}

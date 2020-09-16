package view;

import controller.AccountController;
import model.Account;

import java.util.Scanner;

public class AccountView {
    AccountController controller = new AccountController();

    public void getAccountById () { //++
        System.out.println("Enter account id");
        Scanner scanner = new Scanner(System.in);
            Long number = scanner.nextLong();
            Account account = controller.getById(number);
            System.out.println(account);
    }

    public void getAll() {
        controller.getAll();
    }


    public void save() { //++
        Long id;
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id");
        id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter account name");
        name = scanner.nextLine();
        Account newAccount = new Account(id, name);
        controller.save(newAccount);
    }


    public void update() { //++
        Account oldAccount;
        Long id;
        String updateName;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter oldAccount id");
        id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter oldAccount name");
        String oldName = scanner.nextLine();
        oldAccount = new Account(id, oldName);

        System.out.println("Enter updateAccount name");
        updateName = scanner.nextLine();
        Account updateAccount = new Account(id, updateName);
        controller.update(oldAccount, updateAccount);
    }


    public void deleteById() { //++
        System.out.println("Enter account id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        controller.deleteById(number);
    }
}

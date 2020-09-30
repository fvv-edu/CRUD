package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.controller.AccountController;
import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.AccountStatus;

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
        Long accountId;
        String accountName;
        Long statusId;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id");
        accountId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter account name");
        accountName = scanner.nextLine();
        System.out.println("Enter account status");
        statusId = scanner.nextLong();
        controller.save(accountId, accountName, statusId);
    }


    public void update() { //++
        Long accountId;
        String updateName;
        Long updateStatusId;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id");
        accountId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter update account name");
        updateName = scanner.nextLine();
        System.out.println("Enter update account status");
        updateStatusId = scanner.nextLong();
        controller.update(accountId, updateName, updateStatusId);
    }


    public void deleteById() { //++
        System.out.println("Enter account id");
        Scanner scanner = new Scanner(System.in);
        Long number = scanner.nextLong();
        controller.deleteById(number);
    }
}

package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.controller.AccountController;
import main.java.com.fvv_edu.crud.model.Account;
import java.util.Scanner;

public class AccountView {
    AccountController controller = new AccountController();
    private Scanner scanner;


    private Long enterAccountId () {
        System.out.println("Enter account id");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        Long id = scanner.nextLong();
        return id;
    }


    private String enterAccountName () {
        System.out.println("Enter account name");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        String accountName = scanner.nextLine();
        return accountName;
    }


    private Long enterStatusId () {
        System.out.println("Enter account status id");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        Long statusId = scanner.nextLong();
        return statusId;
    }


    public void getAccountById() { //+
        Account account = controller.getById(enterAccountId());
        System.out.println(account);
    }


    public void getAll() {
        controller.getAll();
    }


    public void save() { //+
        Long accountId = enterAccountId();
        String accountName = enterAccountName();
        Long statusId = enterStatusId();
        controller.save(accountId, accountName, statusId);
    }


    public void update() { //+
        Long accountId = enterAccountId();
        String updateName = enterAccountName();
        Long updateStatusId = enterStatusId();
        controller.update(accountId, updateName, updateStatusId);
    }


    public void deleteById() { //+
        Long id = enterAccountId();
        controller.deleteById(id);
    }
}

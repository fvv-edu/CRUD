package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.controller.AccountController;
import main.java.com.fvv_edu.crud.model.Account;
import java.util.Scanner;

public class AccountView extends AbstractView implements GenericView {
    AccountController controller = new AccountController();
    private Scanner scanner;


    private Long enterAccountId () {
        System.out.println("Enter account id");
        return InternalScanner(1l);
    }


    private String enterAccountName () {
        System.out.println("Enter account name");
        return InternalScanner("");
    }


    private Long enterStatusId () {
        System.out.println("Enter account status id");
        return InternalScanner(1l);
    }


    public void getById() { //+
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

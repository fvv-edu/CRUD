package controller;

import model.Account;
import repository.AccountRepository;
import repository.io.JavaIOAccountRepositoryImpl;

import java.util.List;
import java.util.NoSuchElementException;

public class AccountController {
    private AccountRepository repo = new JavaIOAccountRepositoryImpl();

    public Account getById (Long id) { //++
        List<Account> accountList = getAllInternal();
        boolean result = false;
        try {
            if (id == null) {
                throw new NullPointerException();
            }
            if (id < 0) {
                throw new IllegalArgumentException();
            }
            for (Account x : accountList) {
                if (x.getId().equals(id)) {
                    result = true;
                    break;
                }else {
                    result = false;
                }
            }
            if (result == false) {
                throw new NoSuchElementException();
            }
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
        return repo.getById(id);
    }


    private List<Account> getAllInternal() {
        return repo.getAll();
    }


    public List<Account> getAll() { //+
        return repo.getAll();
    }


    public Account save(Account account) { //++
        List<Account> accountList = getAllInternal();
        Account result = null;
        try {
            if (accountList.contains(account)) {
                throw new IllegalArgumentException("This id or account is exist");
            }else if (account.equals(null)) {
                throw new NullPointerException();
            }else
                result = repo.save(account);
        }catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public Account update(Account account, Account updateAccount) { //++
        List<Account> accountList = getAllInternal();
        Account result = null;
        try {
            if (account.equals(null) || updateAccount.equals(null)) {
                throw new NullPointerException();
            }else if (!accountList.contains(account)) {
               throw new NoSuchElementException();
           } else {
                result = repo.update(account, updateAccount);
            }
        }catch (NullPointerException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public void deleteById(Long id) { //++
        List<Account> accountList = getAllInternal();
        boolean result = false;
        for (Account x : accountList) {
            if (x.getId().equals(id)) {
                result = true;
                break;
            }else {
                result = false;
            }
        }
        try {
            if (id == null) {
                throw new NullPointerException();
            }else if (id < 0) {
                throw new IllegalArgumentException();
            }else if (result == false) {
                throw new NoSuchElementException();
            }else {
                repo.deleteById(id);
            }
        }catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e);
        }
    }
}



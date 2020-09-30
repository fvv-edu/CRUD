package main.java.com.fvv_edu.crud.controller;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.AccountStatus;
import main.java.com.fvv_edu.crud.repository.AccountRepository;
import main.java.com.fvv_edu.crud.repository.io.JavaIOAccountRepositoryImpl;

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


    public Account save(Long accountId, String accountName, Long statusId) { //++
        List<Account> accountList = getAllInternal();
        AccountStatus status = AccountStatus.DELETED;
        try {
            if (statusId == 1) {
                status = AccountStatus.ACTIVE;
            }else if (statusId == 2) {
                status = AccountStatus.BANNED;
            }else if (statusId == 3){
                status = AccountStatus.DELETED;
            }else {
                throw new IllegalArgumentException("id must be more 0 and less 3");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
        Account newAccount = new Account(accountId, accountName, status);
        Account result = null;
        try {
            if (accountList.contains(newAccount)) {
                throw new IllegalArgumentException("This id or account is exist");
            }else if (newAccount.equals(null)) {
                throw new NullPointerException();
            }else
                result = repo.save(newAccount);
        }catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error: " + e);
        }
        return result;
    }


    public Account update(Long accountId, String updateName, Long updateStatusId) { //++
        List<Account> accountList = getAllInternal();
        AccountStatus status = AccountStatus.DELETED;
        try {
            if (updateStatusId == 1) {
                status = AccountStatus.ACTIVE;
            }else if (updateStatusId == 2) {
                status = AccountStatus.BANNED;
            }else if(updateStatusId == 3) {
                status = AccountStatus.DELETED;
            }else {
                throw new IllegalArgumentException("id must be more 0 and less 3");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }

        Account oldAccount = null;
        boolean isExistName = false;
        for (Account x : accountList) {
            if (x.getId().equals(accountId)) {
                oldAccount = x;
                continue;
            }else if (x.getName().equals(updateName)){
                isExistName = true;
            }
        }
        Account updateAccount = new Account(accountId, updateName, status);
        Account result = null;
        try {
            if (oldAccount.equals(null) || updateAccount.equals(null)) {
                throw new NullPointerException();
            }else if (!accountList.contains(oldAccount)) {
                throw new NoSuchElementException("This account not found");
            }else if (isExistName == true) {
                throw new IllegalArgumentException("This name account is exist");
            } else {
                result = repo.update(oldAccount, updateAccount);
            }
        }catch (NullPointerException | NoSuchElementException | IllegalArgumentException e) {
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



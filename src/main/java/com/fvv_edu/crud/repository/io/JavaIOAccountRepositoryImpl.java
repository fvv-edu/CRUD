package main.java.com.fvv_edu.crud.repository.io;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.AccountStatus;
import main.java.com.fvv_edu.crud.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    private List<Account> accounts;
    private String fileName = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\account.txt";

    public Account getById(Long id) { //+
        List<Account> accountList = getAllInternal();
        Account needAccount = null;
        for (Account x : accountList) {
            if (x.getId().equals(id)) {
                needAccount = x;
                break;
            }
        }
        return needAccount;
    }


    private List<Account> getAllInternal() { //+
        String fromSource;
        accounts = new ArrayList<>();
        AccountStatus status = AccountStatus.DELETED;
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((fromSource = br.readLine()) != null) {
                Long id = Long.valueOf(fromSource.substring(0,1));
                int stringStatusIndex = fromSource.indexOf(",", 3);
                String name = fromSource.substring(3, stringStatusIndex);
                Long statusId = Long.valueOf(fromSource.substring(stringStatusIndex+2));
                if (statusId == 1) {
                    status = AccountStatus.ACTIVE;
                }else if (statusId == 2) {
                    status = AccountStatus.BANNED;
                }else if (statusId == 3) {
                    status = AccountStatus.DELETED;
                }
                Account accountObj = new Account(id, name, status);
                accounts.add(accountObj);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return accounts;
    }


    public List<Account> getAll() { //+
        return getAllInternal();
    }


    private String convertForFile(Account account) { //+
        Long accountId;
        if (account.getStatus() == AccountStatus.ACTIVE) {
            accountId = 1l;
        }else if (account.getStatus() == AccountStatus.BANNED) {
            accountId = 2l;
        }else {
            accountId = 3l;
        }
        return (account.getId() + ". " + account.getName() + ", " + accountId + "\n" );
    }


    private Account saveInternal(Account account) { //+
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(convertForFile(account));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return account;
    }


    private Account saveInternal(Account account, int index) { //+
        List<Account> accountList = getAllInternal();
        accountList.set(index, account);
        Stream<String> newStream = accountList.stream().map((n) -> (convertForFile(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return account;
    }


    public Account save(Account account) {
        return saveInternal(account);
    }


    public Account update(Account account, Account updateAccount) { //+
        List<Account> accountList = getAllInternal();
            if (accountList.contains(account) == true) {
                int index = accountList.indexOf(account);
                saveInternal(updateAccount, index);
        }
        return updateAccount;
    }


    public void deleteById(Long id) { //+
        List<Account> accountList = getAllInternal();
        int needIndex = 0;
        for (Account x : accountList) {
            if (x.getId().equals(id)) {
                needIndex = accountList.indexOf(x);
            }
        }
        accountList.remove(needIndex);
        Stream<String> newStream = accountList.stream().map((n) -> (convertForFile(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)){
           for (String x : forFile) {
                fw.write(x);
           }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }


    public List<Account> getAccount() {
        return accounts;
    }
}




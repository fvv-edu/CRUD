package repository.io;

import model.Account;
import repository.AccountRepository;

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
        String anyAccount;
        accounts = new ArrayList<Account>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((anyAccount = br.readLine()) != null) {
                Account accountObj = new Account(anyAccount);
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
        return (account.getId() + ". " + account.getName() + "\n" );
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




package main.java.com.fvv_edu.crud.repository.io.json;

import com.google.gson.Gson;
import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonAccountRepositoryImpl implements AccountRepository {
    private String fileName = "C:\\IdeaProjects\\CRUD\\src\\main\\resources\\files\\json\\accounts.json";

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
        List accounts = new ArrayList<>();
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((fromSource = br.readLine()) != null) {
                Account account = gson.fromJson(fromSource, Account.class);
                accounts.add(account);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return accounts;
    }


    public List<Account> getAll() { //+
        return getAllInternal();
    }


    private String convertForJson(Account account) { //+
        Gson gson = new Gson();
        String jsonString = gson.toJson(account);
        return jsonString + "\n";
    }


    private Account saveInternal(Account account) { //+
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(convertForJson(account));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return account;
    }


    private Account saveInternal(Account account, int index) { //+
        List<Account> accountList = getAllInternal();
        accountList.set(index, account);
        Stream<String> newStream = accountList.stream().map((n) -> (convertForJson(n)));
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


    public Account save(Account account) { //+
        return saveInternal(account);
    }


    public Account update(Account oldAccount, Account updateAccount) { //+
        List<Account> accountList = getAllInternal();
        if (accountList.contains(oldAccount) == true) {
            int index = accountList.indexOf(oldAccount);
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
        Stream<String> newStream = accountList.stream().map((n) -> (convertForJson(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)){
            for (String x : forFile) {
                fw.write(x);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }
}

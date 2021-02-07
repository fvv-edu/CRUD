package main.java.com.fvv_edu.crud;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.AccountStatus;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.io.json.JsonDeveloperRepositoryImpl;
import main.java.com.fvv_edu.crud.view.CommonView;

public class ApplicationRunner {
    public static void main(String[] args) {
        /*CommonView cv = new CommonView();
        cv.dv.deleteById();*/
        JsonDeveloperRepositoryImpl json = new JsonDeveloperRepositoryImpl();
        Account a = new Account(1l, "Filatov", AccountStatus.BANNED);
        Skill s = new Skill(1l, "Java");
        Skill s2 = new Skill(2l, "C++");
        Developer d = new Developer(1l, a, s, s2);
        /*Account a1 = new Account(1l, "Filatov", AccountStatus.BANNED);
        Skill s3 = new Skill(1l, "Java");
        Skill s4 = new Skill(2l, "C++");
        Developer d1 = new Developer(2l, a1, s3, s4);*/
        //json.convertForJson(d);
        json.save(d);
        //json.saveInternal(d1);
    }
}

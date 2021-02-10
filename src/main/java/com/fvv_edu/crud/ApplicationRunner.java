package main.java.com.fvv_edu.crud;

import main.java.com.fvv_edu.crud.model.Account;
import main.java.com.fvv_edu.crud.model.AccountStatus;
import main.java.com.fvv_edu.crud.model.Developer;
import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.io.json.JsonAccountRepositoryImpl;
import main.java.com.fvv_edu.crud.repository.io.json.JsonDeveloperRepositoryImpl;
import main.java.com.fvv_edu.crud.view.CommonView;

import java.io.FileWriter;

public class ApplicationRunner {
    public static void main(String[] args) {
        CommonView cv = new CommonView();
        cv.dv.getDeveloperById();

    }
}

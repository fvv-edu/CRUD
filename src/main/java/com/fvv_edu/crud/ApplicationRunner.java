package main.java.com.fvv_edu.crud;

import main.java.com.fvv_edu.crud.model.Skill;
import main.java.com.fvv_edu.crud.repository.io.JavaIOSkillRepositoryImpl;
import main.java.com.fvv_edu.crud.view.CommonView;


import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        CommonView cv = new CommonView();
        JavaIOSkillRepositoryImpl repo = new JavaIOSkillRepositoryImpl();
        List <Skill> s = repo.getAll();
        System.out.println(repo.toString());

        cv.dv.save();
        cv.dv.save();
        cv.dv.save();
    }
}

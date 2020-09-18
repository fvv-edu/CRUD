package main.java.com.fvv_edu.crud.view;

public class CommonView {
    SkillView sv;
    public DeveloperView dv;
    AccountView av;

    public CommonView(){
        sv = new SkillView();
        dv = new DeveloperView();
        av = new AccountView();
    }
}

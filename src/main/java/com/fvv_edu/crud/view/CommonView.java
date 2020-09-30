package main.java.com.fvv_edu.crud.view;

public class CommonView {
    public SkillView sv;
    public DeveloperView dv;
    public AccountView av;

    public CommonView(){
        sv = new SkillView();
        dv = new DeveloperView();
        av = new AccountView();
    }
}

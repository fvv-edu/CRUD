package main.java.com.fvv_edu.crud.view;

public class CommonView {
    public GenericView gv;

    public CommonView(int i){
        if (i == 1) {
            setMode(new SkillView());
        } else if (i == 2) {
            setMode(new AccountView());
        } else {
            setMode(new DeveloperView());
        }

    }

    public void setMode(GenericView gv) {
        this.gv = gv;
    }


}

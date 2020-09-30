package main.java.com.fvv_edu.crud;

import main.java.com.fvv_edu.crud.view.CommonView;

public class ApplicationRunner {
    public static void main(String[] args) {
        CommonView cv = new CommonView();
        cv.dv.deleteById();
    }
}

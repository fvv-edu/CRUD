package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.model.GenericModel;

import java.util.Scanner;

public abstract class AbstractView <T extends GenericModel,ID extends Number> {

    private Scanner scanner;

    protected Long InternalScanner(Long l) {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLong();
    }

    protected String InternalScanner(String s) {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

}

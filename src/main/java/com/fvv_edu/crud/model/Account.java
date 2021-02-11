package main.java.com.fvv_edu.crud.model;

import java.util.Objects;

public class Account extends GenericModel{
    private Long id;
    private String name;
    private AccountStatus status;

    public Account(Long id, String name, AccountStatus status){
        this.id = id;
        this.name = name;
        this.status = status;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public AccountStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return id + ". " + name + ", " + status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) ||
                Objects.equals(name, account.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

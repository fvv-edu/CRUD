package model;

import java.util.Objects;

public class Account {
    private Long id;
    private String name;

    public Account(String fromSource) {
        id = Long.valueOf(fromSource.substring(0,1));
        name = fromSource.substring(3);
    }


    public Account(Long id, String name){
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return id + ". " + name;
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

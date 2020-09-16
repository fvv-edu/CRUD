package model;

import java.util.Objects;

public class Developer {
    private Long id;
    private String name;

    public Developer(String fromSource) {
        id = Long.valueOf(fromSource.substring(0,1));
        name = fromSource.substring(3);
    }


    public Developer(Long id, String name){
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
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id) ||
                Objects.equals(name, developer.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

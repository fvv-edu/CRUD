package main.java.com.fvv_edu.crud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Developer {
    private Long id;
    private List<Skill> skills;
    private Account account;


    public Developer(Long id, Account account, Skill...skill){
        this.id = id;
        this.account = account;
        skills = new ArrayList<>(skill.length);
        for (Skill x : skill) {
            skills.add(x);
        }
    }


    public Long getId() {
        return id;
    }


    public Account getAccount() {
        return account;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Developer{" + "id=" + id + ", account=" + account + ", skills=" + skills + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id) ||
                Objects.equals(skills, developer.skills) ||
                Objects.equals(account, developer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skills, account);
    }
}

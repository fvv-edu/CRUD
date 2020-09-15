import java.util.Objects;

public class Skill {
    private Long id;
    private String name;

    Skill (String fromSource) {
        id = Long.valueOf(fromSource.substring(0,1));
        name = fromSource.substring(3);
    }


    Skill (Long id, String name){
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
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) ||
                Objects.equals(name, skill.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

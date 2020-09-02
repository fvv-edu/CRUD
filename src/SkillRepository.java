import javax.xml.namespace.QName;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkillRepository {
    public List<Skill> skills;

    public Skill getById(Long id) { //+
        return skills.get(Math.toIntExact(id)-1);
    }

    public List<Skill> getAll() { //+
        String anySkill;
        skills = new ArrayList<Skill>();
        try (BufferedReader br = new BufferedReader(
                new FileReader("C:\\IdeaProjects\\CRUD\\skill.txt"))){
            while ((anySkill = br.readLine()) != null) {
                Skill skillObj = new Skill(anySkill);
                skills.add(skillObj);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skills;
    }

    public Skill save(Skill skill) { //+
        if (skills.contains(skill) == true) {
                System.out.println("This skill is exist");
            }else {
                skills.add(skill);
                Skill lastSkill = skills.get((skills.size()-1));
                String convertToString = ("\n" + lastSkill.getId() + ". " + lastSkill.getName());
                try (FileWriter fw = new FileWriter("C:\\IdeaProjects\\CRUD\\skill.txt", true)){
                    fw.write(convertToString);
                }catch (IOException e) {
                    System.out.println("Input/output Error: " + e);
                }
        }
        return skill;
    }

    public Skill update(Skill skill, Skill updateSkill) { //+
        if (skills.contains(skill) == true) {
            int index = skills.indexOf(skill);
            skills.set(index, updateSkill);
        }
        return updateSkill;
    }

    public void deleteById(Long id) { //+
        skills.remove(Math.toIntExact(id)-1);
        Stream<String> newStream = skills.stream().map((n) -> (n.getId() + ". " + n.getName() + "\n"));
        List<String> stringList = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter("C:\\IdeaProjects\\CRUD\\skill.txt")){
           for (String x : stringList) {
                fw.write(x);
           }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }
}

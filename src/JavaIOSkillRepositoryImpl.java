import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    private List<Skill> skills;
    private String fileName = "C:\\IdeaProjects\\CRUD\\skill.txt";

    public Skill getById(Long id) { //+
        List<Skill> skillList = getAllInternal();
        Skill needSkill = null;
        for (Skill x : skillList) {
            if (x.getId().equals(id)) {
                needSkill = x;
                break;
            }
        }
        return needSkill;
    }


    private List<Skill> getAllInternal() { //+
        String anySkill;
        skills = new ArrayList<Skill>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))){
            while ((anySkill = br.readLine()) != null) {
                Skill skillObj = new Skill(anySkill);
                skills.add(skillObj);
            }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skills;
    }


    public List<Skill> getAll() { //+
        return getAllInternal();
    }


    private String convertForFile(Skill skill) { //+
        return (skill.getId() + ". " + skill.getName() + "\n" );
    }


    private Skill saveInternal(Skill skill) { //+
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(convertForFile(skill));
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skill;
    }


    private Skill saveInternal(Skill skill, int index) { //+
        List<Skill> skillList = getAllInternal();
        skillList.set(index, skill);
        Stream<String> newStream = skillList.stream().map((n) -> (convertForFile(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String x : forFile) {
                fw.write(x);
            }
        } catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
        return skill;
    }


    public Skill save(Skill skill) {
        return saveInternal(skill);
    }


    public Skill update(Skill skill, Skill updateSkill) { //+
        List<Skill> skillList = getAllInternal();
            if (skillList.contains(skill) == true) {
                int index = skillList.indexOf(skill);
                saveInternal(updateSkill, index);
        }
        return updateSkill;
    }


    public void deleteById(Long id) { //+
        List<Skill> skillList = getAllInternal();
        int needIndex = 0;
        for (Skill x : skillList) {
            if (x.getId().equals(id)) {
                needIndex = skillList.indexOf(x);
            }
        }
        skillList.remove(needIndex);
        Stream<String> newStream = skillList.stream().map((n) -> (convertForFile(n)));
        List<String> forFile = newStream.collect(Collectors.toList());
        try (FileWriter fw = new FileWriter(fileName)){
           for (String x : forFile) {
                fw.write(x);
           }
        }catch (IOException e) {
            System.out.println("Input/output Error: " + e);
        }
    }


    public List<Skill> getSkills() {
        return skills;
    }
}




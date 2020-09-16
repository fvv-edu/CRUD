import repository.io.JavaIOSkillRepositoryImpl;
import view.SkillView;

public class SkillRunner {
    public static void main(String[] args) {
        JavaIOSkillRepositoryImpl sr = new JavaIOSkillRepositoryImpl();
        //Skill m = new Skill(4, "Maven");
        //Skill s = new Skill(2, "SQL");
        //Skill c = new Skill(2, "C++");
        //Skill sUpdate = new Skill(3, "UpdateSQL");
        //System.out.println(sr.getAll());
        //sr.save(c);
        //sr.deleteById(4l);
        //System.out.println(sr.getById(2l));
        //Skill sk = sr.update(s, sUpdate);
        //System.out.println(sk);
        //sr.getAll();
        SkillView sv = new SkillView();
        //sv.save();
        //sv.update();
        //sv.getSkillById();
        sv.deleteById();

    }
}

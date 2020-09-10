public class Runner {
    public static void main(String[] args) {
        SkillRepository sr = new SkillRepository();
        Skill m = new Skill(4, "Maven");
        Skill s = new Skill(2, "SQL");
        Skill c = new Skill(2, "C++");
        Skill sUpdate = new Skill(3, "UpdateSQL");
        //System.out.println(sr.getAll());
        //sr.save(c);
        //sr.deleteById(4l);
        //System.out.println(sr.getById(2l));
        Skill sk = sr.update(s, sUpdate);
        //System.out.println(sk);

    }
}

public class Runner {
    public static void main(String[] args) {
        SkillRepository sr = new SkillRepository();
        Skill m = new Skill(4, "Maven");
        Skill s = new Skill(2, "SQL");
        Skill sUpdate = new Skill(2, "UpdateSQL");
        sr.getAll();
        sr.save(m);
        System.out.println(sr.skills);
        //sr.deleteById(2l);
        System.out.println(sr.getById(2l));
        Skill sk = sr.update(s, sUpdate);
        System.out.println(sk);

    }
}

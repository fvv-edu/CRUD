import java.util.List;

public interface SkillRepository extends GenericRepository {
    Skill getById (Long id);
    List<Skill> getAll();
    Skill save(Skill skill);
    Skill update(Skill skill, Skill updateSkill);
    void deleteById(Long id);
}

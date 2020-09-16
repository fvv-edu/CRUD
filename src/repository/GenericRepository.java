package repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById (ID id);
    List<T> getAll();
    T save(T skill);
    T update(T skill, T updateSkill);
    void deleteById(ID id);
}

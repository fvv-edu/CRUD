package main.java.com.fvv_edu.crud.view;

import main.java.com.fvv_edu.crud.controller.DeveloperController;
import main.java.com.fvv_edu.crud.model.Developer;

import java.util.ArrayList;
import java.util.List;

public class DeveloperView extends AbstractView implements GenericView {
    DeveloperController controller = new DeveloperController();

    private Long enterDeveloperId () {
        System.out.println("Enter developer id");
        return InternalScanner(1l);
    }


    private Long enterAccountId () {
        System.out.println("Enter developer account id");
        return InternalScanner(1l);
    }


    private List enterSkillId () {
        List<Long> skillIdList = new ArrayList<>();
        System.out.println("Enter developer skill id");
        Long id = InternalScanner(1l);
        while (id >= 0){
            skillIdList.add(id);
            id = InternalScanner(1l);
        }
        return skillIdList;
    }


    public void getById() { //+
        Developer developer = controller.getById(enterDeveloperId());
        System.out.println(developer);
    }


    public void getAll() {
        controller.getAll();
    }


    public void save() { //+
        Long developerId = enterDeveloperId();
        Long accountId = enterAccountId();
        List<Long> skillIdList = enterSkillId();
        controller.save(developerId, accountId, skillIdList);
    }


    public void update() { //+
        Long developerId = enterDeveloperId();
        Long updateAccountId = enterAccountId();
        List<Long> skillIdList = enterSkillId();
        controller.update(developerId, updateAccountId, skillIdList);
    }


    public void deleteById() { //+
        Long id = enterDeveloperId();
        controller.deleteById(id);
    }
}

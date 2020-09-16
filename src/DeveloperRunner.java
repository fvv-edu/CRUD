import repository.io.JavaIODeveloperRepositoryImpl;
import view.DeveloperView;

public class DeveloperRunner {
    public static void main(String[] args) {
        JavaIODeveloperRepositoryImpl dr = new JavaIODeveloperRepositoryImpl();
        DeveloperView dv = new DeveloperView();
       /* dv.save();
        dv.getDeveloperById();
        dv.save();

        dv.getDeveloperById();
        */
        //dv.deleteById();
        dv.update();

    }
}

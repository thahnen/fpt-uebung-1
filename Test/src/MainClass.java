/**
 * Created by Conny on 21.05.2017.
 */
public class MainClass {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();
        controller.link(model, view);
    }

}

/**
 * Created by Conny on 21.05.2017.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener{

    private Model model;
    private View view;

    public Controller() {

    }

    /**
     * Reads the data from the view and adds it to the model.
     */
    public void actionPerformed(ActionEvent arg0) {
        model.addString(view.getInputPaneText());

    }

    public void link(Model m, View v){

        this.model = m;
        // register Model
        this.model.addObserver(v);

        //Linkt action listener
        this.view = v;
        view.addActionListener(this);

    }

}

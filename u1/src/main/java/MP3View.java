import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by thahnen on 15.05.17.
 * TODO : Irgendwie mit der View.fxml verbinden (hat ne eigene Borderpane?)
 *  => Hardcoden der Einzelteile ist möglich, aber weder fancy noch effektiv!
 * TODO : Implementierung nach MVC-Pattern
 */

public class MP3View extends BorderPane {
    private Label LblOrdN;
    private Label LblTitle;
    private Label LblInterp;
    private Label LblAlbum;
    private Label LblZeit;

    private TextField TxtTitel;
    private TextField TxtInterp;
    private TextField TxtAlbum;

    private ListView LstMP3;
    private ListView LstPl;

    private Stage stage;


    public MP3View() {
        this.stage = null;
    }

    public MP3View(Stage stage) throws Exception {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

import com.sun.corba.se.impl.naming.cosnaming.NamingContextDataStore;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class MP3Controller {
    @FXML private Label LblOrdN;
    @FXML private Label LblZeit;
    @FXML private TextField TxtTitel;
    @FXML private TextField TxtInterp;
    @FXML private TextField TxtAlbum;
    @FXML private ListView LstMP3;
    @FXML private ListView LstPl;

    @FXML protected void onBtnPlNeu(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnPlLad(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnPlLoe(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnOrdW(ActionEvent event) {
        // hier Quelltext einfügen
        System.out.println("click");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a file");
        chooser.setInitialDirectory(new File("C:\\"));
        File selectedFile = chooser.showOpenDialog(null);



    }

    @FXML protected void onBtnShin(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnSloe(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnMetaSp(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnSletzt(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnSstart(ActionEvent event) {
        // hier Quelltext einfügen
    }

    @FXML protected void onBtnSnaechs(ActionEvent event) {
        // hier Quelltext einfügen
    }
}

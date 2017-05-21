
import classes.SongClass;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;

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
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Wähle einen Ordner für MP3");
        chooser.setInitialDirectory(new File(""));
        File selectedFile = chooser.showDialog(null);
        File songs = selectedFile;
        File[] songListAr = songs.listFiles();
        ArrayList<SongClass> songListe = new ArrayList<SongClass>();

        for (int i=0; i<songListe.length; i++) {
            if(songListAr[i].getName().endsWith(".mp3")) {
                SongClass s = new SongClass(songListAr[i].getPath(), songListAr[i].getName());
                songListe.add(s);

            }
        }
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

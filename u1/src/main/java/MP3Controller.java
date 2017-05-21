import classes.Song;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        chooser.setTitle("Choose a file for MP3");
        chooser.setInitialDirectory(new File("C:\\"));
        File selectedFile = chooser.showDialog(null);
        System.out.println(selectedFile.getName());
        File songs = selectedFile;
        File[] songListAr = songs.listFiles();

        ArrayList<Song> songliste = new ArrayList<Song>();

        for (int i=0; i<songListAr.length; i++) {
            if (songListAr[i].getName().endsWith(".mp3")) {
                Song s = new Song(songListAr[i].getPath(), songListAr[i].getName());
                songliste.add(s);
                System.out.println(songliste.size());
            }
        }
        // name der LiestView LstMP3

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

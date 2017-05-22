import classes.SongClass;
import classes.SongListClass;
import interfaces.Song;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

/**
 *  DEN EINZELNEN GUI-ELEMENTEN Updater hinzufügen!
 *  UPDATE OnChange, den einezelnen Playlists weitere Event-Handler hinzufügen
 */

public class MP3Controller {
    @FXML private Label LblOrdN;
    @FXML private Label LblTitle;
    @FXML private Label LblInterp;
    @FXML private Label LblAlbum;
    @FXML private Label LblZeit;

    @FXML private TextField TxtTitel;
    @FXML private TextField TxtInterp;
    @FXML private TextField TxtAlbum;

    @FXML private ListView LstMP3;
    @FXML private ListView LstPl;

    private MP3Model model;
    private MP3View view;

    private SongClass aktiverSong;
    private MediaPlayer player;

    public MP3Controller() {
        this.model = new MP3Model();
        this.view = new MP3View();
    }

    @FXML protected void onBtnPlNeu(ActionEvent event) throws RemoteException {
        System.out.println(this.model); // gibt null zurück
        this.model.delPlaylist();
    }

    @FXML protected void onBtnPlLad(ActionEvent event) {
        System.out.println("Noch nicht implementiert");
    }

    @FXML protected void onBtnPlLoe(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnOrdW(ActionEvent event) throws Exception {
        DirectoryChooser chooser = new DirectoryChooser();
        File ordner;

        if (SystemUtils.IS_OS_MAC) {
            chooser.setInitialDirectory(new File(System.getenv("HOME")));
        } else {
            chooser.setInitialDirectory(new File(System.getenv("USERPROFILE")));
        }
        chooser.setTitle("Ordner wählen");

        ordner = chooser.showDialog(((Node) event.getSource()).getScene().getWindow());
        if (ordner == null) {
            throw new Exception("Fehler, keinen Ordner ausgewählt!");
        }

        this.LblOrdN.setText(ordner.toString());

        /* Logik hinzufügen um MP3-Dateien zu laden und anzuzeigen */
        SongListClass mp3s = new SongListClass();
        File[] inhalt = ordner.listFiles();

        for (File datei : inhalt) {
            if (datei.getName().endsWith(".mp3")) {
                mp3s.addSong(new SongClass(datei.getPath())); // hier schon Metadaten auslesen + unique ID erstellen (HASH?)
            }
        }

        this.model.setMp3dateien(mp3s);

        // View Updaten!
    }

    @FXML protected void onBtnShin(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnSloe(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnMetaSp(ActionEvent event) throws RemoteException{
        String album = this.TxtAlbum.getText();
        String interp = this.TxtAlbum.getText();
        String titel = this.TxtTitel.getText();

        ArrayList<Song> pl = this.model.getPlaylist().getList();
        int index = pl.indexOf(this.aktiverSong);
        pl.get(index).setTitle(titel);
        pl.get(index).setAlbum(album);
        pl.get(index).setInterpret(interp);
    }

    @FXML protected void onBtnSletzt(ActionEvent event) throws RemoteException{
        // alles null testen, abfangen!
        ArrayList<Song> pl = this.model.getPlaylist().getList();
        int index = pl.indexOf(this.aktiverSong);
        if (index >= pl.size()-1) {
            this.aktiverSong = (SongClass) pl.get(pl.indexOf(this.aktiverSong)-1);
            this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));
            this.player.play();
        } else {
            // Entweder auf letzten Song springen und spielen -> 1/2 oder aufhören
            // 1. Auf ersten Song setzen
            this.aktiverSong = (SongClass) pl.get(pl.size());
            // 2. dann weiterspielen
            this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));
            this.player.play();
        }
    }

    @FXML protected void onBtnSstart(ActionEvent event) throws RemoteException {
        if (this.aktiverSong != null && this.player != null) {
            MediaPlayer.Status status = this.player.getStatus();
            if (status == MediaPlayer.Status.PAUSED) {
                this.player.play();
            } else if (status == MediaPlayer.Status.PLAYING) {
                this.player.pause();
            } else if (status == MediaPlayer.Status.STOPPED) {
                // nächsten Song in Playlist spielen ausser Playlist hat nur ein Element, dann aufhören
                ArrayList<Song> pl = this.model.getPlaylist().getList(); // nicht so sondern über model aufrufen!
                this.aktiverSong = (SongClass) pl.get(pl.indexOf(this.aktiverSong)+1); //handlen Ende Liste!

                this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));
            }
        }
    }

    @FXML protected void onBtnSnaechs(ActionEvent event) throws RemoteException {
        // alles null testen, abfangen!
        ArrayList<Song> pl = this.model.getPlaylist().getList();
        int index = pl.indexOf(this.aktiverSong);
        if (index <= pl.size()-1) {
            this.aktiverSong = (SongClass) pl.get(pl.indexOf(this.aktiverSong)+1);
            this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));
            this.player.play();
        } else {
            // Am Ende entweder aufhören oder von Vorne beginnen -> 1/2 ?
            // 1. Auf ersten Song setzen
            this.aktiverSong = (SongClass) pl.get(0);
            // 2. dann weiterspielen
            this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));
            this.player.play();
        }
    }
}

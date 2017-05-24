import classes.SongClass;
import classes.SongListClass;
import interfaces.Song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class MP3Controller {

    /**     ==================================
     *      -*- INHALT DER KLASSE MP3Model -*-
     *      ==================================
     *
     *
     * Alle FXML-Elemente, die es gibt
     *  => später alles unnötige rauswerfen
     *
     * MP3Mode model, das alle Dateien beinhaltet
     *  => Aufruf von Methoden zur Änderung der Daten
     *
     * MP3View view, überflüssig aber ich lass das erstmal drinnen
     *  => falls FXML nicht geht und zusätzlich als Hardcode-Möglichkeit
     */

    @FXML private Label LblOrdN;
    @FXML private Label LblTitle;
    @FXML private Label LblInterp;
    @FXML private Label LblAlbum;
    @FXML private Label LblZeit;

    @FXML private TextField TxtTitel;
    @FXML private TextField TxtInterp;
    @FXML private TextField TxtAlbum;

    @FXML private ListView LstMp3;
    @FXML private ListView LstPl;

    private MP3Model model;
    private MP3View view;

    private SongClass aktiverSong;
    private MediaPlayer player;

    /* DEBUG */
    ObservableList<Song> songlist;
    /* END DEBUG */


    public MP3Controller() {
        this.model = new MP3Model();
        this.view = new MP3View();
    }


    /**
     * Handler onBtnPlNeu
     *  => löscht alle Elemente aus LstPl für eine neue
     */
    @FXML protected void onBtnPlNeu(ActionEvent event) throws RemoteException {
        System.out.println(this.model); // gibt null zurück
        this.model.delPlaylist();
    }

    /**
     * Handler onBtnPlLad
     *  => noch nicht implementiert (Fällt vlt weg)
     */
    @FXML protected void onBtnPlLad(ActionEvent event) {
        System.out.println("Noch nicht implementiert");
    }

    /**
     * Handler onBtnPlLoe
     *  => sollte wie Button PlNeu sein, wird zusammengepackt
     */
    @FXML protected void onBtnPlLoe(ActionEvent event) {
        System.out.println("Siehe Button PlNeu!");
    }

    /**
     * Handler onBtnOrdW (getriggert wenn man auf "Ordner auswählen" klickt)
     *  => Ordner auswählen mit den MP3-Dateien drin
     *  => Ordner als File ordner oder String ordnerPath im Model speichern
     *  => LstMp3 und LstPl anpassen und davon abhängige Variablen (alle)
     *  => View updaten mit den neusten Sachen
     */
    @FXML protected void onBtnOrdW(ActionEvent event) throws Exception {
        DirectoryChooser chooser = new DirectoryChooser();
        File ordner;

        if (SystemUtils.IS_OS_MAC) {
            chooser.setInitialDirectory(new File(System.getenv("HOME")));
        } else if (SystemUtils.IS_OS_WINDOWS) {
            chooser.setInitialDirectory(new File(System.getenv("USERPROFILE")));
        }
        chooser.setTitle("Ordner mit MP3-Dateien wählen");

        ordner = chooser.showDialog(((Node) event.getSource()).getScene().getWindow());
        if (ordner == null) { // z.B. wenn man auf "cancel" drückt
            // Handeln: Gar nichts tun? Bisher ist das nur Platzhalter!
            throw new Exception("Fehler, keinen Ordner ausgewählt!");
        }

        this.LblOrdN.setText(ordner.toString());

        SongListClass mp3s_ordner = new SongListClass();
        File[] inhalt = ordner.listFiles();

        for (File datei : inhalt) {
            if (datei.getName().endsWith(".mp3")) {
                mp3s_ordner.addSong(new SongClass(datei.getPath())); // hier schon Metadaten auslesen + unique ID erstellen (HASH?)
            }
        }

        // Gucken ob das weniger Aufwand ist ein Aufruf mit allen oder alle einzeln
        this.model.setMp3dateien(mp3s_ordner);

        // Nicht schön aber selten!
        // Dabei wird aus den Songs in der MP3-Liste nur der String .toString() benutzt

        this.songlist = FXCollections.observableList(this.model.getMp3dateien().getList());
        if (this.songlist == null) { throw new Exception("SongList ist empty"); }
        this.LstMp3.setItems(songlist);
    }

    /**
     * Funktion onBtnShin (getriggert wenn man auf den Button "Song hinzufügen" clickt)
     *  => fügt den per Mausklick ausgewählten Song aus der Mp3Lst in PlLst hinzu
     */
    @FXML protected void onBtnShin(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    /**
     * Funktion onBtnSloe (getriggert wenn man auf den Button "Song löschen" klickt)
     *  => löscht den per Mausklick ausgewählten Song aus PlLst
     */
    @FXML protected void onBtnSloe(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    /**
     * Funktion onBtnMetaSp (getriggert wenn man auf den Button "Metadaten speichern" klickt)
     *  => speichert die (geänderten) Metadaten des in der PlLst ausgewählten Songs
     */
    @FXML protected void onBtnMetaSp(ActionEvent event) throws RemoteException{
        // ÜBERSRBEITEN ! auf null prüfen
        String album = this.TxtAlbum.getText();
        String interp = this.TxtAlbum.getText();
        String titel = this.TxtTitel.getText();

        ArrayList<Song> pl = this.model.getPlaylist().getList();
        int index = pl.indexOf(this.aktiverSong);
        pl.get(index).setTitle(titel);
        pl.get(index).setAlbum(album);
        pl.get(index).setInterpret(interp);
    }

    /**
     * Funktion onBtnSletzt (getriggert wenn man auf den "Letzten Song abspielen" klickt)
     *  => setzt den aktiven Song auf den vorherigen UND
     *      => spielt ab wenn der momentane läuft,
     *      => setzt den nur (oder nochmal auf Pause) wenn der andere pausiert,
     *      => spielt ab oder setzt auf Pause wenn momentaner beendet ist (?) [sollte eigentlich nie passieren]
     */
    @FXML protected void onBtnSletzt(ActionEvent event) throws RemoteException{

        if ((this.aktiverSong != null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            ArrayList<Song> playlist = this.model.getPlaylist().getList(); // vlt überarbeiten -.-
            int index = playlist.indexOf(this.aktiverSong);
            MediaPlayer.Status altersong_status = player.getStatus();

            if (index > 0) {
                this.aktiverSong = (SongClass) playlist.get(index-1);
            } else {
                this.aktiverSong = (SongClass) playlist.get(playlist.size()-1);
            }

            this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));

            if ((altersong_status == MediaPlayer.Status.PLAYING)
                    || (altersong_status == MediaPlayer.Status.HALTED)) {
                this.player.play();
            }

        } else if ((this.aktiverSong != null) && (this.model.getPlaylist().sizeOfList() == 0)) {
            // das sollte nicht passieren, ist eigentlich gar nicht möglich
            // kann höchstens passieren wenn die Pl gelöscht wird aber noch ein Song spielt (?)
            this.aktiverSong = null;
        } else if ((this.aktiverSong == null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            // sollte eigentlich nicht passieren aber igel
            // einfach aktiverSong auf den letzten aus der Playlist setzen
            ArrayList<Song> playlist = this.model.getPlaylist().getList();
            this.aktiverSong = (SongClass) playlist.get(playlist.size()-1);
        }
    }

    /**
     * Funktion onBtnSstart (getriggert wenn man auf "Song abspielen/ pausieren" klickt)
     *  => spielt den aktuellen Song ab (wenn vorhanden)
     *  => setzt auf Pause wenn aktueller Song gerade läuft
     *  => startet von vorne, wenn aktueller Song fertig ist? [sollte nicht da Autoplay nächster]
     */
    @FXML protected void onBtnSstart(ActionEvent event) throws RemoteException {
        // wie bei den anderen auf die anderen beiden Fälle prüfen!
        if (this.aktiverSong != null && this.player != null) {
            MediaPlayer.Status status = this.player.getStatus();
            if (status == MediaPlayer.Status.PAUSED) {
                this.player.play();
            } else if (status == MediaPlayer.Status.PLAYING) {
                this.player.pause();
            } else if (status == MediaPlayer.Status.STOPPED) {
                // nächsten Song in Playlist spielen ausser Playlist hat nur ein Element, dann aufhören
                ArrayList<Song> pl = this.model.getPlaylist().getList();
                this.aktiverSong = (SongClass) pl.get(pl.indexOf(this.aktiverSong)+1); //handlen Ende Liste!

                this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));
            }
        }
    }

    /**
     * Funktion onBtnSnaechs (getriggert wenn man auf den "Nächsten Song abspielen" klickt)
     *  => setzt den aktiven Song auf den nächsten UND
     *      => spielt ab wenn der momentane läuft,
     *      => setzt den nur (oder nochmal auf Pause) wenn der andere pausiert,
     *      => spielt ab oder setzt auf Pause wenn momentaner beendet ist (?) [sollte eigentlich nie passieren]
     */
    @FXML protected void onBtnSnaechs(ActionEvent event) throws RemoteException {

        if ((this.aktiverSong != null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            ArrayList<Song> playlist = this.model.getPlaylist().getList(); // vlt überarbeiten -.-
            int index = playlist.indexOf(this.aktiverSong);
            MediaPlayer.Status altersong_status = player.getStatus();

            if (index <= playlist.size()-1) {
                this.aktiverSong = (SongClass) playlist.get(index+1);
            } else {
                this.aktiverSong = (SongClass) playlist.get(0);
            }

            this.player = new MediaPlayer(new Media(this.aktiverSong.getPath()));

            if ((altersong_status == MediaPlayer.Status.PLAYING)
                    || (altersong_status == MediaPlayer.Status.HALTED)) {
                this.player.play();
            }

        } else if ((this.aktiverSong != null) && (this.model.getPlaylist().sizeOfList() == 0)) {
            // das sollte nicht passieren, ist eigentlich gar nicht möglich
            // kann höchstens passieren wenn die Pl gelöscht wird aber noch ein Song spielt (?)
            this.aktiverSong = null;
        } else if ((this.aktiverSong == null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            // sollte eigentlich nicht passieren aber igel
            // einfach aktiverSong auf den ersten aus der Playlist setzen
            this.aktiverSong = (SongClass) this.model.getPlaylist().getList().get(0);
        }
    }

    /**
     * Handler onMp3MsPressed (getriggert, wenn man in die ListView Mp3List klickt)
     *  => setzt ausgewählten Song Mp3 auf den, auf den geklickt wurde
     *      => wenn daneben geklickt/ ListView leer nix machen (?)
     */
    @FXML protected void onMp3MsPressed(ActionEvent event) {

    }

    /**
     * Handler onPlMsPressed (getriggert, wenn man in die ListView Mp3List klickt)
     *  => setzt ausgewählten Song Pl auf den, auf den geklickt wurde
     *      => wenn daneben geklickt/ ListView leer nix machen (?)
     */
    @FXML protected void onPlMsPressed(ActionEvent event) {

    }
}

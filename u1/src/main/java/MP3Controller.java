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
import javafx.scene.input.MouseEvent;
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


    public MP3Controller() {
        this.model = new MP3Model();
    }


    /**
     * Handler onBtnShinAll
     *  => Fügt alle Elemente der Mp3Lst zu PlLst hinzu!
     */
    @FXML protected void onBtnShinAll(ActionEvent event) throws RemoteException {
        this.model.setPlaylist(this.model.getMp3dateien());

        for (Song song : this.model.getPlaylist().getList()) {
            this.LstPl.getItems().add(song);
        }

        this.model.setAuswahlMp3Song(null);
    }

    /**
     * Handler onBtnPlLoe
     *  => löscht die Playlist
     */
    @FXML protected void onBtnPlLoe(ActionEvent event)  throws RemoteException{
        this.model.setPlaylist(null);
        // Wenn nicht null, einfach auf null setzen
        this.model.setAuswahlPlSong(null);
        this.model.setMomentanerSong(null);
        this.LstPl.getItems().remove(0, this.LstPl.getItems().size());
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
        if (ordner != null) {

            if (this.model.getPlayer() != null) {
                this.model.getPlayer().stop();
            }
            this.model = new MP3Model();
            this.LstMp3.setItems(FXCollections.observableList(new ArrayList<Song>()));
            this.LstPl.setItems(FXCollections.observableList(new ArrayList<Song>()));

            this.LblOrdN.setText(ordner.toString());

            SongListClass mp3s_ordner = new SongListClass();
            File[] inhalt = ordner.listFiles();

            for (File datei : inhalt) {
                if (datei.getName().endsWith(".mp3")) {
                    mp3s_ordner.addSong(new SongClass(datei.getPath())); // hier schon Metadaten auslesen + unique ID erstellen (HASH?)
                }
            }

            this.model.setMp3dateien(mp3s_ordner);

            // Nicht schön aber selten!
            // Dabei wird aus den Songs in der MP3-Liste nur der String .toString() benutzt

            ObservableList<Song> songlist = FXCollections.observableList(this.model.getMp3dateien().getList());
            if (songlist == null) { throw new Exception("SongList ist empty"); }
            this.LstMp3.setItems(songlist);
        }
    }

    /**
     * Funktion onBtnShin (getriggert wenn man auf den Button "Song hinzufügen" clickt)
     *  => fügt den per Mausklick ausgewählten Song aus der Mp3Lst in PlLst hinzu
     *  => danach wird die Auswahl (Mauscklick in ListView) gelöscht
     */
    @FXML protected void onBtnShin(ActionEvent event) throws RemoteException {
        // auf Null testen -> dann nix machen => kann man noch schöner/ effizienter machen
        //  => dafür fehlt mir die Zeit
        // testen auf doppelte, die nicht hinzufügen!
        if ((this.model.getAuswahlMp3Song() != null) && (this.model.getPlaylist() != null)) {
            this.model.getPlaylist().addSong(this.model.getAuswahlMp3Song());

            if (this.LstPl.getItems() == null) {
                this.LstMp3.setItems(FXCollections.observableList(new ArrayList<Song>()));
            }
            this.LstPl.getItems().add(this.model.getAuswahlMp3Song());

            this.model.setAuswahlMp3Song(null);
            this.LstMp3.getSelectionModel().clearSelection();
        } else if ((this.model.getAuswahlMp3Song() != null) && (this.model.getPlaylist() == null)) {
            this.model.setPlaylist(new SongListClass());
            this.model.getPlaylist().addSong(this.model.getAuswahlMp3Song());

            if (this.LstPl.getItems() == null) {
                this.LstMp3.setItems(FXCollections.observableList(new ArrayList<Song>()));
            }
            this.LstPl.getItems().add(this.model.getAuswahlMp3Song());

            this.model.setAuswahlMp3Song(null);
            this.LstMp3.getSelectionModel().clearSelection();
        } else if ((this.model.getAuswahlMp3Song() == null) && (this.model.getPlaylist() != null)) {
            // kann eigentlich nicht passieren
        }
    }

    /**
     * Funktion onBtnSloe (getriggert wenn man auf den Button "Song löschen" klickt)
     *  => löscht den per Mausklick ausgewählten Song aus PlLst
     */
    @FXML protected void onBtnSloe(ActionEvent event) throws RemoteException {
        // auf Null testen
        // falls es momentanerSong ist umändern auf nächsten, wenn am ende auf den ersten setzen
        if (this.model.getAuswahlPlSong() != null) {
            this.model.getPlaylist().deleteSong(this.model.getAuswahlPlSong());
            this.LstPl.getItems().remove(this.model.getAuswahlPlSong());

            this.model.setAuswahlPlSong(null);
            this.LstPl.getSelectionModel().clearSelection();

            // View Meta-Daten updaten
            this.TxtTitel.setText("");
            this.TxtInterp.setText("");
            this.TxtAlbum.setText("");
        }
    }

    /**
     * Funktion onBtnMetaSp (getriggert wenn man auf den Button "Metadaten speichern" klickt)
     *  => speichert die (geänderten) Metadaten des in der PlLst ausgewählten Songs
     */
    @FXML protected void onBtnMetaSp(ActionEvent event) throws RemoteException{
        String album = this.TxtAlbum.getText();
        String interp = this.TxtAlbum.getText();
        String titel = this.TxtTitel.getText();

        if ((this.model.getPlaylist() != null) && (this.model.getPlaylist().sizeOfList() != 0)) {
            ArrayList<Song> pl = this.model.getPlaylist().getList();
            int index = pl.indexOf(this.model.getMomentanerSong());
            pl.get(index+1).setTitle(titel);
            pl.get(index+1).setAlbum(album);
            pl.get(index+1).setInterpret(interp);
        }
    }

    /**
     * Funktion onBtnSletzt (getriggert wenn man auf den "Letzten Song abspielen" klickt)
     *  => setzt den aktiven Song auf den vorherigen UND
     *      => spielt ab wenn der momentane läuft,
     *      => setzt den nur (oder nochmal auf Pause) wenn der andere pausiert,
     *      => spielt ab oder setzt auf Pause wenn momentaner beendet ist (?) [sollte eigentlich nie passieren]
     */
    @FXML protected void onBtnSletzt(ActionEvent event) throws RemoteException{

        // Prüfen ob der Player null ist!
        // quick and dirty da Player keine Musik abspielen will -.-
        if (this.model.getPlayer() == null) {
            return;
        }

        if ((this.model.getMomentanerSong() != null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            ArrayList<Song> playlist = this.model.getPlaylist().getList(); // vlt überarbeiten -.-
            int index = playlist.indexOf(this.model.getMomentanerSong());
            MediaPlayer.Status altersong_status = this.model.getPlayer().getStatus();

            if (index > 0) {
                this.model.setMomentanerSong((SongClass) playlist.get(index-1));
            } else {
                this.model.setMomentanerSong((SongClass) playlist.get(playlist.size()-1));
            }

            this.model.getPlayer().stop();
            this.model.setPlayer(new MediaPlayer(new Media(
                    (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
            )));

            if ((altersong_status == MediaPlayer.Status.PLAYING)
                    || (altersong_status == MediaPlayer.Status.HALTED)) {
                this.model.getPlayer().play();
            }

        } else if ((this.model.getMomentanerSong() != null) && (this.model.getPlaylist().sizeOfList() == 0)) {
            // das sollte nicht passieren, ist eigentlich gar nicht möglich
            // kann höchstens passieren wenn die Pl gelöscht wird aber noch ein Song spielt (?)
            this.model.setMomentanerSong(null);
        } else if ((this.model.getMomentanerSong() == null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            // sollte eigentlich nicht passieren aber igel
            // einfach aktiverSong auf den letzten aus der Playlist setzen
            ArrayList<Song> playlist = this.model.getPlaylist().getList();
            this.model.setMomentanerSong((SongClass) playlist.get(playlist.size()-1));
        }
    }

    /**
     * Funktion onBtnSstart (getriggert wenn man auf "Song abspielen/ pausieren" klickt)
     *  => spielt den aktuellen Song ab (wenn vorhanden)
     *  => setzt auf Pause wenn aktueller Song gerade läuft
     *  => startet von vorne, wenn aktueller Song fertig ist? [sollte nicht da Autoplay nächster]
     */
    @FXML protected void onBtnSstart(ActionEvent event) throws RemoteException {

        if (this.model.getMomentanerSong() != null && this.model.getPlayer() != null) {
            //System.out.println("Fall 1");
            MediaPlayer.Status status = this.model.getPlayer().getStatus();

            if (status == MediaPlayer.Status.PAUSED) {
                if (this.model.getMomentanerSong() == this.model.getAuswahlPlSong()) {
                    this.model.getPlayer().play();
                } else {
                    this.model.setMomentanerSong(this.model.getAuswahlPlSong());
                    this.model.getPlayer().stop();
                    this.model.setPlayer(new MediaPlayer(new Media(
                            (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
                    )));
                    this.onEndOfSongOfMediaPlayer();
                    this.model.getPlayer().play();
                }
            } else if (status == MediaPlayer.Status.PLAYING) {
                if (this.model.getMomentanerSong() == this.model.getAuswahlPlSong()) {
                    this.model.getPlayer().pause();
                } else {
                    this.model.setMomentanerSong(this.model.getAuswahlPlSong());
                    this.model.getPlayer().stop();
                    this.model.setPlayer(new MediaPlayer(new Media(
                            (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
                    )));
                    this.onEndOfSongOfMediaPlayer();
                    this.model.getPlayer().play();
                }
            } else if (status == MediaPlayer.Status.STOPPED) {
                // kann/ sollte eigentlich nie eintreten
                // nächsten Song in Playlist spielen ausser Playlist hat nur ein Element, dann aufhören

                // DA DER HANDLER NICHT KLAPPT IST DAS HIER NOCH NICHT IMPLEMENTIERT1
                this.model.setMomentanerSong(this.model.getMomentanerSong());
                this.model.setPlayer(new MediaPlayer(new Media(
                        (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
                )));
                this.model.getPlayer().play();
            } else {
                // Irgendein anderer Status ist da

                this.model.setMomentanerSong(this.model.getMomentanerSong());
                this.model.setPlayer(new MediaPlayer(new Media(
                        (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
                )));
                this.model.getPlayer().play();
            }
        } else if (this.model.getMomentanerSong() != null && this.model.getPlayer() == null) {
            //System.out.println("Fall 2");

            this.model.setPlayer(new MediaPlayer(new Media(
                    (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
            )));
            //this.onEndOfSongOfMediaPlayer();
            this.model.getPlayer().play();
        } else if (this.model.getMomentanerSong() == null && this.model.getPlayer() != null) {
            //System.out.println("Fall 3");
            // einfach nix machen
        } else if ((this.model.getMomentanerSong() == null) && (this.model.getPlayer() == null)) {
            //System.out.println("Fall 4");

            if (this.model.getAuswahlPlSong() == null) {
                return;
            }

            this.model.setMomentanerSong(this.model.getAuswahlPlSong());
            this.model.setPlayer(new MediaPlayer(new Media(
                    (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
            )));
            //this.onEndOfSongOfMediaPlayer();
            this.model.getPlayer().play();
        }
        this.model.setAuswahlPlSong(null);
        this.LstPl.getSelectionModel().clearSelection();
    }

    /**
     * Funktion onBtnSnaechs (getriggert wenn man auf den "Nächsten Song abspielen" klickt)
     *  => setzt den aktiven Song auf den nächsten UND
     *      => spielt ab wenn der momentane läuft,
     *      => setzt den nur (oder nochmal auf Pause) wenn der andere pausiert,
     *      => spielt ab oder setzt auf Pause wenn momentaner beendet ist (?) [sollte eigentlich nie passieren]
     */
    @FXML protected void onBtnSnaechs(ActionEvent event) throws RemoteException {

        // Prüfen ob der Player null ist!
        // quick and dirty da Player keine Musik abspielen will -.-
        if (this.model.getPlayer() == null) {
            return;
        }

        if ((this.model.getMomentanerSong() != null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            ArrayList<Song> playlist = this.model.getPlaylist().getList(); // vlt überarbeiten -.-
            int index = playlist.indexOf(this.model.getMomentanerSong());
            MediaPlayer.Status altersong_status = this.model.getPlayer().getStatus();

            if (index+1 < playlist.size()) {
                this.model.setMomentanerSong((SongClass) playlist.get(index+1));
            } else {
                this.model.setMomentanerSong((SongClass) playlist.get(0));
            }

            this.model.getPlayer().stop();
            this.model.setPlayer(new MediaPlayer(new Media(
                    (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
            )));
            //this.onEndOfSongOfMediaPlayer();


            if ((altersong_status == MediaPlayer.Status.PLAYING)
                    || (altersong_status == MediaPlayer.Status.HALTED)) {
                this.model.getPlayer().play();
            }

        } else if ((this.model.getMomentanerSong() != null) && (this.model.getPlaylist().sizeOfList() == 0)) {
            // das sollte nicht passieren, ist eigentlich gar nicht möglich
            // kann höchstens passieren wenn die Pl gelöscht wird aber noch ein Song spielt (?)
            this.model.setMomentanerSong(null);
        } else if ((this.model.getMomentanerSong() == null) && (this.model.getPlaylist().sizeOfList() > 0)) {
            // sollte eigentlich nicht passieren aber igel
            // einfach aktiverSong auf den ersten aus der Playlist setzen
            this.model.setMomentanerSong((SongClass) this.model.getPlaylist().getList().get(0));
        }
    }

    /**
     * Handler onMp3MsPressed (getriggert, wenn man in die ListView Mp3List klickt)
     *  => setzt ausgewählten Song Mp3 auf den, auf den geklickt wurde
     *      => wenn daneben geklickt/ ListView leer nix machen (?)
     *  !!! Funktion wird nicht aufgerufen wenn man reinklickt !!!
     */
    @FXML protected void onMp3MsPressed(MouseEvent event) throws RemoteException {
        SongClass ausgewaehlter_song =  (SongClass) this.LstMp3.getFocusModel().getFocusedItem();

        if (ausgewaehlter_song != null) {
            this.model.setAuswahlMp3Song(ausgewaehlter_song);
        }
    }

    /**
     * Handler onPlMsPressed (getriggert, wenn man in die ListView Mp3List klickt)
     *  => setzt ausgewählten Song Pl auf den, auf den geklickt wurde
     *      => wenn daneben geklickt/ ListView leer nix machen (?)
     *  !!! Funktion wird nicht aufgerufen wenn man reinklickt !!!
     */
    @FXML protected void onPlMsPressed(MouseEvent event) {
        SongClass ausgewaehlter_song =  (SongClass) this.LstPl.getFocusModel().getFocusedItem();

        if (ausgewaehlter_song != null) {
            this.model.setAuswahlPlSong(ausgewaehlter_song);

            this.TxtAlbum.setText(ausgewaehlter_song.getAlbum());
            this.TxtInterp.setText(ausgewaehlter_song.getInterpret());
            this.TxtTitel.setText(ausgewaehlter_song.getTitle());
        }
    }

    /**
     * Versuch Implementierung Handler für wenn Song zu Ende sodass nächster gespielt wird
     *  => klappt leider nicht!
     */
    private void onEndOfSongOfMediaPlayer() throws RemoteException {
        this.model.getPlayer().setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                    playNextSong();
                } catch (RemoteException e) {

                }
            }
        });
    }

    private void playNextSong() throws RemoteException {
        if (this.model.getPlaylist() == null) {
            return;
        } else if ((this.model.getPlaylist() != null) && (this.model.getMomentanerSong() != null)) {
            if (this.model.getPlaylist().getList().indexOf(this.model.getMomentanerSong()) <
                    this.model.getPlaylist().getList().size()) {
                this.model.setMomentanerSong((SongClass) this.model.getPlaylist().getList().get(
                        this.model.getPlaylist().getList().indexOf(this.model.getMomentanerSong())
                ));
                this.model.getPlayer().stop();
                this.model.setPlayer(new MediaPlayer(new Media(
                        (new File(this.model.getMomentanerSong().getPath())).toURI().toString()
                )));
            }
        }
    }
}

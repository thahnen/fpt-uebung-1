import classes.SongListClass;

import java.io.File;
import java.rmi.RemoteException;


public class MP3Model {

    /**     =====================================
     *      -*- VARIABLEN DER KLASSE MP3Model -*-
     *      =====================================
     *
     *
     * File ordner ODER String ordnerpath, ausgewählter Ordner
     *  => bei File ordner: mögliches Update der SongListClass mp3dateien?
     *      => Abfrage alle paar mal Eventloop-Durchlauf? Falls möglich.
     *  => bei String ordnerPath: kein Update der SongListClass mp3dateien
     *      => nur Speicherung Ordnerpfad für das Label, Update bei Änderung
     *
     * SongListClass mp3dateien, mit allen MP3-Dateien in Ordner
     *  => wird nicht geupdatet bei Hinzufügen während Programm offen
     *  => Soll Observable gemacht werden? Für Autoupdate der View?
     *
     * SongListClass playlist, mit allen MP3-Dateien in der Playlist
     *  => Soll Observable gemacht werden? Für Autoupdate der View?
     *
     * Item in ListView LstMp3, auf das geklickt wurde
     *  => wenn keins, dann null, wenn in LstMp3 getippt wurde update
     *
     * Item in ListView LstPl, auf das geklickt wurde
     *  => wenn keins, dann null, wenn in LstPl getippt wurde update
     *
     * Song(Class) [je nachdem was <ListView>.getSelectionModel().getSelectedItem() zurückgibt] auswahlMp3Song,
     *  das per Mausclick ausgewählte Item in LstMp3 (Handeln wenn Liste wenig Items hat!)
     *  => um das Item in die LstPl zu packen
     *  => ändert sich immer, wenn man was anderes anclickt
     *  => ändert sich, wenn man neuen Ordner auswählt usw.
     *
     * Song(Class) [je nachdem was <ListView>.getSelectionModel().getSelectedItem() zurückgibt] auswahlPlSong,
     *  das per Mausclick ausgewählte Item in LstMp3 (Handeln wenn Liste wenig Items hat!)
     *  => um das Item aus LstPl zu löschen, abzuspielen etc.
     *  => ändert sich immer, wenn man was anderes anclickt
     *  => ändert sich, wenn man die Playlist löscht, das Item aus der Playlist löscht usw.
     *
     * Song(Class) momentanerSong, der Song der gerade im Player ist, also aktiv
     *  => ändert sich, wenn man anderen Song auswählt (=> auswahlPlSong) UND abgespielt
     *  => ändert sich, wenn momentanerSong durchgelaufen automatisch auf nächsten gewechselt
     *  => ändert sich, wenn Button BtnSletzt/ BtnSnaechs gedrückt wurde
     *
     * MediaPlayer player, dafür da um darüber Songs abzuspielen,
     *  => Handler für Knöpfe BtnSstart, BtnSletzt, BtnSnaechs
     */

    private File ordner;
    private SongListClass mp3dateien;
    private SongListClass playlist;

    public MP3Model() {
        this.ordner = null;
        this.mp3dateien = new SongListClass();
        this.playlist = new SongListClass();
    }

    public File getOrdner() { return this.ordner; }
    public void setOrdner(File ordner) {
        this.ordner = ordner;
    }

    public SongListClass getMp3dateien() { return this.mp3dateien; }
    public void setMp3dateien(SongListClass dateien) throws RemoteException{
        this.mp3dateien = dateien;

        for (int i = 0; i < this.mp3dateien.sizeOfList(); i++) {
            System.out.println(this.mp3dateien.getList().get(i));
        }
    }
    public void delMp3dateien() throws RemoteException{
        this.mp3dateien.deleteAllSongs();
    }

    public SongListClass getPlaylist() { return this.playlist; }
    public void setPlaylist(SongListClass playlist) {
        this.playlist = playlist;
    }
    public void delPlaylist() throws RemoteException {
        this.playlist.deleteAllSongs();
    }
}

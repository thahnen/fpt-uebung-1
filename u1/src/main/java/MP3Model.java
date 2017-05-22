import classes.SongListClass;
import interfaces.Song;

import java.io.File;
import java.rmi.RemoteException;

/**
 * Created by thahnen on 15.05.17.
 * TODO : Implementierung nach MVC-Pattern
 */

public class MP3Model {

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

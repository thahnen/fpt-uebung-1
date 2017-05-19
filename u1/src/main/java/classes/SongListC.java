package classes;

import interfaces.Song;
import interfaces.SongList;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by thahnen on 10.05.17.
 */
public class SongListC implements SongList {

    private ArrayList<Song> elem;

    public SongListC() {

        this.elem = new ArrayList<Song>();
    }

    public SongListC(ArrayList<Song> nelem) {
        this.elem = new ArrayList<Song>();
        if (!this.elem.addAll(nelem)) {
            // Fehlerbehandlung
        }
    }

    public boolean addSong(Song s) throws RemoteException {
        this.elem.add(s);
        return true;
    }

    public boolean deleteSong(Song s) throws RemoteException {
        if (this.elem.contains(s)) {
            this.elem.remove(s);
            return true;
        }
        return false;
    }

    public void setList(ArrayList<Song> s) throws RemoteException {
        this.elem = s;
    }

    public ArrayList<Song> getList() throws RemoteException {
        return this.elem;
    }

    public void deleteAllSongs() throws RemoteException {
        elem.clear();

    }

    public int sizeOfList() throws RemoteException {
        return elem.size();
    }

    public Song findSongByPath(String name) throws RemoteException {
        // Platzhalter
        return new SongClass();
    }

    @Override
    public Iterator<Song> iterator() {      // ich raff die Scheisse hier erstmal nicht -.-
        return null;                   // guck mal bitte einer ob er/ sie das rafft  --ich glaube das ist für die liste der lieder die angezeigt werden sollen -Marco
    }

}

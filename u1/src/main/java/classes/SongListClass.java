package classes;

import interfaces.Song;
import interfaces.SongList;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;


public class SongListClass implements SongList {

    /**     =====================================
     *      -*- VARIABLEN DER KLASSE MP3Model -*-
     *      =====================================
     *
     *
     * ArrayList<Song> elem, speichert die Songs intern in einem
     */

    private ArrayList<Song> elem;


    public SongListClass() {
        this.elem = new ArrayList<Song>();
    }

    public SongListClass(ArrayList<Song> nelem) throws Exception{
        this.elem = new ArrayList<Song>();
        if (!this.elem.addAll(nelem)) {
            // Fehlerbehandlung
            throw new Exception("Fehler in der DV");
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

    /**
     * Schaut nach, ob Song im Array ist anhand der Path-Property
     */
    public Song findSongByPath(String name) throws RemoteException {
        for ( Song song : this.elem) {
            if (song.getPath() == name) {
                return this.elem.get(this.elem.indexOf(song));
            }
        }
        return null;
    }

    @Override
    public Iterator<Song> iterator() {
        return null;
    }

}

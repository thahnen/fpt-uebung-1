package classes;

import interfaces.Song;
import javafx.beans.value.ObservableValue;

/**
 * Created by thahnen on 10.05.17.
 */
public class SongClass implements Song {

    private String path;
    private long id;
    private String album;
    private String interpret;
    private String title;


    public SongClass() {
    }

    public SongClass(String path, long id, String album, String interpret, String title) {
        this.path = path;
        this.id = id;
        this.album = album;
        this.interpret = interpret;
        this.title = title;
        //bitte löschen
    }


    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getInterpret() {
        return this.interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ObservableValue<String> pathProperty() {
        // Platzhalter
        return (ObservableValue<String>) new Object();
    }

    public ObservableValue<String> albumProperty() {
        // Platzhalter
        return (ObservableValue<String>) new Object();
    }

    public ObservableValue<String> interpretProperty() {
        // Platzhalter
        return (ObservableValue<String>) new Object();
    }

    public String toString() {
        return (this.title + " von " + this.interpret + " aus dem Album " + this.album);
    }
}
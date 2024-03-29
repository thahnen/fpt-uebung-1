package classes;

import interfaces.Song;
import javafx.beans.value.ObservableValue;
import org.apache.commons.lang3.SystemUtils;


public class SongClass implements Song {

    /**     =====================================
     *      -*- VARIABLEN DER KLASSE MP3Model -*-
     *      =====================================
     *
     *
     * String path, speichert den (ganzen) Dateipfad zu der MP3-Date
     *  => bei File ordner: mögliches Update der SongListClass mp3dateien?
     *      => Abfrage alle paar mal Eventloop-Durchlauf? Falls möglich.
     *  => bei String ordnerPath: kein Update der SongListClass mp3dateien
     *      => nur Speicherung Ordnerpfad für das Label, Update bei Änderung
     *
     * long id, speichert ID des Songs
     *  => generiert durch einen MD5-hash (?), sodass der unique ist
     *      => Updaten wenn man die Metadaten ändert
     *  => SPÄTER IMPLEMENTIEREN
     *
     * String album, speichert Album-Metadata (erst) in Klasseninstanz
     *  => wird durch "Metadaten Speichern"-Knopf geändert
     *      => später in Datei speichern
     *      => SPÄTER IMPLEMENTIEREN
     *
     * String interpret, speichert Interpreten-Metadata (erst) in Klasseninstanz
     *  => wird durch "Metadaten Speichern"-Knopf geändert
     *      => später in Datei speichern
     *      => SPÄTER IMPLEMENTIEREN
     *
     * String title, speichert Titel-Metadata (erst) in Klasseninstanz
     *  => wird durch "Metadaten Speichern"-Knopf geändert
     *      => später in Datei speichern
     *      => SPÄTER IMPLEMENTIEREN
     */

    private String path;
    private long id;
    private String album;
    private String interpret;
    private String title;


    /**
     * Konstruktoren für SongClass
     *  => 1. nur Dateipfad mitgeben, den Rest auf null oder äquivalenten Wert setzen (ID generieren ?)
     *  => 2. nur Dateipfad und ID (wird aus Dateipfad generiert)
     *      => NOCH NICHT IMPLEMENTIERT
     *  => 3. alles ausser  ID (wird hier erstmal auf null gesetzt? oder generiert)
     *      => NOCH NICHT IMPLEMENTIERT
     *  => 4. alles angegeben (auch ID, wie bei den anderen)
     *      => NOCH NICHT IMPLEMENTIERT
     */
    public SongClass(String path) { this.path = path; } // verändern, alles ander auf null!

    public SongClass(String path, long id) { this.path = path; this.id = id; }

    public SongClass(String path, String album, String interpret, String title) { // verändern, alles ander auf null!
        this.path = path;
        this.album = album;
        this.interpret = interpret;
        this.title = title;
    }

    public SongClass(String path, long id, String album, String interpret, String title) { // verändern, alles ander auf null!
        this.path = path;
        this.id = id;
        this.album = album;
        this.interpret = interpret;
        this.title = title;
    }


    public String getAlbum() { return this.album; }
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

    /**
     * SPÄTER IMPLEMENTIEREN ODER FALLLEN LASSEN
     */
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

    /**
     * Wird von der ListView aufgerufen
     *  => bisher wird der letzte Teil des Dateipfads genutzt (macOS / UNIX)
     *  => sollte der Dateiname sein
     */
    public String toString() {
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_UNIX) {
            String[] pfad_teile = this.path.split("/");
            return (pfad_teile[pfad_teile.length-1]);
        }/* else if (SystemUtils.IS_OS_WINDOWS) {
            // Hab kein Windows, hier vlt noch Windows handlen ;)
        }*/
        return this.path;
    }
}
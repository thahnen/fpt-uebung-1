import classes.SongClass;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by thahnen on 15.05.17.
 * TODO : Implementierung nach MVC-Pattern
 */

public class MP3Model {

    private File ordner;
    private ArrayList<SongClass> songsInOrd = new ArrayList<>();

    public MP3Model() {
        this.ordner = null;
    }

    public File getOrdner() { return this.ordner; }

    public void setOrdner(File ordner) {
        this.ordner = ordner;
    }
}

import classes.SongClass;
import classes.SongListC;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainP3Player extends Application {

    @Override
    public void start(Stage stage) throws Exception {
/*
        //muss noch verschoben werden
        //alle dateien im resource folder einem array hinzufügen
        File liederImResourcesFolder = new File("\\\\C:\\Users\\Marco\\Documents\\GitHub\\fpt-uebung-1\\u1\\src\\main\\resourcesresources");
        File[] liederListe = liederImResourcesFolder.listFiles();

        //mp3 dateien filtern und einem neuen array hinzufügen
        List<SongListC> songliste = new ArrayList<SongListC>();

        for (int i=0; i<liederListe.length; i++) {
           if (liederListe[i].getName().endsWith(".mp3")) {
              System.out.println(liederListe[i].toString());      // .getName für dateiname .toString für ort #debugging

              //weiß nicht wo z.B. interpret herbekommen soll
               SongClass s = new SongClass(liederListe[i].getAbsolutePath(), (long) 5L, null, null, liederListe[i].getName());

           }
        }*/

        Parent root = FXMLLoader.load(getClass().getResource("fpt-uebung-1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
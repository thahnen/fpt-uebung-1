import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

public class MP3Controller {

    private MP3Model model;
    private MP3View view;

    public MP3Controller() {
        this.model = null;
        this.view = null;
    }

    public void link(MP3Model model, MP3View view) {
        this.model = model;
        this.view = view;
    }

    @FXML protected void onBtnPlNeu(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnPlLad(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnPlLoe(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnOrdW(ActionEvent event) throws Exception {
        Node node = (Node) event.getSource();
        DirectoryChooser chooser = new DirectoryChooser();
        File ordner;

        if (SystemUtils.IS_OS_MAC) { // auf Windoof oder Unix testen!
            chooser.setInitialDirectory(new File(System.getenv("HOME")));
        } else {
            chooser.setInitialDirectory(new File(System.getenv("USERPROFILE")));
        }
        chooser.setTitle("Ordner wählen");

        ordner = chooser.showDialog(node.getScene().getWindow());
        if (ordner == null) { // klappt irgendwie nicht mit setOrdner
            //this.model.setOrdner(ordner);
            throw new Exception("Fehler, keinen Ordner ausgewählt!");
        }
        System.out.println(ordner);


    }

    @FXML protected void onBtnShin(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnSloe(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnMetaSp(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnSletzt(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnSstart(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }

    @FXML protected void onBtnSnaechs(ActionEvent event) {
        System.out.println("Btn Gedrückt!");
    }
}

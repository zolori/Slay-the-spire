package view;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Manager;
import model.Monstre;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import model.Manager;
import model.Salle;

public class VueSalle /*implements Initializable*/ {
    private GridPane terrain= new GridPane();
    private Label lbl;

    public void bonus(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Bonus.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Bonus");
        stage.setScene(new Scene(p, 600, 250));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void defaite(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Defaite.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Defaite");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        stage.setFullScreen(true);
    }

    public void initialize(/*URL url, ResourceBundle resourceBundle*/) {
//        Manager.salleActuelle.getlisteMonstre().addListener(new ListChangeListener<Monstre>()) {
//            @Override
//            public void onChanged(Change<? extends Monstre> c) {
//                int i=0;
//                c.next();
//                for (Monstre monstre: c.getAddedSubList()){
//                    i++;
//                    ImageView monstreAAfficher= new ImageView();
//                    monstreAAfficher.setImage(new Image(getClass().getResource("/Images/Orcs.jpg").toExternalForm()));
//                    switch(i) {
//                        case 1:
//                            terrain.add(monstreAAfficher, 0, 0);
//                        case 2:
//                            terrain.add(monstreAAfficher, 1, 0);
//                        case 3:
//                            terrain.add(monstreAAfficher, 0, 1);
//                        case 4:
//                            terrain.add(monstreAAfficher, 1, 1);
//                    }
//                }
//            }
//        });
    }
}

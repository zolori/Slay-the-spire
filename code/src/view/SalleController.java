package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;
import java.awt.*;
import java.io.IOException;

public class SalleController {
    private GridPane terrain= new GridPane();
    private Label lbl;
    private Joueur joueur;
    @FXML
    private ListView<Carte> deckListView;
    private ObservableList<Carte> deck = FXCollections.observableArrayList();

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

    @FXML public void handleMouseClick(MouseEvent arg0) {
        int selectedCarteIndex = deckListView.getSelectionModel().getSelectedIndex();
        this.joueur.remplaceDeckCarte(selectedCarteIndex);
    }

    public void setJoueur(Joueur j) {
        this.joueur = j;
        initDeck();
    }

    public void initDeck() {
        deckListView.setItems(this.joueur.getDeck());
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

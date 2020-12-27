package view;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import model.*;
import java.io.IOException;

public class SalleController {
    Manager leManager = Manager.getInstance();
    Joueur joueur = leManager.getJoueur();

    @FXML
    private GridPane terrain;
    @FXML
    private VBox ptsdevie;
    @FXML
    private ImageView hero;
    @FXML
    private Text vie;
    @FXML
    private Text vieMonstre;
    @FXML
    private ImageView monstre;
    @FXML
    private Text vietot;
    @FXML
    private ListView<Carte> deckListView;
    private ObservableList<Carte> deck = FXCollections.observableArrayList();
    Carte selectedItem;

    public void defaite(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Defaite.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Defaite");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        stage.setFullScreen(true);
    }

    @FXML public void handleMouseClick(MouseEvent arg0) throws IOException {
        int selectedCarteIndex = deckListView.getSelectionModel().getSelectedIndex();
        selectedItem = deckListView.getSelectionModel().getSelectedItem();
        useCard(selectedItem);
        this.joueur.remplaceDeckCarte(selectedCarteIndex);
    }


    public void useCard(Carte selectedItem) throws IOException {
        boolean changeSalle = false;

        switch (selectedItem.getNom()) {
            case "Attaque":
                changeSalle = joueur.attaque(leManager.getSalle().getMonstre(), selectedItem.getValeur());
                break;
            case "Soin":
                changeSalle = joueur.soigne(selectedItem.getValeur());
                break;
            case "Poison":
                changeSalle = joueur.empoisonnement(leManager.getSalle().getMonstre(), selectedItem);
                break;
            default :
                break;
        }
        if (joueur.getPointsDeVie() <= 0)
            leManager.getPartie().finPartie();
        else if (changeSalle){
            leManager.getSalle().changerSalle();
        }
    }

    public void initialize() {
        leManager.createSalle(1);
        deckListView.setItems(this.joueur.getDeck());

        hero.setImage(new Image(getClass().getResource(joueur.getImage()).toExternalForm()));
        hero.setFitHeight(100);
        hero.setFitWidth(100);
        vie.textProperty().bindBidirectional(joueur.pdvProperty(),new NumberStringConverter());
        vietot.textProperty().bindBidirectional(joueur.pdvMaxProperty(),new NumberStringConverter());

        Monstre monstres = leManager.getSalle().getMonstre();
        monstre.setImage(new Image(getClass().getResource(monstres.getImage()).toExternalForm()));
        monstre.setFitHeight(150);
        monstre.setFitWidth(150);
        vieMonstre.textProperty().bindBidirectional(monstres.pointsDeVieProperty(),new NumberStringConverter());
    }
}

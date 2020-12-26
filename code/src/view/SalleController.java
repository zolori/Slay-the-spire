package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

public class SalleController {
    private Manager leManager= new Manager();
    private Joueur joueur;
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
    private Label lbl;
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
        leManager.useCard(deckListView.getSelectionModel().getSelectedItem());
        this.joueur.remplaceDeckCarte(selectedCarteIndex);
    }

    public void setJoueur(String nom, int pdv, int pa,int nbcartes) {
        this.joueur = new Joueur(nom,pdv,pa,nbcartes);
        initDeck();
    }

    public void setManager(Manager leManager) {
        this.leManager=leManager;
    }

    public void initDeck() {
        deckListView.setItems(this.joueur.getDeck());
    }

    public void initialize() {
        leManager.createSalle(1);
        Joueur joueur= leManager.getJoueur();
        hero.setImage(new Image(getClass().getResource(joueur.getImage()).toExternalForm()));
        hero.setFitHeight(100);
        hero.setFitWidth(100);
        vie.textProperty().bindBidirectional(joueur.pdvProperty(),new NumberStringConverter());
        vietot.textProperty().bindBidirectional(joueur.pdvMaxProperty(),new NumberStringConverter());

        Monstre monstres = leManager.getSalleActuelle().getMonstre();
        monstre.setImage(new Image(getClass().getResource(monstres.getImage()).toExternalForm()));
        monstre.setFitHeight(150);
        monstre.setFitWidth(150);
        vieMonstre.textProperty().bindBidirectional(monstres.pointsDeVieProperty(),new NumberStringConverter());
    }


    public Manager getManager() {
        return leManager;
    }
}

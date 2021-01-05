package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import model.*;

import java.io.*;

public class SalleController {
    Manager leManager = Manager.getInstance();
    Joueur joueur = leManager.getPartie().getJoueur();
    Salle salleActuelle = leManager.getPartie().getSalle(joueur.getNumSalle());

    @FXML
    private GridPane terrain;
    @FXML
    private VBox ptsdevie;
    @FXML
    private ImageView heroImageView;
    @FXML
    private Text vie;
    @FXML
    private Text vieMonstre;
    @FXML
    private ImageView monstreImageView;
    @FXML
    private Text vietot;
    @FXML
    private ListView<Carte> deckListView;
    Carte selectedItem;

    public void loadPartie (Joueur j, Salle s){
        joueur=j;
        salleActuelle=s;
        leManager.getPartie().setJoueur(j);
        leManager.getPartie().setSalle(s);
        bind();

    }

    private void bind(){
        deckListView.setItems(this.joueur.getDeck());

        heroImageView.setImage(new Image(getClass().getResource(joueur.getImage()).toExternalForm()));
        vie.textProperty().bindBidirectional(joueur.pdvProperty(), new NumberStringConverter());
        vietot.textProperty().bindBidirectional(joueur.pdvMaxProperty(), new NumberStringConverter());

        Monstre monstre = salleActuelle.getMonstre();
        monstreImageView.setImage(new Image(getClass().getResource(monstre.getImage()).toExternalForm()));
        vieMonstre.textProperty().bindBidirectional(monstre.pointsDeVieProperty(), new NumberStringConverter());

    }

    public void defaite() throws IOException {
        leManager.getPartie().finPartie();
    }

    @FXML public void handleMouseClick(MouseEvent arg0) throws IOException {
        int selectedCarteIndex = deckListView.getSelectionModel().getSelectedIndex();
        selectedItem = deckListView.getSelectionModel().getSelectedItem();
        useCard(selectedItem);
        this.joueur.remplaceDeckCarte(selectedCarteIndex);
    }


    public void useCard(Carte selectedItem) throws IOException {
        boolean changeSalle = false;

        if(salleActuelle.getMonstre().isEnattaque()){
            deckListView.setDisable(true);
            Timeline delai = new Timeline(
                    new KeyFrame(Duration.seconds(2), event -> {

                    })
            );
            delai.play();
            deckListView.setDisable(false);

        }

        switch (selectedItem.getNom()) {
            case "Attaque":
                changeSalle = joueur.attaque(salleActuelle.getMonstre(), selectedItem.getValeur());
                break;
            case "Soin":
                changeSalle = joueur.soigne(selectedItem.getValeur(), salleActuelle.getMonstre());
                break;
            case "Poison":
                changeSalle = joueur.empoisonnement(salleActuelle.getMonstre(), selectedItem);
                break;
            default :
                break;
        }

        if (joueur.getPointsDeVie() <= 0)
            leManager.getPartie().finPartie();
        else if (changeSalle){
            joueur.setNumSalle(salleActuelle.getNumSalle() + 1);
            salleActuelle.changerSalle();
            deckListView.getScene().getWindow().hide();
        }
    }

    public void initialize() {
        bind();
    }

    public void sauvegarde(ActionEvent actionEvent) throws IOException {
        FileOutputStream sauvegarde = new FileOutputStream("Partie.ser");
        ObjectOutputStream oos = new ObjectOutputStream(sauvegarde);
        Manager manager=Manager.getInstance();
        Partie partie=manager.getPartie();
        partie.getJoueur().serialisation(oos);
        partie.getSalle(partie.getJoueur().getNumSalle()).serialisation(oos);
        partie.getSalle(partie.getJoueur().getNumSalle()).getMonstre().serialisation(oos);

        oos.close();

        Parent p = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}

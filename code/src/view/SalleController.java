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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import model.*;
import java.io.*;

/**
 * SalleController est la classe qui permet de controler l'affichage de ce qui est présent dans la vue Salle et les actions faites sur cette dernière
 */

public class SalleController {
    /**
     * Manager du jeu
     */
    Manager leManager = Manager.getInstance();
    /**
     * Le joueur actuel
     */
    Joueur joueur = leManager.getPartie().getJoueur();
    /**
     * La salle actuelle dans laquelle est le joueur
     */
    Salle salleActuelle = leManager.getPartie().getSalle(joueur.getNumSalle());

    /**
     * Afficheur de l'image du héros
     */
    @FXML
    private ImageView heroImageView;

    /**
     * Bloc de texte qui affiche les points de vie actuelle du joueur.
     */
    @FXML
    private Text vie;

    /**
     * Bloc de texte qui affiche les points de vie actuelle du monstre.
     */
    @FXML
    private Text vieMonstre;

    /**
     * Afficheur de l'image du monstre
     */
    @FXML
    private ImageView monstreImageView;

    /**
     * Bloc de texte qui affiche les points de vie totaux du joueur.
     */
    @FXML
    private Text vietot;

    /**
     * Afficheur des cartes que le joueur a en main.
     */
    @FXML
    private ListView<Carte> deckListView;

    /**
     * Carte sélectionnée par l'utilisateur.
     */
    private Carte selectedItem;

    /**
     * Cette fonction sert à mettre en place une partie
     * @param j : joueur actuelle
     * @param s : Salle actuelle du joueur
     */
    public void loadPartie (Joueur j, Salle s){
        joueur = j;
        salleActuelle=s;
        leManager.getPartie().setJoueur(j);
        leManager.getPartie().setSalle(s);
        bind();
    }

    /**
     * Cette fonction met en place tout les bindings de la salle
     */
    private void bind(){
        deckListView.setItems(this.joueur.getDeck());

        heroImageView.setImage(new Image(getClass().getResource(joueur.getImage()).toExternalForm()));
        vie.textProperty().bindBidirectional(joueur.pdvProperty(), new NumberStringConverter());
        vietot.textProperty().bindBidirectional(joueur.pdvMaxProperty(), new NumberStringConverter());

        Monstre monstre = salleActuelle.getMonstre();
        monstreImageView.setImage(new Image(getClass().getResource(monstre.getImage()).toExternalForm()));
        vieMonstre.textProperty().bindBidirectional(monstre.pointsDeVieProperty(), new NumberStringConverter());
    }

    /**
     * Cette fonction gère le cas de la défaite du joueur en affichant la page de mort.
     * @throws IOException
     */
    public void defaite() throws IOException {
        deckListView.getScene().getWindow().hide();
        leManager.getPartie().finPartie();
    }

    /**
     * Cette fonction gère le click sur les cartes afin qu'elles soient jouées.
     * @throws IOException
     */
    @FXML public void handleMouseClick() throws IOException {
        int selectedCarteIndex = deckListView.getSelectionModel().getSelectedIndex();
        selectedItem = deckListView.getSelectionModel().getSelectedItem();
        useCard(selectedItem);
        this.joueur.remplaceDeckCarte(selectedCarteIndex);
    }

    /**
     * Cette fonction sert à gérer l'utilisation d'une carte.
     * @param selectedItem : Carte sélectionnée par le joueur
     * @throws IOException
     */
    public void useCard(Carte selectedItem) throws IOException {
        boolean changeSalle = false;
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
        desactiveClick(true);
        Timeline delai = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> desactiveClick(false)));
        delai.play();
        if (joueur.getPointsDeVie() <= 0) {
            deckListView.getScene().getWindow().hide();
            defaite();
        }
        else if (changeSalle){
            joueur.setNumSalle(salleActuelle.getNumSalle() + 1);
            salleActuelle.changerSalle();
            deckListView.getScene().getWindow().hide();
        }
    }

    /**
     * fonction se lançant au démarrage de la fenêtre
     */
    public void initialize() {
        bind();
    }

    /**
     * Cette fonction s'occupe de la sauvegarde de la partie dans son état actuel afin de la restaurer ultérieurement
     * @param actionEvent : clique du joueur sur le bouton sauvegarder
     * @throws IOException
     */
    public void sauvegarde(ActionEvent actionEvent) throws IOException {
        File f = new File("Partie.ser");
        if(f.exists()){
            f.delete();
        }
        FileOutputStream sauvegarde = new FileOutputStream("Partie.ser");
        ObjectOutputStream oos = new ObjectOutputStream(sauvegarde);
        Manager manager = Manager.getInstance();
        manager.serialiser(oos);

        oos.close();

        Parent p = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        stage.setFullScreen(true);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    /**
     * Cette fonction gère l'affichage des cartes pour que, dans le cas ou c'est au monstre de jouer, le joueur doit attendre son tour.
     * @param i : booleen qui prévient si oui ou non on désactive l'affichage des cartes.
     */
    private void desactiveClick(boolean i){
        deckListView.setDisable(i);
    }
}

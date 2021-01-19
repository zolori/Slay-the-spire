package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

import model.*;

/**
 * BonusController est la classe qui permet de gérer l'affichage et les actions faites sur la vue Bonus
 */
public class BonusController {
    /**
     * Manager du jeu
     */
    Manager leManager = Manager.getInstance();

    /**
     * Le joueur actuel
     */
    Joueur j = leManager.getPartie().getJoueur();

    /**
     * La salle actuelle dans laquelle est le joueur
     */
    Salle salleActuelle = leManager.getPartie().getSalle(j.getNumSalle());

    /**
     * Cette fonction s'occupe du cas ou le joueur à choisis d'augmenter ses points de vie maximum.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Lancer une partie'
     * @throws IOException
     */
    public void VieMax(ActionEvent actionEvent) throws IOException {
        j.renforcer(Bonus.VieMax, salleActuelle);
        retourAccueil(actionEvent);
    }

    /**
     * Cette fonction s'occupe du cas ou le joueur à choisis de se soigner.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Lancer une partie'
     * @throws IOException
     */
    public void Regen(ActionEvent actionEvent) throws IOException{
        j.renforcer(Bonus.Regeneration, salleActuelle);
        retourAccueil(actionEvent);
    }

    /**
     * Cette fonction s'occupe du cas ou le joueur à choisis d'augmenter ses dégâts.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Lancer une partie'
     * @throws IOException
     */
    public void Degats(ActionEvent actionEvent) throws IOException{
        j.renforcer(Bonus.Degats, salleActuelle);
        reinitDeck(3);
        retourAccueil(actionEvent);
    }

    /**
     * Cette fonction s'occupe de changer les cartes du joueur lorsqu'il change de salle.
     * @param nb_cartes : nombre de cartes du joueur
     */
    public void reinitDeck(int nb_cartes) {
        j.getDeck().removeAll();
        for (int i = 0; i < nb_cartes; i++) {
            j.remplaceDeckCarte(i);
        }
    }

    /**
     * Cette fonction s'occupe de nous mettre dans la salle suivante après avoir chosis un bonus.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'Lancer une partie'
     * @throws IOException
     */
    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Salle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 1000, 700));
        stage.setFullScreen(true);
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}

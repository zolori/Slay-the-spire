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

public class BonusController /*implements Initializable*/{
    Manager leManager = Manager.getInstance();
    Joueur j = leManager.getPartie().getJoueur();
    Salle salleActuelle = leManager.getPartie().getSalle(j.getNumSalle());

    public void initialize() {}

    public void VieMax(ActionEvent actionEvent) throws IOException {
        j.renforcer(Bonus.VieMax, salleActuelle);
        retourAccueil(actionEvent);
    }

    public void Regen(ActionEvent actionEvent) throws IOException{
        j.renforcer(Bonus.Regeneration, salleActuelle);
        retourAccueil(actionEvent);
    }

    public void Degats(ActionEvent actionEvent) throws IOException{
        j.renforcer(Bonus.Degats, salleActuelle);
        reinitDeck(3);
        retourAccueil(actionEvent);
    }

    public void reinitDeck(int nb_cartes) {
        j.getDeck().removeAll();
        for (int i = 0; i < nb_cartes; i++) {
            j.remplaceDeckCarte(i);
        }
    }

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

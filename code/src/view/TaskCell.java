package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Carte;
import java.io.IOException;

/**
 * TaskCell est la classe qui permet de charger les cartes de façon aléatoire
 */
public class TaskCell extends ListCell<Carte> {
    /**
     * Afficheur de l'image de la carte
     */
    @FXML
    private ImageView image;

    /**
     * Zone de texte dans laquelle on affiche le nom de la carte
     */
    @FXML
    private Label titre;

    /**
     * Zone de texte dans laquelle on affiche les efets de la carte
     */
    @FXML
    private Label effet;

    /**
     * Constructeur de TaskCell
     */
    public TaskCell() {
        loadFXML();
    }

    /**
     * Fonction qui permet de charger la nouvelle carte afin de l'afficher
     */
    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Task_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette fonction permet de gérer la mise a jour des cartes une fois qu'elles sont utilisées.
     * @param carte : Carte à changer
     * @param empty : booleen qui dit si il y a quelque chose à afficher
     */
    @Override
    protected void updateItem(Carte carte, boolean empty) {
        super.updateItem(carte, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            Image img = new Image(carte.getImageUrl());
            image.setImage(img);
            titre.setText(carte.getNom());
            effet.setText(carte.getValAffichage());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

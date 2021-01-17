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

public class TaskCell extends ListCell<Carte> {

    @FXML
    private ImageView image;

    @FXML
    private Label titre;

    @FXML
    private Label effet;

    public TaskCell() {
        loadFXML();
    }

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

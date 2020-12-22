package view;

import com.sun.glass.ui.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Carte;
import model.Effets;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class VueCarte {

    public javafx.scene.control.Label nomCarte;

    private Carte carte = new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1);

    public void initialize() {
        nomCarte.textProperty().bind(carte.NomProperty());
    }

}


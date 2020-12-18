package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class Cartes implements Initializable {
    private Text nomCarte;

    private StringProperty NomCarte= new SimpleStringProperty("");
    public String getTextNom(){return NomCarte.get();}
    public StringProperty NomCarteProperty(){return NomCarte;}
    public void setNomCarte(String NomCarte){this.NomCarte.set(NomCarte);}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomCarte.textProperty().setValue(getTextNom());
    }
}

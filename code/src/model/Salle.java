package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

public class Salle implements Serializable {
    private int numSalle;
    private Monstre monstre;

    public Salle(int numeroSalle) {
        numSalle = numeroSalle;
    }
    public Monstre getMonstre() {
        return monstre;
    }
    public void setMonstre(Monstre m){
        monstre = m;
    }
    public int getNumSalle() {
        return numSalle;
    }

    public boolean contientBoss() {
        boolean oui = false;
        if (monstre.isBoss())
                oui = true ;
        return oui;
    }

    public void changerSalle() throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Bonus.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Bonus");
        stage.setScene(new Scene(p, 600, 250));
        stage.show();
    }

    @Override
    public String toString() {
        return "Salle{" +
                "numSalle=" + numSalle +
                ", monstre=" + monstre +
                '}';
    }
}

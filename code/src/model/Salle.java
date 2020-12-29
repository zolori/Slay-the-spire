package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Salle implements Serializable,SerialisationPartie {
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



    public void serialisation(ObjectOutputStream oos) {
        try {
            oos.writeObject(numSalle);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deserialisation(ObjectInputStream ois) {
        try {
            numSalle= (int) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

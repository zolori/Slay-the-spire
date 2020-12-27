package model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Salle {
    private int numSalle;
    private static Monstre monstre;
    private int nbTour;
    Manager leManager = Manager.getInstance();

    public Salle(int numeroSalle) {
        numSalle = numeroSalle;
        monstre = new Monstre("Orcs",100,numSalle,10);
        if(getNumSalle()%5 == 0) {
            nvMonstre("Boss", 200, getNumSalle(), 20);
        }
        else
            nvMonstre("Orc", 100, getNumSalle(), 10);
    }

    public Monstre getMonstre() {
        return monstre;
    }
    public  void setMonstre(Monstre m) {
        monstre = m;
    }

    public void nvMonstre(String n, int pdv, int salle, int degats){
        monstre = new Monstre(n, pdv, salle, degats);
    }

    public int getNumSalle() {
        return numSalle;
    }
    public int getNbTour() { return nbTour; }

    public boolean contientBoss() {
        boolean oui = false;
        if (monstre.isBoss())
                oui = true ;
        return oui;
    }

    public void changerSalle() throws IOException {
        int salle = leManager.getJoueur().getSalle();
        leManager.getJoueur().setSalle(salle + 1);

        Parent p = FXMLLoader.load(getClass().getResource("/Bonus.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Bonus");
        stage.setScene(new Scene(p, 600, 250));
        stage.show();
    }
}

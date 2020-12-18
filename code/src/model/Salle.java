package model;
import java.util.ArrayList;

public class Salle {
    private int num;
    //private ArrayList<Carte> pioche;
    private Joueur joueur;
    private static int numSalle;
    private static ArrayList<Monstre> listeMonstre;
    private static int nbMonstre = 0;
    private int nbTour;

    public Salle(int numeroSalle){
        this.numSalle=numeroSalle;
        ArrayList<Monstre> ListeMonstre=this.listeMonstre;
    }

    public ArrayList<Monstre> getlisteMonstre() {
        return listeMonstre;
    }
    public static void setListeMonstre(ArrayList<Monstre> Monstres) {
        Salle.listeMonstre = Monstres;
    }
    public static void nvMonstre(String n, int pdv, int salle, int degats){
        nbMonstre+=1;
        listeMonstre.add(new Monstre(n, pdv, salle, degats));
    }

    //public ArrayList<Carte> getPioche() { return pioche; }
    public Joueur getJoueur() {return joueur; }
    public static int getNumSalle() {
        return numSalle;
    }
    public void getNbMonstre(){
        nbMonstre=listeMonstre.size();
    }
    public int getNbTour() { return nbTour; }

    public boolean contientBoss() {
        boolean oui = false;

        for (Monstre m : listeMonstre ) {
            if (m.isBoss())
                oui = true;
        }
        return oui;
    }
}

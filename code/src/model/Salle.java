package model;

import java.util.ArrayList;

public class Salle {
    private Joueur joueur;
    private static int numSalle;
    private static ArrayList<Monstre> listeMonstre;
    private static int nbMonstre=0;

    public Salle(int numeroSalle){
        this.numSalle=numeroSalle;
    }

    public static int getNumSalle() {
        return numSalle;
    }

    public ArrayList<Monstre> getlisteMonstre() {
        return listeMonstre;
    }

    public static void setListeMonstre(ArrayList<Monstre> Monstres) {
        Salle.listeMonstre = Monstres;
    }

    public void getNbMonstre(){
        nbMonstre=listeMonstre.size();
    }

    public static void nvMonstre(Monstre Monstre){
        nbMonstre+=1;
        listeMonstre.add(Monstre);
    }
}


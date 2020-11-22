package model;

import java.util.ArrayList;

public class Joueur extends Entite{
    private static Salle Terrain;
    private ArrayList<Carte> deck;

    public Joueur(String p, int pdv, Salle salle) {
        super(p, pdv);
        this.Terrain = salle;
        deck = new ArrayList<>();
    }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public String toString(){
        return super.toString() + Terrain.getNumSalle()/*+ deck*/;
    }

    public static int getNumTerrain(){ return Terrain.getNumSalle();}

    public boolean victoire(){
        for (int i=0; i<Terrain.getlisteMonstre().size();i++){
            if(!Terrain.getlisteMonstre().get(i).isDead()){
                return false;
            }
        }
        return true;
    }

    public void changerSalle(){
        if(victoire()){
            Terrain=new Salle(this.Terrain.getNumSalle()+1);
            this.Terrain=Terrain;
        }
    }

    /*public static void use(Carte c){
        Effets e=Carte.getEffet();
    }*/
}

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Serializable {
    private Partie partie;

    private Manager(){}

    private static Manager INSTANCE = new Manager();

    public static  Manager getInstance() {
        return INSTANCE;
    }

    public void createJoueur(String nom, int pdv) {
        Joueur joueur = new Joueur(nom, pdv, 3);
        partie = new Partie(joueur);
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie p){partie=p;}
    
    public void serialiser(ObjectOutputStream oos){
        Partie p = this.getPartie();
        Joueur j = p.getJoueur();
        Salle s = p.getSalle(j.getNumSalle());
        Monstre m = s.getMonstre();
        List<Carte> deck = new ArrayList<>(j.getDeck());

        try {
            oos.writeObject(j);
            ArrayList<Carte> deckS = new ArrayList<>(deck);
            for (int i=0; i<3; i++) {
                Carte c = deckS.get(i);
                System.out.println(c.getNom());
                oos.writeObject(c);
            }
            oos.writeObject(m);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void deserialiser(ObjectInputStream ois){
        Joueur j = new Joueur("",100,3);
        Monstre m = new Monstre("",100,1,10);
        try {
            Joueur j1 = ((Joueur) ois.readObject());
            j.setAll(j1);
            ArrayList<Carte> deckS = new ArrayList<>();
            for (int i = 0; i<3; i++) {
                Carte c = new Carte("", "",1,1,"images/epee.png");
                Carte c1 = (Carte)ois.readObject();
                c.setAll(c1);
                System.out.println(c.getNom());
                deckS.add(c);
            }
            j.setDeck(deckS);
            Monstre m1 = ((Monstre) ois.readObject());
            m.setAll(m1);
            Salle s = new Salle(j.getNumSalle());
            s.setMonstre(m);
            Partie p = new Partie(j,deckS,s,m);
            setPartie(p);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

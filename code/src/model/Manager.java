package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager de l'application
 */
public class Manager implements Serializable {
    /**
     * Partie de jeu.
     */
    private Partie partie;

    /**
     * (seule) Instance de manager.
     */
    private static Manager INSTANCE = new Manager();

    /**
     * @return l'instance du manager
     */
    public static  Manager getInstance() {
        return INSTANCE;
    }

    /**
     * Créé un joueur pour pouvoir créer la partie.
     *
     * @param nom
     *      Nom du joueur.
     * @param pdv
     *      Points de vie du joueur.
     */
    public void createJoueur(String nom, int pdv) {
        Joueur joueur = new Joueur(nom, pdv, 3);
        partie = new Partie(joueur);
    }

    /**
     * @return la partie en cours.
     */
    public Partie getPartie() {
        return partie;
    }

    /**
     * Met à jour la partie.
     *
     * @param p
     *      Nouvelle partie.
     */
    public void setPartie(Partie p) { partie = p; }

    /**
     * Sauvegarde les données.
     *
     * @param oos
     *      Sert à écrire dans le fichier spécifié.
     */
    public void serialiser(ObjectOutputStream oos){
        Partie p = this.getPartie();
        Joueur j = p.getJoueur();
        Salle s = p.getSalle(j.getNumSalle());
        List<Carte> deck = new ArrayList<>(j.getDeck());
        try {
            oos.writeObject(j);
            ArrayList<Carte> deckS = new ArrayList<>(deck);
            for (int i=0; i<3; i++) {
                Carte c = deckS.get(i);
                oos.writeObject(c);
            }
            oos.writeObject(s);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Récupère les données sauvegardées.
     *
     * @param ois
     *      Sert à lire dans le fichier spécifié.
     */
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
                deckS.add(c);
            }
            j.setDeck(deckS);
            Salle s= (Salle)ois.readObject();
            m.setAll(s.getMonstre());
            Partie p = new Partie(j,deckS,s,m);
            setPartie(p);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}

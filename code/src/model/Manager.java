package model;

public class Manager {
    private int salleActuelle;
    private Joueur joueur;

    public Manager(){
        setSalleActuelle(1);
    }
    public void setSalleActuelle(int salleActuelle) { this.salleActuelle = salleActuelle; }

    public Joueur createJoueur(String nom, int pdv, int pa) {
        Joueur j = new Joueur(nom, pdv,pa, 3);
        j.setSalle(1);

        return j;
    }
}

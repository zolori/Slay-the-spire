package model;

public class Manager {
    private static Salle salle;
    private int salleActuelle;
    private Joueur joueur= new Joueur("Hero",400,3,3);

    public Manager(){
        setSalleActuelle(1);
    }
    public static Salle getSalleActuelle(){ return salle;}
    public void setSalleActuelle(int salleActuelle) { this.salleActuelle = salleActuelle; }

    public Joueur createJoueur(String nom, int pdv, int pa) {
        Joueur j = new Joueur(nom, pdv,pa, 3);
        j.setSalle(1);

        return j;
    }

    public void createSalle(int numsalle){
        salle= new Salle(numsalle);
    }

    public static Salle getSalle() { return salle; }

    public Joueur getJoueur() { return joueur; }

    public void useCard(Carte selectedItem) {
        Monstre m=salle.getMonstre();
        switch (selectedItem.getNom()) {
            case "Attaque":
                m.subit(selectedItem.getValeur());
                break;
            case "Protection":
                joueur.protect(selectedItem.getValeur());
                break;
            case "Soin":
                joueur.soigne(selectedItem.getValeur());
                break;
        }
    }
}

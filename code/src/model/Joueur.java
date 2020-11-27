package model;

import java.util.ArrayList;

public class Joueur {
    private int numSalle;
    private ArrayList<Carte> deck;
    private int ptsAction;
    private String nom;
    private int pointsDeVie;
    private int pdvMax;

    public Joueur(String n, int pdv, int pa) {
        nom = n;
        pdvMax = pdv;
        soin();
        numSalle = 0;
        ptsAction = pa;
        deck = new ArrayList<>();
    }

    private void soin() { pointsDeVie = pdvMax; }

    public ArrayList<Carte> getDeck() {
        return deck;
    }

    public int getPdv() {
        return pointsDeVie;
    }

    public int getPdvMax() { return pdvMax; }

    private String getNom() {
        return nom;
    }

    public int getPA() { return ptsAction; }

    public int getSalle() { return numSalle; }

    public void setSalle(int n) { numSalle = n; }

    public void setPdv(int pointsDeVie) { this.pointsDeVie = pointsDeVie; }

    public void renforcer (Bonus b, Salle s) {
        //On récupère la pioche
        ArrayList<Carte> cartes=s.getPioche();
        Boolean isBoss=false;

        //On regarde si il y avait un boss dans la salle
        if (this.numSalle==s.getNum() && s.contientBoss()) //Si on tue un boss, bonus améliorés
        {
            isBoss=true; //Si il y a un boss, on passe la variable a true
            switch (b) {
                case Degats:
                    for (int i=0;i<cartes.size();i++)
                        cartes.get(i).renforcement(isBoss); //On utilise le renforcement de manière amélioré
                    break;
                case VieMax:
                    pdvMax=getPdvMax()+10; //On augmente le maximum de pdv
                    break;
                case PointAction:
                    ptsAction=ptsAction+2; //On augmente les ptsAction de 2
                    break;
                case Regeneration:
                    pdvMax=getPdvMax()+5; //On augmente le maximum de pdv
                    soin(); // On remet le joueur au maximum de ses pts de vie
                    break;
            }
        }
        switch (b) {
            //Bonus simples
            case Degats:
                for (int i=0;i<cartes.size();i++)
                    cartes.get(i).renforcement(isBoss); //On utilise le renforcement de manière simple
                break;
            case VieMax:
                pdvMax=getPdvMax()+5; //On augmente le maximum de pdv
                break;
            case PointAction:
                ptsAction++; //On augmente les ptsAction de 1
                break;
            case Regeneration:
                soin(); // On remet le joueur au maximum de ses pts de vie
                break;
        }
    }

    public void utiliserCarte(Carte c, Monstre cible){
        if(c.getPA()<getPA() && !c.getUsed()){
            c.jouerCarte(cible);
        }
        return;
    }

}

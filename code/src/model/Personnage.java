package model;

public interface Personnage {
    boolean attaque(Personnage p, int val);
    int getPointsDeVie();
    void setPointsDeVie(int value);
}

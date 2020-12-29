package model;

import java.util.ArrayList;

public class Boss extends Monstre {
    private ArrayList<Monstre> allies;
    private int temps;
    private String image;

    public Boss(String n, int pdv, int num, int d) {
        super(n, pdv, num, d);
        allies = new ArrayList<>();
        image="/images/Boss.png";
    }

    public void superAttaque(int nbTours, Personnage p) {
        if (nbTours%temps == 0) {
            super.attaque(p, getDegats());
        }
    }

    @Override
    public String getImage() { return image; }
}

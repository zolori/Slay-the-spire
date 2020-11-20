package model;

import java.util.ArrayList;

public class Boss extends Monstre{
    private ArrayList<Monstre> allies;

    public Boss(String n, int pdv, int num) {
        super(n, pdv, num);
        allies = new ArrayList<>();
    }
}

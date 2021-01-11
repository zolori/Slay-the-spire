package model;

import java.util.ArrayList;

public class Boss extends Monstre {
    private String image;

    public Boss(String n, int pdv, int num, int d) {
        super(n, pdv, num, d);
        image="/images/Boss.png";
    }

    @Override
    public String getImage() { return image; }
}

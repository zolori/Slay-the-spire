package model;

/**
 * <b>Boss est un Monstre possédant des caractéristiques améliorées.</b>
 */
public class Boss extends Monstre {
    private String image;

    /**
     *
     * @param n
     *      Le nom du boss.
     * @param pdv
     *      Les points de vie du boss.
     * @param num
     *      Le numéro de salle où réside le boss.
     * @param d
     *      Les dégats de base infligés par le boss.
     */
    public Boss(String n, int pdv, int num, int d) {
        super(n, pdv, num, d);
        image = "/images/Boss.png";
    }

    /**
     *
     * @return l'image propre au boss.
     */
    @Override
    public String getImage() { return image; }
}

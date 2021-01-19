package model;

/**
 * Interface personnage
 */
public interface Personnage {
    /**
     * Inflige des dégats à un personnage.
     *
     * @param p
     *      Personnage à attaquer.
     * @param val
     *      Montant des dégats.
     *
     * @return
     *      true : si le monstre est vaincu.
     *      false : s'il est toujours en vie.
     */
    boolean attaque(Personnage p, int val);

    /**
     * @return les points de vide du personnage.
     */
    int getPointsDeVie();

    /**
     * Met à jour les points de vie du personnage.
     *
     * @param value
     *      Nouvelle valeur des points de vie.
     */
    void setPointsDeVie(int value);
}

package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Carte;

/**
 * TaskCell est la classe qui permet de charger les cartes de façon aléatoire
 */
public class TaskCellFactory implements Callback<ListView<Carte>, ListCell<Carte>> {
    /**
     * Cette fonction gère la création de TaskCell afin de créer de nouvelles cartes.
     * @param param : ListView des cartes actuelles du joueur.
     * @return Une nouvelle TaskCell qui va donc créer une nouvelle carte.
     */
    @Override
    public ListCell<Carte> call(ListView<Carte> param) {
        return new TaskCell();
    }
}

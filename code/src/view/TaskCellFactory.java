package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Carte;

public class TaskCellFactory implements Callback<ListView<Carte>, ListCell<Carte>> {

    @Override
    public ListCell<Carte> call(ListView<Carte> param) {
        return new TaskCell();
    }
}

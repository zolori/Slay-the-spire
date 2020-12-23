package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Carte;
import model.Effets;


public class VueCarte {
    @FXML
    private ListView<Carte> listView;
    private ObservableList<Carte> deck = FXCollections.observableArrayList();

    public void initialize() {
        //Cree une liste de carte
        deck.addAll(
                new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1, "images/epee.png"),
                new Carte("Protection", "Description carte", 1, 30, Effets.physique, 1, "images/bouclier.png"),
                new Carte("Soin", "Description carte", 1, 40, Effets.physique, 1, "images/coeur.png")
        );
        //Bind la liste a la ListView
        listView.setItems(deck);
        //listView.setCellFactory(new TaskCellFactory());
    }

    @FXML public void handleMouseClick(MouseEvent arg0) {
        int selectedCarteIndex = listView.getSelectionModel().getSelectedIndex();
        deck.set(selectedCarteIndex, new Carte("Attaque", "Description carte", 1, 10, Effets.magique, 1, "images/epee.png"));
        //selectedCarte.setDescription("Updated");
        //custoListView.refresh();
        //custoListView.getSelectionModel().clearSelection();
        //int carteIndex = cartes.indexOf(selectedCarte);
        //cartes.remove(carteIndex);
        //cartes.add(carteIndex, new Carte("Defendre", "defendre.png", "Se proteger avec un bouclier ("+carteIndex+")"));
    }
}


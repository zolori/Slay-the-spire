package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;

/**
 * Cette classe correspond au popup qui s'affiche lorque l'on essaye de charger une partie alors qu'il n'existe pas de sauvegarde valide
 */
public class PopupController {
    /**
     * Cette fonction ferme le popup.
     * @param actionEvent : Evenement retourné quand l'utilisateur clique sur le bouton 'retour'
     */
    public void retour(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}

package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;

public class PopupController {

    public void retour(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}

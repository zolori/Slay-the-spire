package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Bonus implements Initializable{
    @FXML
    private BorderPane borderpane =new BorderPane();
    @FXML
    private HBox hbox = new HBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void VieMax(ActionEvent actionEvent) throws IOException {
        retourAccueil(actionEvent);
    }

    public void Regen(ActionEvent actionEvent) throws IOException{
        retourAccueil(actionEvent);
    }

    public void PA(ActionEvent actionEvent) throws IOException{
        retourAccueil(actionEvent);
    }

    public void Degats(ActionEvent actionEvent) throws IOException{
        retourAccueil(actionEvent);
    }

    public void retourAccueil(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/Salle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Salle");
        stage.setScene(new Scene(p, 400, 600));
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        stage.setFullScreen(true);
    }
}

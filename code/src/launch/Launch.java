package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launch extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Accueil.fxml"));

        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.setTitle("Accueil");
        primaryStage.show();

    }
}

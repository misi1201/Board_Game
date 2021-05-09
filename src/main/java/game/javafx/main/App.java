package game.javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource("/fxml/Launch.fxml"));
        primaryStage.setTitle("My Board Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
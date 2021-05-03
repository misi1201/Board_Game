package hello;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloWorldApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(HelloWorldApplication.class.getResource("/fxml/Game.fxml"));
        primaryStage.setTitle("Hello, World!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
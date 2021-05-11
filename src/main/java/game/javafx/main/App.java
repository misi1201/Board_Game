package game.javafx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class that controls the GUI of the application.
 */
public class App extends Application {

    /**
     * The method that starts the first scene of the application.
     *
     * @param primaryStage Stage Object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource("/fxml/Launch.fxml"));
        primaryStage.setTitle("My Board Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
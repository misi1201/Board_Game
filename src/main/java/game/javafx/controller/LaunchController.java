package game.javafx.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * The controller class for the launch scene.
 */
@SuppressWarnings("unchecked")
@Slf4j
public class LaunchController {

    @FXML
    private TextField player1name;

    @FXML
    private TextField player2name;

    @FXML
    private Label errorLabel;

    /**
     * The startGame method is called when the game starts.
     * @param actionEvent the current action event
     * @throws IOException if the wanted scene can not be found
     */
    public void startGame(ActionEvent actionEvent) throws IOException {

        if (player1name.getText().isEmpty() || player2name.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
            log.info("Not enough names given!");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
            Parent root = loader.load();
            GameController gameController = loader.<GameController>getController();
            gameController.setName(player1name.getText(), player2name.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /**
     * A method that is called when a player clicks on the exit button.
     * @param actionEvent the current action event
     */
    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * A method that is called when a player clicks on the HighScores button.
     * @param actionEvent the current action event
     * @throws IOException if the wanted scene can not be found
     */
    public void openHighScores(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/highscores.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Loading highscores..");


    };

}
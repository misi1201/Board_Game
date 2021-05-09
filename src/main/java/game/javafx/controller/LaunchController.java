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

import java.io.IOException;

public class LaunchController {

    @FXML
    private TextField player1name;

    @FXML
    private TextField player2name;

    @FXML
    private Label errorLabel;


    public void startGame(ActionEvent actionEvent) throws IOException {

        if (player1name.getText().isEmpty() || player2name.getText().isEmpty()) {
            errorLabel.setText("Enter your name!");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = loader.load();
            GameController gameController = loader.<GameController>getController();
            gameController.setName(player1name.getText(), player2name.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }



}
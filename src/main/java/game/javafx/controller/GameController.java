package game.javafx.controller;

import game.model.GameModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameController {

    @FXML
    private Pane pane;

    @FXML
    private Text player1name;

    @FXML
    private Text player2name;

    @FXML
    private Text player1steps;

    @FXML
    private Text player2steps;

    @FXML
    private Label winnerLabel;


    private int size = 400;
    private int spots = 5;
    private int squareSize = size / spots;
    private int prevX;
    private int prevY;
    private GameModel gameModel;
    private int currentPlayer;
    private String p1name;
    private String p2name;




    @FXML
    public void initialize(){

        Platform.runLater(() -> {
            player1name.setText(p1name);
            player2name.setText(p2name);
            gameModel.setP1name(player1name.getText());
            gameModel.setP2name(player2name.getText());
        });
        startGame();
}

    public void startGame(){
        currentPlayer = 1;
        gameModel = new GameModel();
        gameModel.setP1steps(0);
        gameModel.setP2steps(0);
        player1steps.setText(gameModel.getP1steps()+"");
        player2steps.setText(gameModel.getP2steps()+"");
        gameModel.setP1name(player1name.getText());
        gameModel.setP2name(player2name.getText());
        winnerLabel.setText("");
        for(int i = 0; i < 320; i+= squareSize){
            for(int j = 0; j < size; j+= squareSize){
                Rectangle r = new Rectangle(i,j,squareSize,squareSize);
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().addAll(r);
            }
        }




        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Circle c = new Circle();
                if (i == 0 && j == 0 || i == 2 && j == 0 || i == 1 && j == 4 || i == 3 && j == 4) {
                    c.setFill(Color.BLUE);
                    c.setStroke(Color.BLACK);

                    double radius = squareSize / 2.5;
                    int x = squareSize / 2 + squareSize * (0+i);
                    int y = squareSize / 2 + squareSize * (0+j);
                    c.setRadius(radius);
                    c.setTranslateX(x);
                    c.setTranslateY(y);
                    pane.getChildren().add(c);

                    c.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent, c));
                    c.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent, c));
                    c.setOnMouseReleased(mouseEvent -> mouseReleased(mouseEvent, c));


                }
                else {
                    if (i == 1 && j == 0 || i == 3 && j == 0 || i == 0 && j == 4 || i == 2 && j == 4) {
                        c.setFill(Color.RED);
                        c.setStroke(Color.BLACK);

                        double radius = squareSize / 2.5;
                        int x = squareSize / 2 + squareSize * (0 + i);
                        int y = squareSize / 2 + squareSize * (0 + j);
                        c.setRadius(radius);
                        c.setTranslateX(x);
                        c.setTranslateY(y);
                        pane.getChildren().add(c);

                        c.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent, c));
                        c.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent, c));
                        c.setOnMouseReleased(mouseEvent -> mouseReleased(mouseEvent, c));


                    }
                }
            }
        }

    }

    private void increasePlayerStep(int playerId) {
        if (playerId == 1) {
            gameModel.setP1steps(gameModel.getP1steps()+1);
            player1steps.setText(gameModel.getP1steps()+"");
        }
        else {
            gameModel.setP2steps(gameModel.getP2steps()+1);
            player2steps.setText(gameModel.getP2steps()+"");
        }
    }


    private void mouseReleased(MouseEvent mouseEvent, Circle c) {
        int gridX = (int) c.getTranslateX() / squareSize;
        int gridY = (int) c.getTranslateY() / squareSize;

        int dirX = (int) (squareSize / 2) + squareSize * gridX;
        int dirY = (int) (squareSize / 2) + squareSize * gridY;

        c.setTranslateX(dirX);
        c.setTranslateY(dirY);


    }

    private void mouseDragged(MouseEvent mouseEvent, Circle c) {
        c.setTranslateX(c.getTranslateX() + mouseEvent.getX());
        c.setTranslateY(c.getTranslateY() + mouseEvent.getY());
    }

    private void mousePressed(MouseEvent mouseEvent, Circle c) {

    }

    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void resetGame(ActionEvent actionEvent) {
        startGame();
    }



}
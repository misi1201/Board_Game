package game.javafx.controller;

import game.model.GameModel;
import game.model.Player;
import game.result.GameResult;
import game.result.GameResultDao;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * The controller class for the game scene.
 */
@SuppressWarnings("unchecked")
@Slf4j
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

    private Instant startTime;

    @FXML
    private Label stopWatchLabel;

    private Timeline stopWatchTimeline;


    private int size = 400;
    private int spots = 5;
    private int squareSize = size / spots;
    private int prevX;
    private int prevY;
    private GameModel gameModel;
    private Player currentPlayer;
    private String p1name;
    private String p2name;
    private Player player1;
    private Player player2;
    private GameResultDao gameResultDao;



    /**
     * The initialize method that runs when the scene is opened.
     */
    @FXML
    public void initialize(){

        Platform.runLater(() -> {
            player1name.setText(p1name);
            player2name.setText(p2name);
            player1.setName(p1name);
            player2.setName(p2name);
        });
        startGame();
}

    /**
     * A method that initializes the starting state.
     */
    public void startGame(){
        gameModel = new GameModel();
        player1 = new Player(player1name.getText(),1,0);
        player2 = new Player(player2name.getText(),2,0);
        player1steps.setText("Steps: " + player1.getStepCount());
        player2steps.setText("Steps: " + player2.getStepCount());
        gameModel.setBluePlayer(player1);
        gameModel.setRedPlayer(player2);
        currentPlayer = player1;
        winnerLabel.setText("");
        startTime = Instant.now();
        createStopWatch();

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

    /**
     * A method that increases the player's steps.
     * @param currentPlayer the player who moved in the current round
     */
    private void increasePlayerStep(Player currentPlayer) {
        if (currentPlayer.getPlayerId() == 1) {
            currentPlayer.setStepCount(currentPlayer.getStepCount() + 1);
            player1steps.setText("Steps: " + player1.getStepCount());
        }
        else {
            currentPlayer.setStepCount(currentPlayer.getStepCount() + 1);
            player2steps.setText("Steps: " + player2.getStepCount());
        }
    }

    /**
     * A method that sets the player's name to {@code p1name} and {@code p2name}.
     * @param p1name the name of Player One (Blue)
     * @param p2name the name of Player Two (Red)
     */
    public void setName(String p1name, String p2name) {
        this.p1name = p1name;
        this.p2name = p2name;
    }

    /**
     * A mouseReleased method is invoked when a puck is being released.
     * @param mouseEvent the current mouse event
     * @param c the puck that has been dragged and released
     */
    private void mouseReleased(MouseEvent mouseEvent, Circle c) {
        int gridX = (int) c.getTranslateX() / squareSize;
        int gridY = (int) c.getTranslateY() / squareSize;
        int dir;
        if (gridX == prevX + 1 && gridY == prevY) {
            dir = 0;
        } else {
            if (gridX == prevX - 1 && gridY == prevY) {
                dir = 1;
            } else {
                if (gridY == prevY - 1 && gridX == prevX) {
                    dir = 2;
                } else {
                    if (gridY == prevY + 1 && gridX == prevX) {
                        dir = 3;
                    } else {
                        dir = -1;
                    }
                }
            }
        }

        if (gameModel.isValidMove(currentPlayer,prevY, prevX,dir)){
            int dirX = (int) (squareSize / 2) + squareSize * gridX;
            int dirY = (int) (squareSize / 2) + squareSize * gridY;
            gameModel.move(currentPlayer,prevY,prevX,dir);
            c.setTranslateX(dirX);
            c.setTranslateY(dirY);
            increasePlayerStep(currentPlayer);
            if(currentPlayer.getPlayerId() == 1) {
                currentPlayer = player2;
            }
            else {
                currentPlayer = player1;
            }
            if (gameModel.isGameOver()){
                winnerLabel.setText(gameModel.getWinner().getName()+" won the game.");
                log.info(gameModel.getWinner().getName()+" won the game.");
                gameResultDao = new GameResultDao();
                gameResultDao.persist(createGameResult());
            }

        }
        else {
            c.setTranslateX(squareSize / 2 + squareSize * prevX);
            c.setTranslateY(squareSize / 2 + squareSize * prevY);
        }

    }

    /**
     * A mouseDragged method is invoked when the mouse is dragged on a puck.
     * @param mouseEvent the current mouse event
     * @param c the puck that has been dragged
     */
    private void mouseDragged(MouseEvent mouseEvent, Circle c) {
        c.setTranslateX(c.getTranslateX() + mouseEvent.getX());
        c.setTranslateY(c.getTranslateY() + mouseEvent.getY());
    }

    /**
     * A mousePressed method is invoked when the mouse is pressed on a puck.
     * @param mouseEvent the current mouse event
     * @param c the puck that has been pressed
     */
    private void mousePressed(MouseEvent mouseEvent, Circle c) {
        prevX = (int) c.getTranslateX() / squareSize;
        prevY = (int) c.getTranslateY() / squareSize;

    }

    /**
     * A method that is called when a player clicks on the exit button.
     * @param actionEvent the current action event
     */
    public void exitGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     * A method that is called when a player clicks on the reset button.
     * @param actionEvent the current action event
     */
    public void resetGame(ActionEvent actionEvent) {
        startGame();
    }

    /**
     * A method that creates the result of the game.
     * @return A {@code GameResult} object that stores the result of the game
     */
    private GameResult createGameResult(){

        return GameResult.builder().player(gameModel.getWinner().getName()).steps(gameModel.getWinner().getStepCount()).duration(Duration.between(startTime,Instant.now())).build();
    }

    /**
     * A method that creates and runs the stopwatch.
     */
    private void createStopWatch() {
        stopWatchTimeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            long millisElapsed = startTime.until(Instant.now(), ChronoUnit.MILLIS);
            stopWatchLabel.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(javafx.util.Duration.seconds(1)));
        stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopWatchTimeline.play();
    }

}
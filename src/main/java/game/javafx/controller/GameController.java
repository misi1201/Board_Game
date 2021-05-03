package game.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {

    @FXML
    Pane pane;

    private int size = 400;
    private int spots = 5;
    private int squareSize = size / spots;




    @FXML
    public void initialize(){
        for(int i = 0; i < 320; i+= squareSize){
            for(int j = 0; j < size; j+= squareSize){
                Rectangle r = new Rectangle(i,j,squareSize,squareSize);
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().addAll(r);
            }
        }


    }

}

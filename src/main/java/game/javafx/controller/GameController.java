package game.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController {

    @FXML
    Pane pane;


    @FXML
    public void initialize(){
        for(int i = 0; i < 200; i+= 40){
            for(int j = 0; j < 200; j+= 40){
                Rectangle r = new Rectangle(i,j,40,40);
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().add(r);
            }
        }


    }

}

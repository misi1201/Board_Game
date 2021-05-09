package game.model;

import lombok.Data;

@Data
public class Player {

    private String name;

    private int playerId;

    private int stepCount;


    public Player(){

    }

    public Player(String name, int playerId, int stepCount){
        this.name = name;
        this.playerId = playerId;
        this.stepCount = stepCount;
    }



}

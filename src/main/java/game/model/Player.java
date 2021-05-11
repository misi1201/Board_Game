package game.model;

import lombok.Data;

/**
 * Class that represents a player object.
 */
@Data
public class Player {

    /**
     * String value that stores the name of the player.
     */
    private String name;

    /**
     * Integer value that stores the id of the player.
     * The player who uses the blue pucks has the id of 1, the other player with the red pucks has the id of 2.
     */
    private int playerId;

    /**
     * Integer value that stores how many steps the player has done.
     */
    private int stepCount;

    /**
     * No args constructor.
     */
    public Player(){

    }

    /**
     * All args constructor.
     * @param name the name of the player.
     * @param playerId the id of the player. Blue player's id has to be 1, red player's has to be 2.
     * @param stepCount the number of steps of the player.
     */
    public Player(String name, int playerId, int stepCount){
        this.name = name;
        this.playerId = playerId;
        this.stepCount = stepCount;
    }



}

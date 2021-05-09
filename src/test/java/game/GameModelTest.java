package game;

import game.model.GameModel;
import game.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class GameModelTest {

    GameModel gameModel;
    Player player1;
    Player player2;

    @BeforeEach
    void setUp(){
        gameModel = new GameModel();
        player1 = new Player("p1",1,0);
        player2 = new Player("p2",2,0);
    }

    @Test
    void testIsValidMoveAtStartingPosition(){

        assertTrue(gameModel.isValidMove(player1, 0, 0, 3));
        assertFalse(gameModel.isValidMove(player1, 0, 0, 2));
        assertFalse(gameModel.isValidMove(player1, 0, 0, 1));
        assertFalse(gameModel.isValidMove(player1, 0, 0, 0));
        assertFalse(gameModel.isValidMove(player1, 0, 0, 5));
        assertFalse(gameModel.isValidMove(player2, 0, 0, 3));








    }



}

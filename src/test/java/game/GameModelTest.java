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

        assertTrue(gameModel.isValidMove(player2, 0, 1, 3));
        assertFalse(gameModel.isValidMove(player2, 0, 1, 2));
        assertFalse(gameModel.isValidMove(player2, 0, 1, 1));
        assertFalse(gameModel.isValidMove(player2, 0, 1, 0));
        assertFalse(gameModel.isValidMove(player2, 0, 1, 5));
        assertFalse(gameModel.isValidMove(player1, 0, 1, 3));


        assertTrue(gameModel.isValidMove(player1, 4, 3, 2));
        assertFalse(gameModel.isValidMove(player1, 4, 3, 3));
        assertFalse(gameModel.isValidMove(player1, 4, 3, 1));
        assertFalse(gameModel.isValidMove(player1, 4, 3, 0));
        assertFalse(gameModel.isValidMove(player1, 4, 3, 5));
        assertFalse(gameModel.isValidMove(player2, 4, 3, 2));

        assertTrue(gameModel.isValidMove(player2, 4, 2, 2));
        assertFalse(gameModel.isValidMove(player2, 4, 2, 3));
        assertFalse(gameModel.isValidMove(player2, 4, 2, 1));
        assertFalse(gameModel.isValidMove(player2, 4, 2, 0));
        assertFalse(gameModel.isValidMove(player2, 4, 2, 5));
        assertFalse(gameModel.isValidMove(player1, 4, 2, 2));


    }

    @Test
    void testIsValidMove(){
        gameModel = new GameModel(new int[][]{
                {0, 1, 1, 0},
                {2, 0, 0, 2},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {2, 2, 0, 0}
        });

        assertTrue(gameModel.isValidMove(player1, 0, 1, 3));
        assertFalse(gameModel.isValidMove(player1, 0, 2, 1));
        assertTrue(gameModel.isValidMove(player2, 1, 3, 2));
        assertFalse(gameModel.isValidMove(player2, 4, 0, 1));
        assertTrue(gameModel.isValidMove(player1, 2, 1, 1));
        assertFalse(gameModel.isValidMove(player1, 4, 3, 5));
        assertTrue(gameModel.isValidMove(player2, 4, 1, 2));
        assertFalse(gameModel.isValidMove(player2, 0, 1, 3));

    }

    @Test
    void testHasBlueWon(){
        gameModel = new GameModel(new int[][]{
                {2, 0, 0, 0},
                {1, 1, 1, 2},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 2, 0, 1}
        });
        gameModel.setBluePlayer(player1);
        assertTrue(gameModel.hasBlueWon());

        gameModel = new GameModel(new int[][]{
                {0, 2, 0, 0},
                {0, 1, 0, 0},
                {2, 1, 0, 2},
                {0, 1, 0, 0},
                {0, 2, 0, 1}
        });
        gameModel.setBluePlayer(player1);
        assertTrue(gameModel.hasBlueWon());


        gameModel = new GameModel(new int[][]{
                {0, 2, 0, 0},
                {0, 0, 1, 2},
                {0, 1, 2, 0},
                {1, 0, 0, 0},
                {2, 0, 0, 1}
        });
        gameModel.setBluePlayer(player1);
        assertTrue(gameModel.hasBlueWon());


        gameModel = new GameModel(new int[][]{
                {0, 1, 0, 0},
                {1, 2, 0, 2},
                {2, 1, 2, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        gameModel.setBluePlayer(player1);
        assertFalse(gameModel.hasBlueWon());


        gameModel = new GameModel(new int[][]{
                {0, 0, 2, 0},
                {1, 2, 1, 0},
                {2, 0, 0, 0},
                {0, 2, 0, 1},
                {1, 0, 0, 0}
        });
        gameModel.setBluePlayer(player1);
        assertFalse(gameModel.hasBlueWon());

    }


    @Test
    void testHasRedWon(){
        gameModel = new GameModel(new int[][]{
                {0, 1, 0, 0},
                {0, 2, 2, 2},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {2, 0, 0, 1}
        });
        gameModel.setRedPlayer(player2);
        assertTrue(gameModel.hasRedWon());

        gameModel = new GameModel(new int[][]{
                {0, 0, 1, 0},
                {0, 1, 2, 0},
                {0, 0, 2, 0},
                {2, 0, 2, 1},
                {0, 1, 0, 0}
        });
        gameModel.setRedPlayer(player2);
        assertTrue(gameModel.hasRedWon());

        gameModel = new GameModel(new int[][]{
                {2, 0, 0, 0},
                {0, 2, 1, 0},
                {1, 1, 2, 1},
                {2, 0, 0, 0},
                {0, 0, 0, 0}
        });
        gameModel.setRedPlayer(player2);
        assertTrue(gameModel.hasRedWon());

        gameModel = new GameModel(new int[][]{
                {0, 0, 1, 0},
                {0, 1, 2, 2},
                {2, 0, 1, 1},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        });
        gameModel.setRedPlayer(player2);
        assertFalse(gameModel.hasRedWon());


        gameModel = new GameModel(new int[][]{
                {0, 0, 0, 1},
                {0, 2, 1, 2},
                {0, 1, 2, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 1}
        });
        gameModel.setRedPlayer(player2);
        assertFalse(gameModel.hasRedWon());


    }

    @Test
    void testIsGameOver(){
        gameModel = new GameModel(new int[][]{
                {0, 1, 0, 2},
                {0, 1, 0, 0},
                {0, 1, 2, 2},
                {0, 0, 0, 0},
                {2, 0, 0, 1}
        });

        gameModel.setBluePlayer(player1);
        gameModel.setRedPlayer(player2);
        assertTrue(gameModel.isGameOver());

        gameModel = new GameModel(new int[][]{
                {0, 0, 0, 0},
                {0, 2, 1, 2},
                {1, 1, 2, 0},
                {2, 0, 0, 1},
                {0, 0, 0, 0}
        });

        gameModel.setBluePlayer(player1);
        gameModel.setRedPlayer(player2);
        assertFalse(gameModel.isGameOver());

        gameModel = new GameModel(new int[][]{
                {1, 0, 0, 2},
                {0, 1, 2, 0},
                {0, 2, 0, 0},
                {0, 1, 2, 1},
                {0, 0, 0, 0}
        });
        gameModel.setBluePlayer(player1);
        gameModel.setRedPlayer(player2);
        assertTrue(gameModel.isGameOver());

    }

    @Test
    void testMove(){
        gameModel.move(player1, 0, 0, 3);
        assertArrayEquals(new int[][] {
                {0, 2, 1, 2},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 1, 2, 1}},
                gameModel.getGrid());

        gameModel.move(player2,4,2,2);
        assertArrayEquals(new int[][] {
                {0, 2, 1, 2},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {2, 1, 0, 1}},
                gameModel.getGrid());

        gameModel.setGrid(new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 2},
                {0, 2, 0, 0},
                {2, 1, 2, 1},
                {0, 0, 0, 0}});

        gameModel.move(player1,1,0,3);
        assertArrayEquals(new int[][] {
                {0, 0, 0, 0},
                {0, 1, 0, 2},
                {1, 2, 0, 0},
                {2, 1, 2, 1},
                {0, 0, 0, 0}},
                gameModel.getGrid());
        gameModel.move(player2,2,1,0);
        assertArrayEquals(new int[][] {
                        {0, 0, 0, 0},
                        {0, 1, 0, 2},
                        {1, 0, 2, 0},
                        {2, 1, 2, 1},
                        {0, 0, 0, 0}},
                gameModel.getGrid());
    }



}

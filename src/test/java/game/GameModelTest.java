package game;

import game.model.GameModel;
import game.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;


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



}

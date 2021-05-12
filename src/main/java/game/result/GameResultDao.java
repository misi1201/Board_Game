package game.result;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import java.util.List;

/**
 * DAO class for the {@link GameResult} entity.
 */
public class GameResultDao extends GenericJpaDao<GameResult> {

    /**
     * The constructor that accepts no arguments.
     */
    public GameResultDao() {
        super(GameResult.class);
    }

    /**
     * Returns the list of all results with respect to the steps and time
     * spent for winning the game.
     *
     * @return the list of all results with respect to the steps and time
     * spent for winning the game.
     */
    @Transactional
    public List<GameResult> findAll() {
        return entityManager.createQuery("SELECT r FROM GameResult r ORDER BY r.steps ASC, r.duration ASC", GameResult.class).getResultList();
    }

}

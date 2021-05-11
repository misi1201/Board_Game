package game.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the model of the game and the board.
 */
@Data
@NoArgsConstructor
@Slf4j
public class GameModel {

    /**
     * Integer value that stores the direction of Right.
     */
    private static final int RIGHT = 0;

    /**
     * Integer value that stores the direction of Left.
     */
    private static final int LEFT = 1;

    /**
     * Integer value that stores the direction of Up.
     */
    private static final int UP = 2;

    /**
     * Integer value that stores the direction of Down.
     */
    private static final int DOWN = 3;

    /**
     * Player object that stores the player with the blue pucks.
     */
    private Player bluePlayer;

    /**
     * Player object that stores the player with the red pucks.
     */
    private Player redPlayer;

    /**
     * Player object that stores the winner Player.
     */
    private Player winner;

    /**
     * Player object that stores the loser Player.
     */
    private Player loser;

    /**
     * 2D array representing the state of the game.
     *
     * The blue pucks are represented as 1's, the red pucks are represented as 2's, and the empty spaces as 0's.
     */
    private int[][] grid = {
            {1, 2, 1, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {2, 1, 2, 1}
    };

    /**
     * Constructor which takes a 2D array and set this array to the grid.
     *
     * @param grid 2D array of integers that stores the state of the game and the state of the board.
     */
    public GameModel(int[][] grid) {
        this.grid = grid;
    }


    /**
     * A method that checks if the current move is possible or not.
     *
     * @param currentPlayer The player who has to move at the current state.
     * @param row The row where the the current puck stands on the board.
     * @param col The column where the current puck stands on the board.
     * @param direction The direction where the player wants to move to.
     *
     * @return {@code true} if the player can move to the wanted direction, {@code false} otherwise.
     */
    public boolean isValidMove(Player currentPlayer, int row, int col, int direction) {

        boolean isValid = false;

        if (currentPlayer.getPlayerId() == 1 && grid[row][col] == 2 || !(currentPlayer.getPlayerId() == 1) && grid[row][col] == 1) {
            log.info("You can not move, because it is the other player's turn!");
            isValid = false;
        } else {
            try {
                if (currentPlayer.getPlayerId() == 1) {
                    switch (direction) {
                        case RIGHT: {
                            if (grid[row][col + 1] == 0) {
                                isValid = true;
                            } else {
                                log.info("You can not move there, because there is a puck on ({},{})",row,col + 1);
                            }
                            break;
                        }
                        case LEFT: {
                            if (grid[row][col - 1] == 0) {
                                isValid = true;
                            } else {
                                log.info("You can not move there, because there is a puck on ({},{})",row,col - 1);
                            }
                            break;
                        }
                        case UP: {
                            if (grid[row - 1][col] == 0) {
                                isValid = true;
                            } else {
                                log.info("You can not move there, because there is a puck on ({},{})",row - 1,col);
                            }
                            break;
                        }
                        case DOWN: {
                            if (grid[row + 1][col] == 0) {
                                isValid = true;
                            } else {
                                log.info("You can not move there, because there is a puck on ({},{})",row + 1,col);
                            }
                            break;
                        }
                        default:
                            log.info("Invalid move, you cannot move there!");
                            isValid = false;
                    }
                } else {
                    if (currentPlayer.getPlayerId() == 2) {
                        switch (direction) {
                            case RIGHT: {
                                if (grid[row][col + 1] == 0) {
                                    isValid = true;
                                } else {
                                    log.info("You can not move there, because there is a puck on ({},{})",row,col + 1);
                                }
                                break;
                            }
                            case LEFT: {
                                if (grid[row][col - 1] == 0) {
                                    isValid = true;
                                } else {
                                    log.info("You can not move there, because there is a puck on ({},{})",row,col - 1);
                                }
                                break;
                            }
                            case UP: {
                                if (grid[row - 1][col] == 0) {
                                    isValid = true;
                                } else {
                                    log.info("You can not move there, because there is a puck on ({},{})",row -1 ,col);
                                }
                                break;
                            }
                            case DOWN: {
                                if (grid[row + 1][col] == 0) {
                                    isValid = true;
                                } else {
                                    log.info("You can not move there, because there is a puck on ({},{})",row + 1,col);
                                }
                                break;
                            }
                            default:
                                isValid = false;
                                log.info("Invalid move, you cannot move there!");
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isValid = false;
                log.warn("You can not move out of the field!");
            }
        }
        return isValid;
    }

    /**
     * A method that modifies the grid if the current move is possible.
     *
     * @param currentPlayer The player who has to move at the current state.
     * @param row The row where the the current puck stands on the board.
     * @param col The column where the current puck stands on the board.
     * @param direction The direction where the player wants to move to.
     */
    public void move(Player currentPlayer, int row, int col, int direction) {

        if (isValidMove(currentPlayer, row, col, direction)) {
            switch (direction) {
                case RIGHT: {
                    grid[row][col + 1] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), row, col, row, col + 1);
                    break;
                }
                case LEFT: {
                    grid[row][col - 1] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), row, col, row, col - 1);
                    break;
                }
                case UP: {
                    grid[row - 1][col] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), row, col, row - 1, col);
                    break;
                }
                case DOWN: {
                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", currentPlayer.getName(), row, col, row + 1, col);
                    break;
                }

            }
        }

    }

    /**
     * A method that checks if the current player has won horizontally.
     *
     * @param currentPlayer The current player who might have won the game.
     *
     * @return {@code true} if the current player has won horizontally, {@code false} otherwise.
     */
    public boolean horizontalCheck(Player currentPlayer) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == currentPlayer.getPlayerId()) {
                    if (j != 0 && j != grid[i].length-1 && grid[i][j-1] == currentPlayer.getPlayerId() && grid[i][j+1] == currentPlayer.getPlayerId()) {
                        log.info("Horizontal victory!");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**
     * A method that checks if the current player has won vertically.
     *
     * @param currentPlayer The current player who might have won the game.
     *
     * @return {@code true} if the current player has won vertically, {@code false} otherwise.
     */
    public boolean verticalCheck(Player currentPlayer) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == currentPlayer.getPlayerId()) {
                    if (i != 0 && i != grid.length-1 && grid[i - 1][j] == currentPlayer.getPlayerId() && grid[i + 1][j] == currentPlayer.getPlayerId()) {
                        log.info("Vertical victory!");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**
     * A method that checks if the current player has won diagonally.
     *
     * @param currentPlayer The current player who might have won the game.
     *
     * @return {@code true} if the current player has won diagonally, {@code false} otherwise.
     */
    public boolean diagonalCheck(Player currentPlayer) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == currentPlayer.getPlayerId()) {
                    if (i != 0 && j != grid[i].length-1 && i != grid.length-1 && j !=0 && grid[i-1][j+1] == currentPlayer.getPlayerId() && grid[i+1][j-1] == currentPlayer.getPlayerId() ||
                            i != 0 && i != grid.length-1 && j != grid[i].length-1 && j !=0 && grid[i-1][j-1] == currentPlayer.getPlayerId() && grid[i+1][j+1] == currentPlayer.getPlayerId()
                    ) {
                        log.info("Diagonal victory!");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    /**
     * A method that checks if the player with the red pucks has won the game.
     *
     * @return {@code true} if the player with the red pucks has won the game, {@code false} otherwise.
     */
    public boolean hasRedWon() {


        if (verticalCheck(redPlayer) || horizontalCheck(redPlayer) || diagonalCheck(redPlayer)) {
            winner = redPlayer;
            loser = redPlayer;
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that checks if the player with the blue pucks has won the game.
     *
     * @return {@code true} if the player with the blue pucks has won the game, {@code false} otherwise.
     */
    public boolean hasBlueWon () {
        if (verticalCheck(bluePlayer) || horizontalCheck(bluePlayer) || diagonalCheck(bluePlayer)) {
            winner = bluePlayer;
            loser = redPlayer;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * A method that checks if the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    public boolean isGameOver () {
        if (hasBlueWon() || hasRedWon()) {
            return true;
        } else {
            return false;
        }
    }


}

package game.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class GameModel {

    private static final int RIGHT = 0;


    private static final int LEFT = 1;


    private static final int UP = 2;


    private static final int DOWN = 3;

    private Player bluePlayer;

    private Player redPlayer;

    private Player winner;

    private Player loser;

    private int[][] grid = {
            {1, 2, 1, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {2, 1, 2, 1}
    };


    public GameModel(int[][] grid) {
        this.grid = grid;
    }



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
                                log.info("Invalid direction, you can not move to that space!");
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
        kiir();
    }

    private void kiir() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j % 4 == 0) {
                    System.out.println("");
                }
                System.out.print(grid[i][j] + " ");
            }
        }
        System.out.println("");
    }

    public boolean horizontalCheck(Player currentPlayer) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == currentPlayer.getPlayerId()) {
                    if (j != 0 && j != grid[i].length-1 && grid[i][j-1] == currentPlayer.getPlayerId() && grid[i][j+1] == currentPlayer.getPlayerId()) {
                        System.out.println("NYERT");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean verticalCheck(Player currentPlayer) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == currentPlayer.getPlayerId()) {
                    if (i != 0 && i != grid.length-1 && grid[i - 1][j] == currentPlayer.getPlayerId() && grid[i + 1][j] == currentPlayer.getPlayerId()) {
                        System.out.println("NYERT");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean diagonalCheck(Player currentPlayer) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == currentPlayer.getPlayerId()) {
                    if (i != 0 && j != grid[i].length-1 && i != grid.length-1 && j !=0 && grid[i-1][j+1] == currentPlayer.getPlayerId() && grid[i+1][j-1] == currentPlayer.getPlayerId() ||
                            i != 0 && i != grid.length-1 && j != grid[i].length-1 && j !=0 && grid[i-1][j-1] == currentPlayer.getPlayerId() && grid[i+1][j+1] == currentPlayer.getPlayerId()
                    ) {
                        System.out.println("NYERT");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean hasRedWon() {


        if (verticalCheck(redPlayer) || horizontalCheck(redPlayer) || diagonalCheck(redPlayer)) {
            winner = redPlayer;
            loser = redPlayer;
            return true;
        } else {
            return false;
        }
    }

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

    public boolean isGameOver () {
        if (hasBlueWon() || hasRedWon()) {
            return true;
        } else {
            return false;
        }
    }


}

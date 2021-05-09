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

    private int p1steps;

    private int p2steps;

    private String p1name;

    private String p2name;

    private String winner;

    private int winnerSteps;

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



    public boolean isValidMove(int playerId, int row, int col, int direction) {

        boolean isValid = false;

        if (playerId == 1 && grid[row][col] == 2 || !(playerId == 1) && grid[row][col] == 1) {
            log.info("You can not move, because it is {}'s turn!", playerId);
            isValid = false;
        } else {
            try {
                if (playerId == 1) {
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
                    if (playerId == 2) {
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


    public void move(int playerId, int row, int col, int direction) {

        if (isValidMove(playerId, row, col, direction)) {
            switch (direction) {
                case RIGHT: {
                    grid[row][col + 1] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", playerId, row, col, row, col + 1);
                    break;
                }
                case LEFT: {
                    grid[row][col - 1] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", playerId, row, col, row, col - 1);
                    break;
                }
                case UP: {
                    grid[row - 1][col] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", playerId, row, col, row - 1, col);
                    break;
                }
                case DOWN: {
                    grid[row + 1][col] = grid[row][col];
                    grid[row][col] = 0;
                    log.info("{} moved from ({}, {}) to ({}, {}).", playerId, row, col, row + 1, col);
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

    public boolean horizontalCheck(int playerId) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == playerId) {
                    if (j != 0 && j != grid[i].length-1 && grid[i][j-1] == playerId && grid[i][j+1] == playerId) {
                        System.out.println("NYERT");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean verticalCheck(int playerId) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == playerId) {
                    if (i != 0 && i != grid.length-1 && grid[i - 1][j] == playerId && grid[i + 1][j] == playerId) {
                        System.out.println("NYERT");
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean diagonalCheck(int playerId) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == playerId) {
                    if (i != 0 && j != grid[i].length-1 && i != grid.length-1 && j !=0 && grid[i-1][j+1] == playerId && grid[i+1][j-1] == playerId ||
                            i != 0 && i != grid.length-1 && j != grid[i].length-1 && j !=0 && grid[i-1][j-1] == playerId && grid[i+1][j+1] == playerId
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


        if (verticalCheck(2) || horizontalCheck(2) || diagonalCheck(2)) {
            winner = p2name;
            winnerSteps = p2steps;
            return true;
        } else {
            return false;
        }
    }

    public boolean hasBlueWon () {
        if (verticalCheck(1) || horizontalCheck(1) || diagonalCheck(1)) {
            winner = p1name;
            winnerSteps = p1steps;
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

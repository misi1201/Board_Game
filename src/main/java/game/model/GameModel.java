package game.model;

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


    public GameModel() {
    }


    public GameModel(int[][] grid) {
        this.grid = grid;
    }


    public int getWinnerSteps() {
        return winnerSteps;
    }

    public String getWinner() {
        return winner;
    }

    public void setP1name(String p1name) {
        this.p1name = p1name;
    }

    public void setP2name(String p2name) {
        this.p2name = p2name;
    }

    public void setP1steps(int steps) {
        this.p1steps = steps;
    }

    public void setP2steps(int steps) {
        this.p2steps = steps;
    }

    public int getP1steps() {
        return p1steps;
    }

    public int getP2steps() {
        return p2steps;
    }

    public String getP1name() {
        return p1name;
    }

    public String getP2name() {
        return p2name;
    }


    public boolean isValidMove(int playerId, int row, int col, int direction) {

        boolean isValid = false;

        if (playerId == 1 && grid[row][col] == 2 || !(playerId == 1) && grid[row][col] == 1) {
            System.out.println("Nem a te köröd!");
            isValid = false;
        } else {
            try {
                if (playerId == 1) {
                    switch (direction) {
                        case RIGHT: {
                            if (grid[row][col + 1] == 0) {
                                isValid = true;
                            }
                            break;
                        }
                        case LEFT: {
                            if (grid[row][col - 1] == 0) {
                                isValid = true;
                            }
                            break;
                        }
                        case UP: {
                            if (grid[row - 1][col] == 0) {
                                isValid = true;
                            }
                            break;
                        }
                        case DOWN: {
                            if (grid[row + 1][col] == 0) {
                                isValid = true;
                            }
                            break;
                        }
                        default:
                            isValid = false;
                    }
                } else {
                    if (playerId == 2) {
                        switch (direction) {
                            case RIGHT: {
                                if (grid[row][col + 1] == 0) {
                                    isValid = true;
                                }
                                break;
                            }
                            case LEFT: {
                                if (grid[row][col - 1] == 0) {
                                    isValid = true;
                                }
                                break;
                            }
                            case UP: {
                                if (grid[row - 1][col] == 0) {
                                    isValid = true;
                                }
                                break;
                            }
                            case DOWN: {
                                if (grid[row + 1][col] == 0) {
                                    isValid = true;
                                }
                                break;
                            }
                            default:
                                isValid = false;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isValid = false;
            }
        }
        return isValid;
    }









}

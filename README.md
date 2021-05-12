# Board game

A board game played by 2 players.

## About

The game is turn-based and players can use blue and red pucks.
The first player uses the blue pucks, the second player uses the red pucks.

The board is a 5x4 grid. If a player manages to arrange 3 pucks with the same color horizontally, vertically or diagonally, then that player wins the game.

## Allowed moves

The blue player can move one blue puck on the field in the following directions: up,down,left, right
You cannot move diagonally.
You can only move on a field, if there is no puck on that specific field.

## Controls

The current player drags and drops a puck with a mouse.

### Run the project

```
mvn clean compile exec:java
```

### Generate docs

```
mvn site
```

### Add Clover coverage report

```
mvn -P clover site
```

### Requirements

To build the project, you will need Apache Maven, and JDK 11 or higher.









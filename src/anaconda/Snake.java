package anaconda;

import java.util.*;

public class Snake {
    
    int startCoordinate;
    int playerNumber;
    List<SnakePart> snakeParts;
    

    public Snake(int gridSize, int playerNumber) {
        snakeParts = new ArrayList<>();
        this.playerNumber = playerNumber;
        if (playerNumber == 1) {
            this.startCoordinate = ((gridSize) / 4) + 1;
            snakeParts = Arrays.asList(new Head(startCoordinate, startCoordinate, 1, 0),
                                       new Body(startCoordinate, startCoordinate+1), 
                                       new Body(startCoordinate, startCoordinate+2), 
                                       new Body(startCoordinate, startCoordinate+3));
        }
        if (playerNumber == 2) {
            this.startCoordinate = ((gridSize) / 5) * 4;
            snakeParts = Arrays.asList(new Head(startCoordinate, startCoordinate, -1, 0),
                                       new Body(startCoordinate, startCoordinate-1), 
                                       new Body(startCoordinate, startCoordinate-2), 
                                       new Body(startCoordinate, startCoordinate-3));
        }
    }
}
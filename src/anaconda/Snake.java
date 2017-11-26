package anaconda;

import java.util.*;

public class Snake {
    
    int sc;
    List<SnakePart> snakeParts;
    

    public Snake(int gridSize, int playerNumber) {
        snakeParts = new ArrayList<>();
        if (playerNumber == 1) {
            this.sc = ((gridSize) / 4) + 1;
            snakeParts = Arrays.asList(new Head(sc, sc, 1, 0),
                                       new Body(sc, sc+1), 
                                       new Body(sc, sc+2), 
                                       new Body(sc, sc+3));
        }
        if (playerNumber == 2) {
            this.sc = ((gridSize) / 4) * 3 + 1;
            snakeParts = Arrays.asList(new Head(sc, sc, -1, 0),
                                       new Body(sc, sc-1), 
                                       new Body(sc, sc-2), 
                                       new Body(sc, sc-3));
        }
    }
}
package anaconda;

import java.util.*;

public class Snake {
    
    int sc;
    List<SnakePart> snakeParts;
    

    public Snake(int gridSize) {
        this.sc = ((gridSize) / 2) + 1;
        snakeParts = new ArrayList<>();
        
        snakeParts = Arrays.asList(new Head(sc, sc, 1, 0),
                                   new Body(sc, sc+1), 
                                   new Body(sc, sc+2), 
                                   new Body(sc, sc+3));
    }
}
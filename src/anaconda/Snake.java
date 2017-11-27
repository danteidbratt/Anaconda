package anaconda;

import java.awt.Color;
import java.util.*;

public class Snake {

    int startCoordinate;
    int playerNumber;
    Color color;
    List<SnakePart> snakeParts;
    Head head;
    Body[] bodies = new Body[3];
    boolean alive;

    public Snake(int gridSize, int playerNumber) {
        snakeParts = new ArrayList<>();
        this.playerNumber = playerNumber;
        alive = true;
        if (playerNumber == 1) {
            color = Color.RED;
            this.startCoordinate = ((gridSize) / 4) + 1;
            head = new Head(startCoordinate, startCoordinate, 1, 0);
            snakeParts.add(head);
            for (int i = 0; i < bodies.length; i++) {
                bodies[i] = new Body(startCoordinate, startCoordinate + (i + 1));
                snakeParts.add(bodies[i]);
            }
        }
        if (playerNumber == 2) {
            color = Color.BLUE;
            this.startCoordinate = ((gridSize) / 5) * 4 + 1;
            head = new Head(startCoordinate, startCoordinate, -1, 0);
            snakeParts.add(head);
            for (int i = 0; i < bodies.length; i++) {
                bodies[i] = new Body(startCoordinate, startCoordinate - (i+1));
                snakeParts.add(bodies[i]);
            }
        }
    }
}

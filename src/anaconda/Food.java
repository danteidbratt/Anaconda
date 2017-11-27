package anaconda;

import java.awt.Color;

public class Food {

    int y;
    int x;
    Color color;

    public Food(int gridSize) {
        y = gridSize / 2 +1;
        x = gridSize / 2 +1;
        color = Color.GREEN;
    }
}
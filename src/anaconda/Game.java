package anaconda;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Game extends JPanel implements Runnable {

    Thread activity = new Thread(this);
    Snake snake;

    JPanel field = new JPanel();
    Square[][] grid;

    JLabel topSpace = new JLabel(" ");
    JLabel botSpace = new JLabel(" ");
    JLabel leftSpace = new JLabel(" ");
    JLabel rightSpace = new JLabel(" ");
    JLabel[] spaces = {topSpace, botSpace, leftSpace, rightSpace};

    int gridSize;
    Color backgroundColor;
    Color snakeColor;

    public Game(int gridSize, Color backgroundColor) {
        grid = new Square[gridSize + 2][gridSize + 2];
        this.gridSize = gridSize;
        this.backgroundColor = backgroundColor;
        snakeColor = Color.RED;
        snake = new Snake(gridSize, 1);
    }

    public void setPanel() {

        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        field.setLayout(new GridLayout(gridSize, gridSize, 5, 5));
        field.setBackground(Color.BLACK);
        field.setOpaque(true);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Square();
                grid[i][j].setBackground(backgroundColor);
                grid[i][j].setOpaque(true);
                grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                if (i > 0 && i < grid.length - 1 && j > 0 && j < grid.length - 1) {
                    field.add(grid[i][j]);
                }
            }
        }

        for (SnakePart sp : snake.snakeParts) {
            grid[sp.y][sp.x].occupied = true;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].occupied) {
                    grid[i][j].setBackground(snakeColor);
                }
            }
        }

        for (JLabel space : spaces) {
            space.setBackground(backgroundColor);
            space.setOpaque(true);
            space.setPreferredSize(new Dimension(8, 8));
        }

        add(field, BorderLayout.CENTER);
        add(topSpace, BorderLayout.NORTH);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(botSpace, BorderLayout.SOUTH);
    }

    @Override
    public void run() {
        java.util.List<Step> snakeNextFrame = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

            for (int i = snake.snakeParts.size() - 1; i > 0; i--) {
                grid[snake.snakeParts.get(i).y][snake.snakeParts.get(i).x].setBackground(backgroundColor);
                snake.snakeParts.get(i).moveBody(snake.snakeParts.get(i - 1));
            }
            snake.snakeParts.get(0).moveHead();

            for (int i = 0; i < snake.snakeParts.size(); i++) {
                grid[snake.snakeParts.get(i).y][snake.snakeParts.get(i).x].setBackground(snakeColor);
            }
        }
    }
}
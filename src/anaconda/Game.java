package anaconda;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {

    Snake p1;
    Snake p2;
    SnakeThread st1;
    SnakeThread st2;
    Snake[] snakes = new Snake[2];
    Food food1;
    Food food2;
    int fps;

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

    public Game(int gridSize, Color backgroundColor, int fps) {
        grid = new Square[gridSize + 2][gridSize + 2];
        this.gridSize = gridSize;
        this.backgroundColor = backgroundColor;
        this.fps = fps;
        snakeColor = Color.RED;
        p1 = new Snake(gridSize, 1);
        p2 = new Snake(gridSize, 2);
        snakes[0] = p1;
        snakes[1] = p2;
        st1 = new SnakeThread(p1);
        st2 = new SnakeThread(p2);
        food1 = new Food((gridSize / 4) + 1, (gridSize / 5) * 4 + 1);
        food2 = new Food((gridSize / 5) * 4 + 1, (gridSize / 4) + 1);
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

        // Placera ut första ormen
        for (int i = 0; i < p1.snakeParts.size(); i++) {
            grid[p1.snakeParts.get(i).y][p1.snakeParts.get(i).x].setBackground(p1.color);
        }

        // Placera ut andra ormen
        for (int i = 0; i < p2.snakeParts.size(); i++) {
            grid[p2.snakeParts.get(i).y][p2.snakeParts.get(i).x].setBackground(p2.color);
        }

        // Placera ut maten
        grid[food1.y][food1.x].setBackground(food1.color);
        grid[food2.y][food2.x].setBackground(food2.color);

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

    public class SnakeThread extends Thread {

        Snake hero;
        Snake enemy;

        public SnakeThread(Snake snake) {
            this.hero = snake;
        }

        @Override
        public void run() {
            java.util.List<Step> snakeNextFrame = new ArrayList<>();
            boolean alive = true;
            while (alive) {
                try {
                    Thread.sleep(1000 / fps);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

                // Ät mat
                if (checkIfEqual(hero.snakeParts.get(0), food1)) {
                    spawnFood(food1);
                    grow(hero);
                }
                if (checkIfEqual(hero.snakeParts.get(0), food2)) {
                    spawnFood(food2);
                    grow(hero);
                }

                // Flytta kroppen
                for (int i = hero.snakeParts.size() - 1; i > 0; i--) {
                    grid[hero.snakeParts.get(i).y][hero.snakeParts.get(i).x].setBackground(backgroundColor);
                    hero.snakeParts.get(i).moveBody(hero.snakeParts.get(i - 1));
                }

                // Flytta huvudet
                hero.snakeParts.get(0).moveHead();
                teleport(hero.snakeParts.get(0));

                // Uppdatera positionen
                for (int i = 0; i < hero.snakeParts.size(); i++) {
                    grid[hero.snakeParts.get(i).y][hero.snakeParts.get(i).x].setBackground(hero.color);
                }

                if (hero.playerNumber == 1) {
                    enemy = p2;
                } else {
                    enemy = p1;
                }

                for (int i = 1; i < enemy.snakeParts.size(); i++) {
                    if (hero.snakeParts.get(0).x == enemy.snakeParts.get(i).x
                            && hero.snakeParts.get(0).y == enemy.snakeParts.get(i).y) {
                        alive = false;
                    }
                }

            }
            System.out.println("död");
            for (int i = 1; i < hero.snakeParts.size(); i++) {
                grid[hero.snakeParts.get(i).y][hero.snakeParts.get(i).x].setBackground(backgroundColor);
                hero.snakeParts.get(i).x = 0;
                hero.snakeParts.get(i).y = 0;
            }
        }
    }

    public void spawnFood(Food food) {
        Random random = new Random();
        int randY;
        int randX;
        do {
            randY = random.nextInt(gridSize) + 1;
            randX = random.nextInt(gridSize) + 1;
        } while (!checkIfEmpty(randY, randX));
        food.y = randY;
        food.x = randX;
        grid[food.y][food.x].setBackground(food1.color);
    }

    public boolean checkIfEmpty(int y, int x) {
        for (int i = 0; i < snakes.length; i++) {
            for (int j = 0; j < snakes[i].snakeParts.size(); j++) {
                if (snakes[i].snakeParts.get(j).x == x && snakes[i].snakeParts.get(j).y == y) {
                    return false;
                }
            }
        }
        return (food1.y != y && food1.x != x && food2.x != x && food2.y != y);
    }

    public boolean checkIfEqual(SnakePart snakePart, Food food) {
        return (snakePart.x == food.x && snakePart.y == food.y);
    }

    public void teleport(SnakePart head) {
        if (head.x == 0) {
            head.x = gridSize;
        }
        if (head.x == gridSize + 1) {
            head.x = 1;
        }
        if (head.y == 0) {
            head.y = gridSize;
        }
        if (head.y == gridSize + 1) {
            head.y = 1;
        }
    }
    
    public void grow(Snake snake){
        int v;
        int h;
        int index = snake.snakeParts.size()-1;
        Body body = new Body(snake.snakeParts.get(snake.snakeParts.size()-1).y,
                             snake.snakeParts.get(snake.snakeParts.size()-1).x);
        snake.snakeParts.add(body);
    }
}

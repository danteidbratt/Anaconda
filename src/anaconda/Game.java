package anaconda;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Game extends JPanel{

    Snake p1;
    Snake p2;
    SnakeThread st1;
    SnakeThread st2;
    Snake[] snakes = new Snake[2];

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
        p1 = new Snake(gridSize, 1);
        p2 = new Snake(gridSize, 2);
        snakes[0] = p1;
        snakes[1] = p2;
        st1 = new SnakeThread(p1);
        st2 = new SnakeThread(p2);
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

        for (int i = 0; i < p1.snakeParts.size(); i++) {
            grid[p1.snakeParts.get(i).y][p1.snakeParts.get(i).x].setBackground(snakeColor);
        }

        for (int i = 0; i < p2.snakeParts.size(); i++) {
            grid[p2.snakeParts.get(i).y][p2.snakeParts.get(i).x].setBackground(snakeColor);
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
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                
                // Flytta kroppen
                for (int i = hero.snakeParts.size() - 1; i > 0; i--) {
                    grid[hero.snakeParts.get(i).y][hero.snakeParts.get(i).x].setBackground(backgroundColor);
                    hero.snakeParts.get(i).moveBody(hero.snakeParts.get(i - 1));
                }
                
                // Flytta huvudet
                hero.snakeParts.get(0).moveHead();
                
                // Uppdatera positionen
                for (int i = 0; i < hero.snakeParts.size(); i++) {
                    grid[hero.snakeParts.get(i).y][hero.snakeParts.get(i).x].setBackground(snakeColor);
                }
                
                if(hero.playerNumber == 1)
                    enemy = p2;
                else
                    enemy = p1;
                
                for (int i = 1; i < enemy.snakeParts.size(); i++) {
                    if (hero.snakeParts.get(0).x == enemy.snakeParts.get(i).x &&
                        hero.snakeParts.get(0).y == enemy.snakeParts.get(i).y)
                        alive = false;
                }
                
                if(hero.snakeParts.get(0).x < 1 || hero.snakeParts.get(0).x > gridSize-2 ||
                   hero.snakeParts.get(0).y < 1 || hero.snakeParts.get(0).y > gridSize-2)
                    alive = false;
            }
            System.out.println("död");
            for (int i = 1; i < hero.snakeParts.size(); i++) {
                grid[hero.snakeParts.get(i).y][hero.snakeParts.get(i).x].setBackground(backgroundColor);
            }
        }
    }
}
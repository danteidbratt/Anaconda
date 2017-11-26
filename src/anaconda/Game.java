package anaconda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game extends JPanel implements Runnable{
    
    JPanel field = new JPanel();
    Square[][] grid;
    
    JLabel topSpace = new JLabel(" ");
    JLabel botSpace = new JLabel(" ");
    JLabel leftSpace = new JLabel(" ");
    JLabel rightSpace = new JLabel(" ");
    JLabel[] spaces = {topSpace, botSpace, leftSpace, rightSpace};

    int gridSize;
    Color backgroundColor;

    public Game(int gridSize, Color backgroundColor) {
         grid = new Square[gridSize][gridSize];
         this.gridSize = gridSize;
         this.backgroundColor = backgroundColor;
    }
    
    public void setPanel(){
        setLayout(new BorderLayout());
        field.setLayout(new GridLayout(gridSize, gridSize, 5, 5));
        field.setBackground(Color.BLACK);
        field.setOpaque(true);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        
        for (Square[] row : grid) {
            for (Square square : row) {
                square = new Square();
                square.setBackground(backgroundColor);
                square.setOpaque(true);
                square.setHorizontalAlignment(SwingConstants.CENTER);
                field.add(square);
            }
        }
        
//        grid[0][0].occupied = true;
        
        for (Square[] row : grid) {
            for (Square square : row) {
                if (square.occupied)
                    square.setBackground(Color.RED);
            }
        }
        
        for (JLabel space : spaces) {
            space.setBackground(backgroundColor);
            space.setOpaque(true);
            space.setPreferredSize(new Dimension(40, 40));
        }
        add(field, BorderLayout.CENTER);
        add(topSpace, BorderLayout.NORTH);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(botSpace, BorderLayout.SOUTH);
    }
    
    @Override
    public void run() {
    }
}
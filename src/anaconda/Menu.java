package anaconda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel{
    
    JPanel topPanel = new JPanel();
    JLabel topSpace = new JLabel("");
    JLabel logo = new JLabel("- Anaconda -");
    JPanel centerPanel = new JPanel();
    JButton startButton = new JButton("Start Game");
    JButton optionsButton = new JButton("Options");
    JButton exitButton = new JButton("Exit");
    JButton[] buttons = {startButton, optionsButton, exitButton};
    JLabel botSpace = new JLabel(" ");
    JLabel leftSpace = new JLabel(" ");
    JLabel rightSpace = new JLabel(" ");
    JLabel[] spaces = {topSpace, botSpace, leftSpace, rightSpace};
    Color backgroundColor;
    Color logoColor = Color.RED;

    public Menu(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public void setPanel(){
        setLayout(new BorderLayout(0, 30));
        setBackground(backgroundColor);
        for (JLabel space : spaces) {
            space.setBackground(backgroundColor);
            space.setOpaque(true);
            space.setPreferredSize(new Dimension(100, 0));
        }
        topSpace.setPreferredSize(new Dimension(0, 30));
        botSpace.setPreferredSize(new Dimension(0, 50));
        
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(backgroundColor);
        topPanel.setPreferredSize(new Dimension(0, 150));
        topPanel.add(topSpace, BorderLayout.NORTH);
        topPanel.add(logo, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(3, 1, 0, 10));
        centerPanel.setBackground(backgroundColor);
        logo.setBackground(backgroundColor);
        logo.setOpaque(true);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setFont(new Font("SansSerif", 1, 70));
        logo.setForeground(logoColor);
        for (JButton button : buttons) {
            button.setFont(new Font("SansSerif", 2, 40));
            button.setForeground(Color.BLACK);
            centerPanel.add(button);
        }
        add(topPanel, BorderLayout.NORTH);
        add(leftSpace, BorderLayout.WEST);
        add(rightSpace, BorderLayout.EAST);
        add(botSpace, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    public void addActionListeners(ActionListener al){
        for (JButton button : buttons) {
            button.addActionListener(al);
        }
    }
}
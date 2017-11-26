package anaconda;

import javax.swing.JFrame;

public class Frame extends JFrame{
    
    public void setFrame(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
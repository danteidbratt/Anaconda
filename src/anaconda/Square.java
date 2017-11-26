package anaconda;

import javax.swing.*;

public class Square extends JLabel{
    
    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean occupied;

    public Square() {
        occupied = false;
    }
    
    public boolean isOccupied(){
        return occupied;
    }
    
}

package anaconda;

import javax.swing.*;

public class Square extends JLabel{
    
    boolean occupied;
    String direction = "";

    public Square() {
        occupied = false;
    }
    
    public boolean isOccupied(){
        return occupied;
    }
    
}

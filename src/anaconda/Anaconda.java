package anaconda;

import java.awt.Color;

public class Anaconda {
    
    Frame frame;
    Menu menu;
    Game game;
    ActionHandler actionHandler;
    
    int gridSize;
    Color backgroundColor;
    int fps;
    

    public Anaconda() {
        // Settings
        backgroundColor = new Color(50, 50, 50);
        gridSize = 15;
        fps = 10;
        
        frame = new Frame();
        menu = new Menu(backgroundColor);
        game = new Game(gridSize, backgroundColor, fps);
        actionHandler = new ActionHandler(this);
    }

    public void program() {
        menu.setPanel();
        menu.addActionListeners(actionHandler);
        frame.setFrame();
        frame.add(menu);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        Anaconda anaconda = new Anaconda();
        anaconda.program();
    }
}
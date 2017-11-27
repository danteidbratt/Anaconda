package anaconda;

import java.awt.Color;

public class Anaconda {
    
    Color backgroundColor;
    Frame frame;
    Menu menu;
    Game game;
    ActionHandler actionHandler;

    public Anaconda() {
        backgroundColor = Color.DARK_GRAY;
        frame = new Frame();
        menu = new Menu(backgroundColor);
        game = new Game(11, backgroundColor, 5);
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
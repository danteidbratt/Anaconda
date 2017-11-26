package anaconda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{

    Anaconda a;
    public ActionHandler(Anaconda a) {
        this.a = a;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == a.menu.startButton) {
            a.game.setPanel();
            a.frame.remove(a.menu);
            a.frame.add(a.game);
        }
        
        if (e.getSource() == a.menu.exitButton) {
            System.exit(0);
        }
        a.frame.revalidate();
        a.frame.repaint();
    }
}
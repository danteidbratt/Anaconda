package anaconda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
            a.frame.addKeyListener(ka);
            a.frame.requestFocus();
            a.game.st1.start();
            a.game.st2.start();
        }
        
        if (e.getSource() == a.menu.exitButton) {
            System.exit(0);
        }
        a.frame.revalidate();
        a.frame.repaint();
    }
    
    KeyAdapter ka = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent x) {
            switch (x.getKeyChar()) {
                case 'w':
                    a.game.p1.snakeParts.get(0).setDirection(-1, 0);
                    break;
                case 's':
                    a.game.p1.snakeParts.get(0).setDirection(1, 0);
                    break;
                case 'a':
                    a.game.p1.snakeParts.get(0).setDirection(0, -1);
                    break;
                case 'd':
                    a.game.p1.snakeParts.get(0).setDirection(0, 1);
                    break;
                case 'p':
                    a.game.p2.snakeParts.get(0).setDirection(-1, 0);
                    break;
                case 'ö':
                    a.game.p2.snakeParts.get(0).setDirection(1, 0);
                    break;
                case 'l':
                    a.game.p2.snakeParts.get(0).setDirection(0, -1);
                    break;
                case 'ä':
                    a.game.p2.snakeParts.get(0).setDirection(0, 1);
                    break;
                default:
                    break;
            }
        }
    };
}
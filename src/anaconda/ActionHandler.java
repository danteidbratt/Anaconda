package anaconda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActionHandler implements ActionListener{

    char forbidden;
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
        public void keyTyped(KeyEvent x) {
            switch (x.getKeyChar()) {
                case 'w':
                    if(forbidden != 'w'){
                        a.game.p1.snakeParts.get(0).setDirection(-1, 0);
                        forbidden = 'w';
                    }
                    break;
                case 's':
                    if(forbidden != 's'){
                        a.game.p1.snakeParts.get(0).setDirection(1, 0);
                        forbidden = 's';
                    }
                    break;
                case 'a':
                    if(forbidden != 'a'){
                    a.game.p1.snakeParts.get(0).setDirection(0, -1);
                    forbidden = 'a';
                    }
                    break;
                case 'd':
                    if(forbidden != 'd'){
                    a.game.p1.snakeParts.get(0).setDirection(0, 1);
                    forbidden = 'd';
                    }
                    break;
                case 'p':
                    if(forbidden != 'p'){
                    a.game.p2.snakeParts.get(0).setDirection(-1, 0);
                    forbidden = 'p';
                    }
                    break;
                case 'ö':
                    if(forbidden != 'ö'){
                    a.game.p2.snakeParts.get(0).setDirection(1, 0);
                    forbidden = 'ö';
                    }
                    break;
                case 'l':
                    if(forbidden != 'l'){
                    a.game.p2.snakeParts.get(0).setDirection(0, -1);
                    forbidden = 'l';
                    }
                    break;
                case 'ä':
                    if(forbidden != 'ä'){
                    a.game.p2.snakeParts.get(0).setDirection(0, 1);
                    forbidden = 'ä';
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
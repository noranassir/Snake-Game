import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    static final int PANEL_WIDTH = 500;
    static final int PANEL_HEIGHT = 500;
    static final int COMPONENT_SIZE = 25;
    static final int GAME_UNIT =(PANEL_WIDTH*PANEL_HEIGHT)/COMPONENT_SIZE;
    int x[] = new int[GAME_UNIT];
    int y[] = new int[GAME_UNIT];
    Timer timer;
    boolean running = false;

    GamePanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(new AKeyAdapter());
        startGame();
    }

    public void draw(Graphics graphics){

    }
    public void components(Graphics graphics){

    }
    public void generateFood(){

    }
    public void move(){

    }
    public void checkCollision(){

    }
    public void checkFood(){

    }
    public void startGame(){
        generateFood();
    }
    public void gameOver(){

    }
    public class AKeyAdapter extends KeyAdapter{
        public void pressedKey(KeyEvent event){

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

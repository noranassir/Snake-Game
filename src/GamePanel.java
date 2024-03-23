import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int PANEL_WIDTH = 500;
    static final int PANEL_HEIGHT = 500;
    static final int COMPONENT_SIZE = 10;
    static final int GAME_UNIT =(PANEL_WIDTH*PANEL_HEIGHT)/COMPONENT_SIZE;
    int x[] = new int[GAME_UNIT];
    int y[] = new int[GAME_UNIT];
    Timer timer;
    boolean running = false;
    int delay = 50;
    int foodX;
    int foodY;
    int bodyComponent = 10;
    char dirction = 'D';

    Random random = new Random();

    GamePanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(new AKeyAdapter());
        startGame();
    }

    public void draw(Graphics g){
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color randomColor = new Color(red, green, blue);
        g.setColor(randomColor);
        g.fillOval(foodX,foodY, COMPONENT_SIZE, COMPONENT_SIZE);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void generateFood(){
        foodX = random.nextInt(PANEL_WIDTH/COMPONENT_SIZE) * COMPONENT_SIZE;
        foodY = random.nextInt(PANEL_HEIGHT/COMPONENT_SIZE) * COMPONENT_SIZE;

    }
    public void move(){


    }
    public void checkCollision(){

    }
    public void checkFood(){

    }
    public void startGame(){
        generateFood();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
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

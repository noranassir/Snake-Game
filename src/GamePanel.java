import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 500;
    private static final int PANEL_HEIGHT = 500;
    private static final int COMPONENT_SIZE = 10;
    private static final int GAME_UNIT =(PANEL_WIDTH*PANEL_HEIGHT)/COMPONENT_SIZE;
    private int x[] = new int[GAME_UNIT];
    private int y[] = new int[GAME_UNIT];
    private Timer timer;
    private boolean running = false;
    private int delay = 50;
    private int foodX;
    private int foodY;
    private int bodyComponents = 10;
    private char direction = 'R';

    Random random = new Random();

    GamePanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(new AKeyAdapter());
        startGame();
    }

    public void draw(Graphics g){

        //draw food
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color randomColor = new Color(red, green, blue);
        g.setColor(randomColor);
        g.fillOval(foodX,foodY, COMPONENT_SIZE, COMPONENT_SIZE);

        //draw body
        for(int i = 0; i < bodyComponents; i++){
            if(i == 0){
                g.setColor(Color.green);
                g.fillRect(x[i],y[i], COMPONENT_SIZE, COMPONENT_SIZE);
            }
            else {
                g.setColor(new Color(128,255,0));
                g.fillRect(x[i],y[i], COMPONENT_SIZE, COMPONENT_SIZE);
            }
        }

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
        for(int i = bodyComponents - 1; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - COMPONENT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + COMPONENT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - COMPONENT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + COMPONENT_SIZE;
                break;
        }
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
        if(running){
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }
}

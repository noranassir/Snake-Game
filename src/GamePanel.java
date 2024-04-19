/**
 * The GamePanel class represents the main panel for the snake game.
 * It extends JPanel and implements the ActionListener interface.
 * This class manages the game logic, drawing components, and user input.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    // Constants for panel dimensions and component size
    private static final int PANEL_WIDTH = 500;
    private static final int PANEL_HEIGHT = 500;
    private static final int COMPONENT_SIZE = 10;
    // Calculate the total game units based on panel dimensions and component size
    private static final int GAME_UNIT = (PANEL_WIDTH * PANEL_HEIGHT) / COMPONENT_SIZE;
    // Arrays to store coordinates of snake segments
    private int x[] = new int[GAME_UNIT];
    private int y[] = new int[GAME_UNIT];
    // Timer for game loop
    private Timer timer;
    // Flag to track game running state
    private boolean running = false;
    // Delay for game loop
    private int delay = 50;
    // Coordinates for food
    private int foodX;
    private int foodY;
    // Initial number of body components for the snake
    private int bodyComponents = 10;
    // Direction of snake movement
    private char direction = 'R';
    // Score tracking
    private int eatenFood = 0;
    // Random number generator
    Random random = new Random();

    /*
     * Constructor for GamePanel class.
     * Sets up the panel dimensions, background color, and initializes the game.
     */
    GamePanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(new AKeyAdapter());
        this.setFocusable(true); // Allow the panel to receive keyboard input
        this.requestFocusInWindow(); // Request focus for the panel
        startGame();
    }
    
    /**
     * Method to draw game components on the panel.
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g){
        if(running) {

            //Draw food
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color randomColor = new Color(red, green, blue);
            g.setColor(randomColor);
            g.fillOval(foodX, foodY, COMPONENT_SIZE, COMPONENT_SIZE);

            //Draw snake
            for (int i = 0; i < bodyComponents; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], COMPONENT_SIZE, COMPONENT_SIZE);
                } else {
                    g.setColor(new Color(128, 255, 0));
                    g.fillRect(x[i], y[i], COMPONENT_SIZE, COMPONENT_SIZE);
                }
            }
            // Display score
            g.setColor(Color.white);
            g.setFont(new Font("Jazz LET",Font.PLAIN, 15));
            FontMetrics fontMetrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + eatenFood,PANEL_WIDTH - fontMetrics.stringWidth("Score: ") - 15, 15);
        }
        else{
            // Game over screen
            gameOver(g);
        }

    }
    
    /**
     * Overrides the paintComponent method to draw components on the panel.
     * @param g The Graphics object used for drawing.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Generates food at random coordinates within the panel.
     */
    public void generateFood(){
        foodX = random.nextInt(PANEL_WIDTH/COMPONENT_SIZE) * COMPONENT_SIZE;
        foodY = random.nextInt(PANEL_HEIGHT/COMPONENT_SIZE) * COMPONENT_SIZE;
    }

    /**
     * Moves the snake by updating its coordinates based on the current direction.
     */
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

    /**
     * Checks for collisions with the snake's body and panel borders.
     */
    public void checkCollision(){
        for(int i = bodyComponents - 1; i > 0; i--){
            if(x[0] == x[i] && y[0] == y[i]){
                running = false;
            }
        }

        if(x[0] < 0){
            running=false;
        }
        if(x[0] > PANEL_WIDTH){
            running=false;
        }
        if(y[0] < 0){
            running=false;
        }
        if(y[0] > PANEL_HEIGHT){
            running=false;
        }
    }
        
    /**
    * Checks if the snake has eaten food, and updates the game state accordingly.
     */
    public void checkFood(){
        if(x[0] == foodX && y[0] == foodY){
            bodyComponents++;
            eatenFood++;
            generateFood();
        }
    }
    
    /**
     * Starts the game by initializing food position and starting the timer.
     */
    public void startGame(){
        generateFood();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }
    /**
     * Displays the game over screen.
     * @param g The Graphics object used for drawing.
     */
    public void gameOver(Graphics g){

        g.setColor(Color.white);
        g.setFont(new Font("Jazz LET",Font.BOLD, 50));
        FontMetrics fontMetrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(PANEL_WIDTH - fontMetrics1.stringWidth("Game Over"))/2, PANEL_HEIGHT/2);

        g.setColor(Color.white);
        g.setFont(new Font("Jazz LET",Font.BOLD, 25));
        FontMetrics fontMetrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + eatenFood,(PANEL_WIDTH - fontMetrics2.stringWidth("Game Over"))/2, PANEL_HEIGHT/2 + fontMetrics1.getHeight() ) ;

    }
    
    /**
     * Key adapter for handling keyboard input.
     */
    public class AKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event){
            switch (event.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }

    /**
     * Handles the game loop by moving the snake and checking game conditions.
     * @param e The ActionEvent triggered by the timer.
     */
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

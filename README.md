Snake Game
This is a simple Snake game implemented in Java using Swing for the graphical interface. The game features a snake that moves around the screen, eating food and growing longer. The objective of the game is to eat as much food as possible without colliding with the snake's own body or the walls of the game area.

Features:
Snake movement controlled by arrow keys.
Random generation of food items.
Collision detection with walls and the snake's own body.
Score tracking.

How to Play:
Run the GamePanel class to start the game.
Use the arrow keys to control the direction of the snake.
Eat the food items to increase your score.
Avoid colliding with the walls or the snake's body.

Code Overview:
The game consists of two main classes:

GamePanel: This class extends JPanel and represents the game area where the snake and food are drawn. It handles user input for controlling the snake's movement and updates the game state accordingly.

GameFrame: This class extends JFrame and is responsible for creating the main window of the game. It adds an instance of GamePanel to the frame and sets up the window properties.


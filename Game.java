import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener {
    private StartScreen startScreenPanel;
    int width, height, directionX, directionY;
    int tileSize = 25;
    Tile snakeHead, food;
    ArrayList<Tile> snakeBody;
    Timer gameLoop;
    boolean gameOver = false;

    class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Game(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(this.width, this.height));
        setBackground(Color.green.darker().darker());
        addKeyListener(this);
        setFocusable(true);
        startScreenPanel = new StartScreen(this);
        add(startScreenPanel);
        snakeHead = new Tile(10,10);
        snakeBody = new ArrayList<Tile>();
        food = new Tile(10, 10);

        food.x = (int) (Math.random() * width/tileSize);
        food.y = (int) (Math.random() * height/tileSize);

        directionX = 0;
        directionY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }
    public void startGame() {
        gameLoop.start(); // Start the game timer
        requestFocus(); // Request focus for keyboard input
        startScreenPanel.setVisible(false); // Hide start screen panel
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!gameOver)
            draw(g);
    }

    public void draw(Graphics g) {
        //map
        g.setColor(Color.orange.darker().darker().darker());
        for(int k=0; k < this.width/tileSize; k++) {
            g.drawLine(k*tileSize, 0, k*tileSize, 500);
            g.drawLine(0, k*tileSize, 500, k*tileSize);
        }

        //food
        g.setColor(Color.red);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);

        //head
        g.setColor(Color.black);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize);

        //body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
        }
    }

    public void move() {
        if (isCollision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            do{
                food.x = (int) (Math.random() * width/tileSize);
                food.y = (int) (Math.random() * height/tileSize);
                System.out.println(food.x+tileSize + " " + food.y+tileSize);
            }while (food.x*tileSize < 25 || food.x*tileSize > 475 ||food.y*tileSize < 25 || food.y*tileSize > 475);

        }

        for (int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        snakeHead.x += directionX;
        snakeHead.y += directionY;

        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            if (isCollision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }
        if (snakeHead.x*tileSize < 0 || snakeHead.x*tileSize > width || snakeHead.y*tileSize < 0 || snakeHead.y*tileSize > height)
            gameOver = true;
    }

    public boolean isCollision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && directionY != 1) {
            directionX = 0;
            directionY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && directionY != -1) {
            directionX = 0;
            directionY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && directionX != 1) {
            directionX = -1;
            directionY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && directionX != -1) {
            directionX = 1;
            directionY = 0;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}


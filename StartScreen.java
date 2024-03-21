import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartScreen extends JPanel implements MouseListener {

    private final Game game;

    public StartScreen(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(game.width, game.height));
        setBackground(Color.black);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Snake Game", game.width / 2 - 80, game.height / 2);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Click 'Start' to begin!", game.width / 2 - 70, game.height / 2 + 50);

        g.setColor(Color.green);
        g.fillRect(game.width / 2 - 50, game.height / 2 + 100, 100, 30);
        g.setColor(Color.black);
        g.drawString("Start", game.width / 2 - 35, game.height / 2 + 125);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (x >= game.width / 2 - 50 && x <= game.width / 2 + 50 && y >= game.height / 2 + 100 && y <= game.height / 2 + 130) {
            game.startGame();
            setVisible(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}

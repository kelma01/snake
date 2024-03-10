import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Game game = new Game(500,500);
        frame.add(game);
        frame.pack();
        game.requestFocus();

    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

abstract class Game extends JFrame implements Runnable {
    private int level;
    public abstract JComponent createMainFrame();
    public abstract void init();
    public abstract void update();
    public abstract void gameWon();
    public abstract void gameOver();

    public Game() {
        EventQueue.invokeLater(this);
    }

    @Override
    public void run() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createMainFrame());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
    }

    protected void reset() {
        requestFocusInWindow();
    }
}

import javax.swing.*;
import java.awt.*;

/*
to be able to create a new game, we need to have a init function and a mainframe we create using the createMainFrame method
 */

abstract class Game extends JFrame implements Runnable {
    public abstract JComponent createMainFrame();
    public abstract void init();

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

package Controller;

import Model.MapModel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


/**
 * This class is responsible for handling the key events.
 */
public class KeyController extends MoveHandler{

    public KeyController(MapModel mapModel) {
        super(mapModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            moveUp();
        }
        if(code == KeyEvent.VK_A) {
            moveLeft();
        }
        if(code == KeyEvent.VK_D) {
            moveRight();
        }
        if(code == KeyEvent.VK_S) {
            moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

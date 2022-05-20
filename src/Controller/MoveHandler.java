package Controller;

import Model.MapModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Handels the movement of the player, and the different events that can occur from the user.
 */
public abstract class MoveHandler implements KeyListener, ActionListener {
    MapModel mapModel;

    public MoveHandler(MapModel mapModel){
        this.mapModel = mapModel;
    }

    public void moveUp() {
        mapModel.movePlayerUp();
    }

    public void moveDown() {
        mapModel.movePlayerDown();
    }

    public void moveLeft() {
        mapModel.movePlayerLeft();
    }

    public void moveRight() {
        mapModel.movePlayerRight();
    }

}

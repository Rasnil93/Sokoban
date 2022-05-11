package Controller;

import Model.MapModel;
import View.MapView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MapController extends KeyAdapter implements PropertyChangeListener {
    private MapModel mapModel;
    private MapView mapView;
    public boolean up,down,left,right;

    public MapController(MapModel mapModel, MapView mapView) {
       this.mapModel = mapModel;
       this.mapView = mapView;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            up = true;
            propertyChange(new PropertyChangeEvent(this, "up", false, true));
        }
        if(code == KeyEvent.VK_A) {
            left = true;
            propertyChange(new PropertyChangeEvent(this, "left", false, true));
        }
        if(code == KeyEvent.VK_D) {
            right = true;
            propertyChange(new PropertyChangeEvent(this, "right", false, true));
        }
        if(code == KeyEvent.VK_S) {
            down = true;
            propertyChange(new PropertyChangeEvent(this, "down", false, true));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("up")) {
            mapModel.movePlayerUp();
        }else if (evt.getPropertyName().equals("down")) {
            mapModel.movePlayerDown();
        }else if (evt.getPropertyName().equals("left")) {
            mapModel.movePlayerLeft();
        }else if (evt.getPropertyName().equals("right")) {
            mapModel.movePlayerRight();
        }else if (evt.getPropertyName().equals("restart")) {
            mapView.propertyChange(new PropertyChangeEvent(this, "restart", false, true));
        }
    }
}

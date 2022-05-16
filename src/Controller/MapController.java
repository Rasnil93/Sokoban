package Controller;

import Model.MapModel;
import Model.ModelEntity;
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
            mapModel.movePlayerUp();
        }
        if(code == KeyEvent.VK_A) {
            left = true;
            mapModel.movePlayerLeft();
        }
        if(code == KeyEvent.VK_D) {
            right = true;
            mapModel.movePlayerRight();
        }
        if(code == KeyEvent.VK_S) {
            down = true;
            mapModel.movePlayerDown();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("upAccepted")) {
            mapView.repaintMap();
        }else if (evt.getPropertyName().equals("downAccepted")) {
            mapView.repaintMap();
        }else if (evt.getPropertyName().equals("leftAccepted")) {
            mapView.repaintMap();
        }else if (evt.getPropertyName().equals("rightAccepted")) {
            mapView.repaintMap();
        }else if (evt.getPropertyName().equals("restart")) {
            mapView.restartMap();
        }else if (evt.getPropertyName().equals("placePlayer")) {
            mapView.placePlayer();
        }
    }
}

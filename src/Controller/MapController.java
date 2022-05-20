package Controller;

import Model.MapModel;
import View.MapView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handles the events from the MapView and MapModel.
 */
public class MapController implements PropertyChangeListener {
    private MapModel mapModel;
    private MapView mapView;

    public MapController(MapModel mapModel, MapView mapView) {
       this.mapModel = mapModel;
       this.mapView = mapView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "upAccepted" -> mapView.repaintMap();
            case "downAccepted" -> mapView.repaintMap();
            case "leftAccepted" -> mapView.repaintMap();
            case "rightAccepted" -> mapView.repaintMap();
            case "restart" -> mapView.restartMap();
            case "placePlayer" -> mapView.placePlayer();
        }
    }
}

package Controller;

import Model.TileModel;
import View.Tile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileController implements PropertyChangeListener {
    private Tile tileView;
    private TileModel tileModel;

    public TileController(TileModel tileModel, Tile tile){
        this.tileView = tile;
        this.tileModel = tileModel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}

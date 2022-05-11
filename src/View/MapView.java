package View;

import Model.MapModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class MapView extends JPanel{
    private MapModel mapModel;

    public MapView(MapModel mapModel) {
        this.mapModel = mapModel;
        drawMap(mapModel.getMap());
    }

    public JPanel drawMap(ArrayList<ArrayList<Tile>> mapGrid) {
        int rows = mapGrid.size();
        int cols = mapGrid.get(0).size();
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                add(mapGrid.get(i).get(j));
            }
        }
        setPreferredSize(new Dimension(cols * 32, rows * 32));
        repaint();
        revalidate();
        return this;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "restart":
                removeAll();
                drawMap(mapModel.getMap());
                break;
        }
    }

}

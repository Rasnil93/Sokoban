package View;

import Model.MapModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapView extends JPanel{
    private MapModel mapModel;
    private Tile occupiedTile;
    private Tile playerTile;
    private ArrayList<ArrayList<Tile>> mapGrid;

    public MapView(MapModel mapModel) {
        this.mapModel = mapModel;
        drawMap(mapModel.getMap());
        placePlayer();
    }

    public void placePlayer() {
        int playerY = mapModel.getPlayerY();
        int playerX = mapModel.getPlayerX();
        if (playerTile == null){
            Tile playerTile = new Tile(9);
            this.occupiedTile  = mapGrid.get(playerY).get(playerX);
            this.playerTile = playerTile;
        } else {
            occupiedTile = mapGrid.get(playerY).get(playerX);
        }
        mapGrid.get(playerY).get(playerX).setTileImage(playerTile.getTileImage());
    }

    public void drawMap(ArrayList<ArrayList<Integer>> mapGrid) {
        if (mapGrid != null) {
            removeAll();
        }
        int rows = mapModel.getHeightOfMap();
        int cols = mapModel.getWidthOfMap();
        setLayout(new GridLayout(rows, cols));
        this.mapGrid = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Tile> row = new ArrayList<Tile>();
            for (int j = 0; j < cols; j++) {
                int tileId = mapGrid.get(i).get(j);
                Tile tile = new Tile(tileId);
                row.add(tile);
                add(tile);
            }
            this.mapGrid.add(row);
        }
        setPreferredSize(new Dimension(cols * 32, rows * 32));
        revalidate();
        repaint();
        placePlayer();
    }

    public void repaintMap() {
        int rows = mapModel.getHeightOfMap();
        int cols = mapModel.getWidthOfMap();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mapGrid.get(i).get(j).setTileType(mapModel.getTileFromMap(i, j));
            }
        }
        revalidate();
        repaint();
        placePlayer();
    }

    public void restartMap(){
        removeAll();
        drawMap(mapModel.getMap());
    }

}

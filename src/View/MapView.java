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
        }
        else {
            occupiedTile = mapGrid.get(playerY).get(playerX);
        }
        mapGrid.get(playerY).get(playerX).setTileImage(playerTile.getTileImage());
    }

    public JPanel drawMap(ArrayList<ArrayList<Integer>> mapGrid) {
        int rows = mapGrid.size();
        int cols = mapGrid.get(0).size();
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
        repaint();
        revalidate();
        return this;
    }

    public boolean movePlayer(String direction) {
        Tile moveToThisTile = null;
        Tile moveBoxToNextTile = null;
        int playerY = mapModel.getPlayerY();
        int playerX = mapModel.getPlayerX();
        int moveToX = 0;
        int moveToY = 0;
        switch (direction) {
            case "up" -> {
                moveToThisTile = mapGrid.get(playerY - 1).get(playerX);
                moveToY = -1;
            }
            case "down" -> {
                moveToThisTile = mapGrid.get(playerY + 1).get(playerX);
                moveToY = 1;
            }
            case "left" -> {
                moveToThisTile = mapGrid.get(playerY).get(playerX - 1);
                moveToX = -1;
            }
            case "right" -> {
                moveToThisTile = mapGrid.get(playerY).get(playerX + 1);
                moveToX = 1;
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

        if (moveToThisTile.isMovable()) {
            if(moveToThisTile.getTileID() == 2 || moveToThisTile.getTileID() == 3){
                switch (direction) {
                    case "up" -> moveBoxToNextTile = mapGrid.get(playerY - 2).get(playerX);
                    case "down" -> moveBoxToNextTile = mapGrid.get(playerY + 2).get(playerX);
                    case "left" -> moveBoxToNextTile = mapGrid.get(playerY).get(playerX - 2);
                    case "right" -> moveBoxToNextTile = mapGrid.get(playerY).get(playerX + 2);
                }
                if(moveBoxToNextTile.isMovable() && moveBoxToNextTile.getTileID() != 2 && moveBoxToNextTile.getTileID() != 3){
                    if (moveToThisTile.getTileID() == 3) {
                        moveToThisTile.setTileType(1);
                        moveBoxToNextTile.setTileType(2);
                        mapModel.setIncreaseBoxesUntilWin();
                    }else{
                        if (moveBoxToNextTile.getTileID() == 1) {
                            moveBoxToNextTile.setTileType(3);
                            moveToThisTile.setTileType(0);
                            mapModel.setDecreaseBoxesUntilWin();
                        }else{
                            moveBoxToNextTile.setTileType(2);
                            moveToThisTile.setTileType(0);
                        }
                    }
                }else {
                    return false;
                }
            }
            occupiedTile.setTileImage(occupiedTile.getTileImage());
            playerY += moveToY;
            playerX += moveToX;
            occupiedTile = mapGrid.get(playerY).get(playerX);
            occupiedTile.setTileImage(playerTile.getTileImage());
            return true;
        }else{
            return false;
        }
    }

    public void restartMap(){
        removeAll();
        drawMap(mapModel.getMap());
        placePlayer();
    }

}

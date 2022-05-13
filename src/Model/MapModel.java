package Model;

import View.Tile;
import java.io.*;
import java.util.ArrayList;

public class MapModel extends ModelEntity {
    private int mapID;
    private ArrayList<ArrayList<Tile>> mapGrid;
    private int playerX;
    private int playerY;
    private int heightOfMap;
    private int widthOfMap;
    private Tile occupiedTile;
    private Tile playerTile;
    private int boxesUntilWin;
    private int boxesOnMap;
    private int movesMade;

    public MapModel(){
        this.mapID = 0;
        getMapFromFile(mapID);
        placePlayer();
    }

    public void placePlayer() {
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

    public ArrayList<ArrayList<Tile>> getMapFromFile(int level) {
        if (mapGrid != null){
            mapGrid.clear();
        }
        mapGrid = new ArrayList<>();
        File file = new File("src/assets/levels/"+ level +".txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int boxesUntilWin = 0;
            int totalBoxes = 0;
            String st;
            while ((st = reader.readLine()) != null){
                ArrayList<Tile> row = new ArrayList<Tile>();
                for (int i = 0; i < st.length(); i++) {
                    int tileID = Integer.parseInt(String.valueOf(st.charAt(i)));
                    if (tileID == 1) {
                        boxesUntilWin++;
                    }
                    if (tileID == 3 ) {
                        boxesUntilWin++;
                        totalBoxes++;
                        boxesUntilWin--;
                    }
                    if (tileID == 2) {
                        totalBoxes++;
                    }
                    if (tileID == 9) {
                        playerX = i;
                        playerY = mapGrid.size();
                        tileID = 0;
                    }
                    Tile newTile = new Tile(tileID);
                    row.add(newTile);
                }
                mapGrid.add(row);
            }
            reader.close();
            this.boxesOnMap = totalBoxes;
            this.boxesUntilWin = boxesUntilWin;
            this.heightOfMap = mapGrid.size();
            this.widthOfMap = mapGrid.get(0).size();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.widthOfMap = mapGrid.get(0).size();
        this.heightOfMap = mapGrid.size();
        return mapGrid;
    }

    public int getMapID() {
        return mapID;
    }

    public ArrayList<ArrayList<Tile>> getMap() {
        return mapGrid;
    }

    public void checkWin() {
        if (boxesUntilWin == 0) {
            firePropertyChange("gameWon");
        }
    }

    public void movePlayer(String direction) {
        Tile moveToThisTile = null;
        Tile moveBoxToNextTile = null;
        int moveToX = 0;
        int moveToY = 0;
        switch (direction) {
            case "up":
                moveToThisTile = mapGrid.get(playerY - 1).get(playerX);
                moveToY = -1;
                break;
            case "down":
                moveToThisTile = mapGrid.get(playerY + 1).get(playerX);
                moveToY = 1;
                break;
            case "left":
                moveToThisTile = mapGrid.get(playerY).get(playerX - 1);
                moveToX = -1;
                break;
            case "right":
                moveToThisTile = mapGrid.get(playerY).get(playerX + 1);
                moveToX = 1;
                break;
        }

        if (moveToThisTile.isMovable()) {
            if(moveToThisTile.getTileID() == 2 || moveToThisTile.getTileID() == 3){
                switch (direction) {
                    case "up":
                        moveBoxToNextTile = mapGrid.get(playerY - 2).get(playerX);
                        break;
                    case "down":
                        moveBoxToNextTile = mapGrid.get(playerY + 2).get(playerX);
                        break;
                    case "left":
                        moveBoxToNextTile = mapGrid.get(playerY).get(playerX - 2);
                        break;
                    case "right":
                        moveBoxToNextTile = mapGrid.get(playerY).get(playerX + 2);
                        break;
                }
                if(moveBoxToNextTile.isMovable() && moveBoxToNextTile.getTileID() != 2 && moveBoxToNextTile.getTileID() != 3){
                    if (moveToThisTile.getTileID() == 3) {
                        moveToThisTile.setTileType(1);
                        moveBoxToNextTile.setTileType(2);
                        boxesUntilWin++;
                        checkWin();
                    }else{
                        if (moveBoxToNextTile.getTileID() == 1) {
                            moveBoxToNextTile.setTileType(3);
                            moveToThisTile.setTileType(0);
                            boxesUntilWin--;
                            checkWin();
                        }else{
                            moveBoxToNextTile.setTileType(2);
                            moveToThisTile.setTileType(0);
                        }
                    }
                }else {
                    return;
                }
            }
            occupiedTile.setTileImage(occupiedTile.getTileImage());
            playerY += moveToY;
            playerX += moveToX;
            occupiedTile = mapGrid.get(playerY).get(playerX);
            occupiedTile.setTileImage(playerTile.getTileImage());
            movesMade++;
            firePropertyChange("updateScoreView");
        }
    }

    public int getMovesMade(){
        return movesMade;
    }

    public void movePlayerUp() {
        if (playerY > 0) {
            movePlayer("up");
        }
    }

    public void movePlayerDown() {
        if (playerY < heightOfMap - 1) {
            movePlayer("down");
        }
    }

    public void movePlayerLeft() {
        if (playerX > 0) {
            movePlayer("left");
        }
    }

    public void movePlayerRight() {
        if (playerX < widthOfMap - 1) {
            movePlayer("right");
        }
    }

    public void restart(int mapID) {
        mapGrid = getMapFromFile(mapID);
        placePlayer();
        movesMade = 0;
        this.mapID = mapID;
        firePropertyChange("restart");
    }

}

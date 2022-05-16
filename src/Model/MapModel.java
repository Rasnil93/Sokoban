package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MapModel extends ModelEntity {
    private int mapID;
    private ArrayList<ArrayList<Integer>> mapGridInt;
    private int playerX;
    private int playerY;
    private int heightOfMap;
    private int widthOfMap;
    private int boxesUntilWin;
    private int boxesOnMap;
    private int movesMade;
    private boolean testing;

    public MapModel(){
        this.mapID = 0;
        getMapFromFile(mapID);
        testing = false;
    }

    public boolean getTesting(){
        return testing;
    }

    public void setTesting(boolean testing) {
        this.testing = testing;
    }

    public void getMapFromFile(int level) {
        if (mapGridInt != null){
            mapGridInt.clear();
        }
        mapGridInt = new ArrayList<>();
        File file;

        if (testing) {
            file = new File("src/assets/testLevels/"+ level +".txt");
        }else{
            file = new File("src/assets/levels/"+ level +".txt");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int boxesUntilWin = 0;
            int totalBoxes = 0;
            String st;
            while ((st = reader.readLine()) != null){
                ArrayList<Integer> row = new ArrayList<>();
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
                        playerY = mapGridInt.size();
                        tileID = 0;
                    }
                    row.add(tileID);
                }
                mapGridInt.add(row);
            }
            reader.close();
            this.boxesOnMap = totalBoxes;
            this.boxesUntilWin = boxesUntilWin;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.widthOfMap = mapGridInt.get(0).size();
        this.heightOfMap = mapGridInt.size();
    }

    public int getMapID() {
        return mapID;
    }

    public ArrayList<ArrayList<Integer>> getMap() {
        return mapGridInt;
    }

    public void checkWin() {
        if (boxesUntilWin == 0 && !testing) {
            firePropertyChange("gameWon");
        }
    }

    public int getMovesMade(){
        return movesMade;
    }

    public void movePlayerUp() {
        if (playerY > 0) {
            if (movePlayer("up")){
                firePropertyChange("upAccepted");
            }
            firePropertyChange("focusFrame");
        }
    }

    public void movePlayerDown() {
        if (playerY < heightOfMap - 1) {
            if (movePlayer("down")){
                firePropertyChange("downAccepted");
            }
            firePropertyChange("focusFrame");
        }
    }

    public void movePlayerLeft() {
        if (playerX > 0) {
            if (movePlayer("left")){
                firePropertyChange("leftAccepted");
            }
            firePropertyChange("focusFrame");
        }
    }

    public void movePlayerRight() {
        if (playerX < widthOfMap - 1) {
            if (movePlayer("right")){
                firePropertyChange("rightAccepted");
            }
            firePropertyChange("focusFrame");
        }
    }

    public boolean movePlayer(String direction) {
        int moveToThisTile;
        int moveBoxToNextTile;
        int moveToX = 0;
        int moveToY = 0;
        switch (direction) {
            case "up" -> {
                moveToThisTile = mapGridInt.get(playerY - 1).get(playerX);
                moveToY = -1;
            }
            case "down" -> {
                moveToThisTile = mapGridInt.get(playerY + 1).get(playerX);
                moveToY = 1;
            }
            case "left" -> {
                moveToThisTile = mapGridInt.get(playerY).get(playerX - 1);
                moveToX = -1;
            }
            case "right" -> {
                moveToThisTile = mapGridInt.get(playerY).get(playerX + 1);
                moveToX = 1;
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }

        if (isMovable(moveToThisTile)) {
            if(moveToThisTile == 2 || moveToThisTile == 3){
                int moveBoxToNextTileY;
                int moveBoxToNextTileX;
                switch (direction) {
                    case "up" -> {
                        moveBoxToNextTileX = playerX;
                        moveBoxToNextTileY = playerY - 2;
                        moveBoxToNextTile = mapGridInt.get(playerY - 2).get(playerX);
                    }
                    case "down" -> {
                        moveBoxToNextTileX = playerX;
                        moveBoxToNextTileY = playerY +2;
                        moveBoxToNextTile = mapGridInt.get(playerY + 2).get(playerX);
                    }
                    case "left" -> {
                        moveBoxToNextTileX = playerX -2;
                        moveBoxToNextTileY = playerY ;
                        moveBoxToNextTile = mapGridInt.get(playerY).get(playerX - 2);
                    }
                    case "right" -> {
                        moveBoxToNextTileX = playerX +2;
                        moveBoxToNextTileY = playerY ;
                        moveBoxToNextTile = mapGridInt.get(playerY).get(playerX + 2);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + direction);
                }
                if(isMovable(moveBoxToNextTile) && moveBoxToNextTile != 2 && moveBoxToNextTile != 3 ){
                       if (moveToThisTile == 3) {
                           mapGridInt.get(playerY+moveToY).set(playerX+moveToX, 1);
                           mapGridInt.get(moveBoxToNextTileY).set(moveBoxToNextTileX, 2);
                           setIncreaseBoxesUntilWin();
                       } else {
                           if (moveBoxToNextTile == 1) {
                               mapGridInt.get(playerY+moveToY).set(playerX+moveToX, 0);
                               mapGridInt.get(moveBoxToNextTileY).set(moveBoxToNextTileX, 3);
                               setDecreaseBoxesUntilWin();
                               checkWin();
                           } else {
                               mapGridInt.get(playerY+moveToY).set(playerX+moveToX, 0);
                               mapGridInt.get(moveBoxToNextTileY).set(moveBoxToNextTileX, 2);
                           }
                       }
                }else {
                    return false;
                }
            }
            movesMade++;
            playerY += moveToY;
            playerX += moveToX;
            firePropertyChange("updateScoreView");
            return true;
        }else{
            return false;
        }
    }
    ArrayList<Integer> intArr = new ArrayList<Integer>(Arrays.asList(0,1,2,3));

    private boolean isMovable(int moveToThisTile) {
        return intArr.contains(moveToThisTile);
    }


    public void restart(int newMapId) {
        getMapFromFile(newMapId);
        movesMade = 0;
        this.mapID = newMapId;
        firePropertyChange("restart");
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getHeightOfMap() {
        return heightOfMap;
    }

    public int getWidthOfMap() {
        return widthOfMap;
    }

    public void setIncreaseBoxesUntilWin() {
        boxesUntilWin++;
    }

    public void setDecreaseBoxesUntilWin() {
        boxesUntilWin--;
    }
}

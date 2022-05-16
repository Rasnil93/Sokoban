package Model;

import java.io.*;
import java.util.ArrayList;

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
        firePropertyChange("up");
        firePropertyChange("focusFrame");
    }

    public void movePlayerDown() {
        firePropertyChange("down");
        firePropertyChange("focusFrame");
    }

    public void movePlayerLeft() {
        firePropertyChange("left");
        firePropertyChange("focusFrame");
    }

    public void movePlayerRight() {
        firePropertyChange("right");
        firePropertyChange("focusFrame");
    }


    public void movePlayer(String direction) {
        movesMade++;
        checkWin();
        switch (direction) {
            case "up" -> playerY--;
            case "down" -> playerY++;
            case "left" -> playerX--;
            case "right" -> playerX++;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
        firePropertyChange("updateScoreView");
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

package Model;

import javax.swing.*;

/**
 * Creates the model for the tile, contains all of the information for the tile
 */
public class TileModel {
    private boolean movable;
    private int tileType;
    private ImageIcon image;

    public void setMovable(boolean b) {
        this.movable = b;
    }

    /**
     * Sets the image for the tile
     * @param tileType
     */
    public void setTileType(int tileType) {
        this.tileType = tileType;
        ImageIcon imgIcon;
        switch (tileType) {
            case 0:
                imgIcon = new ImageIcon("src/assets/blank.png");
                setMovable(true);
                break;
            case 1:
                imgIcon = new ImageIcon("src/assets/blankmarked.png");
                setMovable(true);
                break;
            case 2:
                imgIcon = new ImageIcon("src/assets/crate.png");
                setMovable(true);
                break;
            case 3:
                imgIcon = new ImageIcon("src/assets/cratemarked.png");
                setMovable(true);
                break;
            case 4:
                imgIcon = new ImageIcon("src/assets/wall.png");
                setMovable(false);
                break;
            case 9:
                imgIcon = new ImageIcon("src/assets/player.png");
                setMovable(false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tileType);
        }
        image = imgIcon;
    }

    public ImageIcon getTileImage() {
        return image;
    }

}
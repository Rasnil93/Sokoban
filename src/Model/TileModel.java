package Model;

import javax.swing.*;

/**
 * Creates the model for the tile, contains all of the information for the tile
 */
public class TileModel {
    private ImageIcon image;

    /**
     * Sets the image for the tile
     * @param tileType
     */
    public void setTileType(int tileType) {
        ImageIcon imgIcon = switch (tileType) {
            case 0 -> new ImageIcon("src/assets/blank.png");
            case 1 -> new ImageIcon("src/assets/blankmarked.png");
            case 2 -> new ImageIcon("src/assets/crate.png");
            case 3 -> new ImageIcon("src/assets/cratemarked.png");
            case 4 -> new ImageIcon("src/assets/wall.png");
            case 9 -> new ImageIcon("src/assets/player.png");
            default -> throw new IllegalStateException("Unexpected value: " + tileType);
        };
        image = imgIcon;
    }

    public ImageIcon getTileImage() {
        return image;
    }
}
package View;

import Model.TileModel;

import javax.swing.*;
import java.awt.*;

//Tile is the view of a tile in the mapView
public class Tile extends JLabel {
    private final TileModel tileModel;

    //Constructor of the Tile, it takes a TileModel as parameter and sets the view of the tile
    public Tile(int tileType) {
        tileModel = new TileModel();
        tileModel.setTileType(tileType);
        setTileImage(tileModel.getTileImage());
    }

    //Set the type of the tile
    public void setTileType(int tileType) {
        tileModel.setTileType(tileType);
        setTileImage(tileModel.getTileImage());
    }

    //Set the image of the tile
    public void setTileImage(ImageIcon imgIcon) {
        setVisible(true);
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        setOpaque(true);
        setBackground(Color.green);
        setIcon(imgIcon);
        repaint();
    }

    //get the image of the tile
    public ImageIcon getTileImage() {
        return tileModel.getTileImage();
    }
}

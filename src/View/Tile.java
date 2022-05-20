package View;

import Model.TileModel;

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {
    private TileModel tileModel;

    public Tile(int tileType) {
        tileModel = new TileModel();
        tileModel.setTileType(tileType);
        setTileImage(tileModel.getTileImage());
    }

    public void setTileType(int tileType) {
        tileModel.setTileType(tileType);
        setTileImage(tileModel.getTileImage());
    }

    public void setTileImage(ImageIcon imgIcon) {
        setVisible(true);
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        setOpaque(true);
        setBackground(Color.green);
        setIcon(imgIcon);
        repaint();
    }

    public ImageIcon getTileImage() {
        return tileModel.getTileImage();
    }
}

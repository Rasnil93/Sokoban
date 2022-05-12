import Controller.GameConsoleController;
import Controller.MapController;
import View.GameConsoleView;
import View.MapView;
import Model.MapModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class App extends Game implements PropertyChangeListener {
    private JComponent mainView;
    private KeyListener keyListener;
    private MapModel mapModel;

    public App(){
        init();
    }

    @Override
    public JComponent createMainFrame() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(1200, 1200));
        this.mainView = panel;
        return panel;
    }


    @Override
    public void init() {
        MapModel mapModel = new MapModel();
        this.mapModel = mapModel;
        MapView mapView = new MapView(mapModel);
        GameConsoleView gameConsole = new GameConsoleView(mapModel);
        GameConsoleController gameConsoleController = new GameConsoleController(gameConsole, mapModel);
        MapController mapController = new MapController(mapModel, mapView);
        mapModel.addPropertyChangeListener(mapController);
        mapModel.addPropertyChangeListener(gameConsoleController);
        mapModel.addPropertyChangeListener(this);
        super.addKeyListener(mapController);
        mainView.add(gameConsole);
        mainView.add(mapView);
        mainView.validate();
    }

    @Override
    public void update() {
        //update the game, check the listeners
    }

    @Override
    public void gameWon() {
        System.out.println("You win!");
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("gameWon")){
            gameWon();
        }
        if(evt.getPropertyName().equals("restart")){
            super.reset();
        }

    }
}

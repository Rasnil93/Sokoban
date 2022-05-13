import Controller.ButtonPanelController;
import Controller.GameConsoleController;
import Controller.MapController;
import View.ButtonPanelView;
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
        //map
        MapModel mapModel = new MapModel();
        MapView mapView = new MapView(mapModel);
        MapController mapController = new MapController(mapModel, mapView);

        //game console
        GameConsoleView gameConsole = new GameConsoleView(mapModel);
        GameConsoleController gameConsoleController = new GameConsoleController(gameConsole, mapModel);

        //buttonPanel
        ButtonPanelView buttonPanelView = new ButtonPanelView();
        ButtonPanelController buttonPanelController = new ButtonPanelController(buttonPanelView, mapModel);

        //add listeners to mapModel
        mapModel.addPropertyChangeListener(mapController);
        mapModel.addPropertyChangeListener(gameConsoleController);
        mapModel.addPropertyChangeListener(this);

        //add keylistener to main frame
        super.addKeyListener(mapController);

        mainView.add(buttonPanelView);
        mainView.add(gameConsole);
        mainView.add(mapView);
        mainView.validate();

        this.mapModel = mapModel;
        this.keyListener = mapController;
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
        if (evt.getPropertyName().equals("focusFrame")){
            super.reset();
        }

    }
}

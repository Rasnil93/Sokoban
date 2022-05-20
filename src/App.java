import Controller.*;
import View.ButtonPanelView;
import View.GameConsoleView;
import View.MapView;
import Model.MapModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;

public class App extends Game implements PropertyChangeListener {
    private JComponent mainView;
    private MapModel mapModel;
    private MoveHandler moveHandler;

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

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setController(MoveHandler moveHandler){
        if(moveHandler.getClass() == KeyController.class){
            super.addKeyListener(moveHandler);
        }
        if (moveHandler.getClass() == ButtonPanelController.class){
            ButtonPanelView buttonPanelView = new ButtonPanelView();
            ((ButtonPanelController) moveHandler).setButtonPanelView(buttonPanelView);
            ((ButtonPanelController) moveHandler).setAllActionListeners();
            mainView.add(buttonPanelView);
            mainView.revalidate();
        }
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
        //ButtonPanelView buttonPanelView = new ButtonPanelView();
        //ButtonPanelController buttonPanelController = new ButtonPanelController(buttonPanelView, mapModel);

        //add listeners to mapModel
        mapModel.addPropertyChangeListener(mapController);
        mapModel.addPropertyChangeListener(gameConsoleController);
        mapModel.addPropertyChangeListener(this);

        //mainView.add(buttonPanelView);
        mainView.add(gameConsole);
        mainView.add(mapView);
        mainView.validate();

        this.mapModel = mapModel;
    }

    @Override
    public void gameWon() {
        System.out.println("You win!");
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

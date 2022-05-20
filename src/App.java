import Controller.*;
import View.ButtonPanelView;
import View.GameConsoleView;
import View.MapView;
import Model.MapModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//this is where all of the views and game logic creates, the app listens to the main model to make sure it dosent loose focus
public class App extends Game implements PropertyChangeListener {
    //the main panel, JPanel that occupies the whole game frame
    private JComponent mainView;

    //the main model
    public MapModel mapModel;

    //constructor to initialize the game
    public App(){
        init();
    }

    //creates the main frame where you add all of the other view too
    @Override
    public JComponent createMainFrame() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(800, 600));
        this.mainView = panel;
        return panel;
    }

    //get the mapModel, the mapModel is public but this is a more logical name to get the model
    public MapModel getMapModel() {
        return mapModel;
    }

    //checks if the type of movehandler and set it in it's proper way
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


    //creates all the view, mapview and so on. Also adds the observers to the main model
    @Override
    public void init() {

        //map
        MapModel mapModel = new MapModel();
        MapView mapView = new MapView(mapModel);
        MapController mapController = new MapController(mapModel, mapView);

        //game console
        GameConsoleView gameConsole = new GameConsoleView(mapModel);
        GameConsoleController gameConsoleController = new GameConsoleController(gameConsole, mapModel);

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
    public void propertyChange(PropertyChangeEvent evt) {
        //when we recreate the views, the keylisteners looses focus, and we use this to refocus the screen
        if(evt.getPropertyName().equals("restart")){
            super.reset();
        }

        //When you press the buttons the keylisteners looses focus, therefore we need to refocus the screen
        if (evt.getPropertyName().equals("focusFrame")){
            super.reset();
        }

    }
}

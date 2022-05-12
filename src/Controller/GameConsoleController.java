package Controller;

import Model.MapModel;
import View.GameConsoleView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameConsoleController implements PropertyChangeListener {
    private GameConsoleView gameConsoleView;
    private MapModel mapModel;

    public GameConsoleController(GameConsoleView gameConsole, MapModel mapModel) {
        this.gameConsoleView = gameConsole;
        this.mapModel = mapModel;

        gameConsoleView.getRestartButton().addActionListener(e -> {
            mapModel.restart(0);
        });

        gameConsoleView.getNextMapButton().addActionListener(e -> {
            mapModel.restart(mapModel.getMapID()+1);
            gameConsoleView.hideNextButton();
        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "restart":
                gameConsoleView.updateScoreView();
                gameConsoleView.hideYouWin();
                break;
            case "gameWon":
                if (mapModel.getMapID() < 1) {
                    gameConsoleView.showNextButton();
                }else{
                    gameConsoleView.hideNextButton();
                    gameConsoleView.showYouWin();
                }
                break;
            case "updateScoreView":
                gameConsoleView.updateScoreView();
                break;

        }
    }
}

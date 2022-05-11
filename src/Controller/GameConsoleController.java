package Controller;

import Model.MapModel;
import View.GameConsoleView;
import View.ViewEntity;

import java.beans.PropertyChangeEvent;

public class GameConsoleController implements ViewEntity {
    private GameConsoleView gameConsoleView;
    private MapModel mapModel;

    public GameConsoleController(GameConsoleView gameConsole, MapModel mapModel) {
        this.gameConsoleView = gameConsole;
        this.mapModel = mapModel;

        gameConsoleView.getRestartButton().addActionListener(e -> {
            mapModel.restart();
        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "restart":
                gameConsoleView.updateScoreView();
                break;
            case "updateScoreView":
                gameConsoleView.updateScoreView();
                break;

        }
    }
}

package Controller;

import Model.MapModel;
import View.ButtonPanelView;

public class ButtonPanelController {
    private ButtonPanelView buttonPanelView;
    private MapModel mapModel;

    public ButtonPanelController(ButtonPanelView buttonPanelView, MapModel mapModel) {
        this.buttonPanelView = buttonPanelView;
        this.mapModel = mapModel;

        buttonPanelView.getButtonUp().addActionListener(e -> {
            mapModel.movePlayerUp();
        });
        buttonPanelView.getButtonDown().addActionListener(e -> {
            mapModel.movePlayerDown();
        });
        buttonPanelView.getButtonLeft().addActionListener(e -> {
            mapModel.movePlayerLeft();
        });
        buttonPanelView.getButtonRight().addActionListener(e -> {
            mapModel.movePlayerRight();
        });
    }
}

package Controller;

import Model.MapModel;
import View.ButtonPanelView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ButtonPanelController extends MoveHandler{
    private ButtonPanelView buttonPanelView;

    public ButtonPanelController(MapModel mapModel) {
        super(mapModel);
    }

    public void setAllActionListeners(){
        buttonPanelView.getButtonUp().addActionListener(this);
        buttonPanelView.getButtonDown().addActionListener(this);
        buttonPanelView.getButtonLeft().addActionListener(this);
        buttonPanelView.getButtonRight().addActionListener(this);
    }

    public void setButtonPanelView(ButtonPanelView view){
        this.buttonPanelView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String code = e.getActionCommand();

        switch (code){
            case "up" -> moveUp();
            case "down" -> moveDown();
            case "left" -> moveLeft();
            case "right" -> moveRight();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package View;

import javax.swing.*;

public class ButtonPanelView extends JPanel{
    private JButton buttonUp;
    private JButton buttonDown;
    private JButton buttonLeft;
    private JButton buttonRight;

    public ButtonPanelView(){
        JButton buttonUp = new JButton("up");
        JButton buttonDown = new JButton("down");
        JButton buttonLeft = new JButton("left");
        JButton buttonRight = new JButton("right");

        this.buttonDown = buttonDown;
        this.buttonLeft = buttonLeft;
        this.buttonRight = buttonRight;
        this.buttonUp = buttonUp;

        this.add(buttonUp);
        this.add(buttonDown);
        this.add(buttonLeft);
        this.add(buttonRight);
    }

    public JButton getButtonUp(){
        return buttonUp;
    }

    public JButton getButtonDown(){
        return buttonDown;
    }

    public JButton getButtonLeft(){
        return buttonLeft;
    }

    public JButton getButtonRight(){
        return buttonRight;
    }


}

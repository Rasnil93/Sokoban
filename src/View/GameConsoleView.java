package View;

import Model.MapModel;

import javax.swing.*;

public class GameConsoleView extends JPanel {
    private MapModel mapModel;
    private JButton restartButton;
    private JLabel scoreValue;

    public GameConsoleView(MapModel mapModel) {
        this.mapModel = mapModel;
        add(new JLabel("Game Console"));
        JLabel scoreLabel = new JLabel("Score: ");
        add(scoreLabel);
        JLabel scoreValue = new JLabel("0");
        this.scoreValue = scoreValue;
        add(scoreValue);
        JButton restartButton = new JButton("Restart");
        this.restartButton = restartButton;
        add(restartButton);
    }

    public JButton getRestartButton(){
        return restartButton;
    }

    public void updateScoreView() {
        scoreValue.setText(mapModel.getMovesMade()+"");
        scoreValue.repaint();
        validate();
        repaint();
    }
}

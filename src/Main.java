import Controller.ButtonPanelController;
import Controller.KeyController;

public class Main {
    public static void main(String[] args) {
        //create the sokoban app
        App app = new App();

        //choose what listeners and events you want to use to move the player

        //add keylisteners WASD to the frame
        app.setController(new KeyController(app.getMapModel()));
        //add new button view to the frame
        app.setController(new ButtonPanelController(app.getMapModel()));

    }
}

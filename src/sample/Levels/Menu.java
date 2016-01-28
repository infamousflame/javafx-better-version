package sample.Levels;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Menu extends StackPane{
    Button button = new Button("Same");
    public Menu(){
        this.getChildren().add(button);
    }

}

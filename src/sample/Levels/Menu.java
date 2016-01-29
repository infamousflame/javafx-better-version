package sample.Levels;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Menu extends StackPane{
    Button button = new Button("Allah");
    Pane root = new Pane();
    Image start;
    ImageView startView;
    int click;

    public Image setImage(String path){
        start = new Image(path);
    }

    public Menu(){
        startView = new ImageView(start);
        this.getChildren().add(root);
        this.getChildren().add(button);
       // this.getChildren().add(startView);
        button.setOnAction(e -> {
            System.out.println("click");
            click++;
        });

    }

    public int getClick(){
        return click;
    }



}

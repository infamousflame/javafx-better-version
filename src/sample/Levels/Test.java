package sample.Levels;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Owenl_000 on 1/30/2016.
 */
public class Test extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 1000, 1000);
        primaryStage.show();
        primaryStage.setScene(scene);
    }
}

package sample;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Levels.Menu;

import java.util.ArrayList;

public class Main extends Application{
        int x, y;
        boolean isJump;
        boolean kLeft, kRight, kUp;

        int gravity = 10;

        Stage window;
        StackPane pane = new StackPane();
        Pane root = new Pane();

        ArrayList<ImageView> backgroundViewer = new ArrayList<>();

        ImageView characterView;

        Menu menu = new Menu();
        int WIDTH, HEIGHT;
        int grav = 40;
        int jumpHeight = 35;
        Image background = new Image(Main.class.getResource("res/background.png").toString());
        Image hero = new Image(Main.class.getResource("res/img.png").toString());
        @Override
        public void start(Stage primaryStage) throws Exception {
            window = primaryStage;
            characterView = new ImageView(hero);
            addBackgrounds();
            renderBackgrounds();
            initCharacter();
            Scene scene = new Scene(pane, 800, 600);

            window.setScene(scene);
            pane.getChildren().add(root);
            root.getChildren().add(characterView);

            scene.setOnKeyPressed(event -> {
                switch (event.getCode()){
                    case UP: kUp = true; break;
                    case LEFT: kLeft = true; break;
                    case RIGHT: kRight = true; break;
                }
            });
            scene.setOnKeyReleased(event -> {
                switch (event.getCode()){
                    case UP: kUp = false; break;
                    case LEFT: kLeft = false; break;
                    case RIGHT: kRight = false; break;
                }
            });

            final long startNanoTime = System.nanoTime();

            new AnimationTimer()
            {
                public void handle(long currentNanoTime) {
                    jump();
                    gravity();
                    checkKeys();
                    backgroundsSetX();
                    HEIGHT = (int) root.getHeight();
                    WIDTH = (int) primaryStage.getWidth();
                 //   System.out.println(HEIGHT);

                }
            }.start();
           // primaryStage.setFullScreen(true);
            primaryStage.setTitle("Same");
            primaryStage.setAlwaysOnTop(true);

            primaryStage.show();
        }
        public void checkKeys(){
            if(kUp) {
                isJump = true;
            }
            if(kRight){
                x -= 5;

            }
            if(kLeft && x < 0){
                x += 5;
            }
        }

        public void addBackgrounds(){
            for(int i = 0; i < 200; i++){
                backgroundViewer.add(new ImageView(background));
            }

        }
        public void renderBackgrounds(){
            for(int i = 0; i < backgroundViewer.size(); i++) {
                root.getChildren().add(backgroundViewer.get(i));
                backgroundViewer.get(i).setFitHeight(window.getHeight());
            }
        }
        public void backgroundsSetX(){
            for(int i = 0; i < backgroundViewer.size(); i++){
                backgroundViewer.get(i).setX(x + (i * WIDTH));
                backgroundViewer.get(i).setFitWidth(WIDTH);
                backgroundViewer.get(i).setFitHeight(HEIGHT);
                System.out.println(HEIGHT);

            }
        }

      public void gravity(){
          characterView.setY(y);
          y += gravity;
         if(y >= 400){
             y = 400;
             grav = 0;
             isJump = false;
         }
      }

    public void initCharacter(){
        characterView.setFitHeight(200);
        characterView.setFitWidth(100);
        characterView.setX(500);
    }

      public void jump(){
            if(isJump) {
                y -= jumpHeight + grav;
                grav -= 2;
            }
        }
        public static void main(String[] args) {
            launch(args);
        }



}

package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.sprites.Bullet;
import sample.sprites.Hero;

import java.util.ArrayList;

public class Main extends Application {
    int x, y;
    boolean isJump;
    boolean kLeft, kRight, kUp, kE;

    byte gravity = 10;
    Image start = new Image(Main.class.getResource("res/Ghoul.jpg").toString());

    Stage window;
    StackPane stackpane = new StackPane();
    Pane pane = new Pane();
    Scene scene = new Scene(stackpane, 800, 600);

    ArrayList<ImageView> backgroundViewer = new ArrayList<>();
    ArrayList<Bullet> bulletList = new ArrayList<>();
    Group bulletGroup = new Group();

    int WIDTH, HEIGHT;
    int grav = 40;
    int jumpHeight = 35;
    Image background = new Image(Main.class.getResource("res/background.png").toString());
    Image hero = new Image(Main.class.getResource("res/img.png").toString());

    ImageView characterView = new ImageView(hero);
    Hero character = new Hero(characterView);

    int shootCounter;
    int shootSpeed = 2;
    boolean allowShoot;
    double offset = scene.getWidth() / 4;

    public void start(Stage primaryStage) throws Exception {
        /** Stage>Scene>Stackpane>Pane>Child-Elements **/

        character.setOffset(offset);

        window = primaryStage;
        addBackgrounds();
        initCharacter();
        window.setScene(scene);

        stackpane.getChildren().add(pane);

        pane.getChildren().add(bulletGroup);
        pane.getChildren().add(character.getImageView());
        new Thread(character).start();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    kUp = true;
                    character.setKey("u");
                    break;
                case LEFT:
                    kLeft = true;
                    character.setKey("l");
                    break;
                case RIGHT:
                    kRight = true;
                    character.setKey("r");
                    break;
                case E:
                    kE = true;
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case SPACE:
                    kUp = false;
                    break;
                case LEFT:
                    kLeft = false;
                    break;
                case RIGHT:
                    kRight = false;
                    break;
                case E:
                    kE = false;
                    break;
            }
        });

        /** Game Loop **/
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                jump();
                gravity();
                checkKeys();
                backgroundsSetX();
                bullet();
                //characterView.setX(-x + offset);
                HEIGHT = (int) pane.getHeight();
                WIDTH = (int) primaryStage.getWidth();
                pane.setTranslateX((double) x);
                character.main();
            }
        }.start();

        primaryStage.setOnCloseRequest(event -> System.exit(0));
        /** Stage Maniplulation **/
        primaryStage.setTitle("Same");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }



    public void checkKeys() {
        if (kUp) {
            isJump = true;
        }
        if (kRight) {
            x -= 5;
            character.setX(x);
        }
        if (kLeft && x < 0) {
            x += 5;
            character.setX(x);
        }
        if (kE) {

            shootCounter++;
            if (shootCounter > shootSpeed) {
                allowShoot = true;
                shootCounter = 0;
            }

            if (allowShoot) {
                bulletList.add(new Bullet(-x + (int) offset, y, 100, 100, 2));
                bulletGroup.getChildren().add(bulletList.get(bulletList.size() - 1));
                bulletList.get(bulletList.size() - 1).setImage(start);
                allowShoot = false;
            }
        }
    }

    public void bullet() {
        for(int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).move(true);
            bulletGroup.getChildren().get(i).setLayoutX(bulletList.get(i).getBulletX() + 55);
            bulletGroup.getChildren().get(i).setLayoutY(bulletList.get(i).getBulletY() + 100);
            bulletList.get(i).setFitWidth(10);
            bulletList.get(i).setFitHeight(10);
        }
    }

    public void addBackgrounds() {
        for (int i = 0; i < 200; i++) {
            backgroundViewer.add(new ImageView(background));
        }
        renderBackgrounds();
    }

    public void renderBackgrounds() {
        for (ImageView aBackgroundViewer : backgroundViewer) {
            pane.getChildren().add(aBackgroundViewer);
            aBackgroundViewer.setFitHeight(window.getHeight());
        }
    }

    public void backgroundsSetX() {
        for (int i = 0; i < backgroundViewer.size(); i++) {
            backgroundViewer.get(i).setX((i * WIDTH));
            backgroundViewer.get(i).setFitWidth(WIDTH);
            backgroundViewer.get(i).setFitHeight(HEIGHT);
        }
    }

    public void gravity() {
        characterView.setY(y);
        y += gravity;
        if (y >= 400) {
            y = 400;
            grav = 0;
            isJump = false;
        }
    }

    public void initCharacter() {
        characterView.setFitHeight(200);
        characterView.setFitWidth(100);
        characterView.setX(300);
    }

    public void jump() {
        if (isJump) {
            y -= jumpHeight + grav;
            grav -= 2;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

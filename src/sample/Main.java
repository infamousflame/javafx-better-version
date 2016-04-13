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
import sample.Levels.Menu;
import sample.sprites.Bullet;
import sample.sprites.Platform;

import java.util.ArrayList;

public class Main extends Application {
    int x, y;
    boolean isJump;
    boolean kLeft, kRight, kUp, kE;

    byte gravity = 10;
    Image start = new Image(Main.class.getResource("res/Ghoul.jpg").toString());

    Stage window;
    StackPane pane = new StackPane();
    Menu menu = new Menu();
    Pane root = new Pane();
    Scene scene = new Scene(pane, 800, 600);

    ArrayList<ImageView> backgroundViewer = new ArrayList<>();

    ArrayList<Bullet> bullets = new ArrayList<>();
    Group bulletList = new Group();

    ArrayList<Platform> platforms = new ArrayList<>();
    Group platformList = new Group();
    ImageView characterView;
    int WIDTH, HEIGHT;
    int grav = 40;
    int jumpHeight = 35;
    Image background = new Image(Main.class.getResource("res/background.png").toString());
    Image hero = new Image(Main.class.getResource("res/img.png").toString());

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        menu.setImage(start);
        characterView = new ImageView(hero);

        addBackgrounds();
        initCharacter();
        addPlatforms(200, 200, 200, 200);

        window.setScene(scene);

        pane.getChildren().add(root);
        pane.getChildren().add(bulletList);
        //root.getChildren().add(platformList);
        root.getChildren().add(characterView);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    kUp = true;
                    break;
                case LEFT:
                    kLeft = true;
                    break;
                case RIGHT:
                    kRight = true;
                    break;
                case E:
                    kE = true;
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
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

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                jump();
                gravity();
                checkKeys();
                backgroundsSetX();
                checkPlatforms();
                bullet();

                characterView.setX(-x);
                HEIGHT = (int) root.getHeight();
                WIDTH = (int) primaryStage.getWidth();
                root.setTranslateX((double) x);
            }
        }.start();
        primaryStage.setTitle("Same");
        primaryStage.setAlwaysOnTop(true);

        root.getChildren().add(bulletList);

        primaryStage.show();
    }

    public void checkKeys() {
        if (kUp) {
            isJump = true;
        }
        if (kRight) {
            x -= 5;
        }
        if (kLeft && x < 0) {
            x += 5;
        }
        if (kE) {
            bullets.add(new Bullet(-x, y, 100, 100, 2));
            bulletList.getChildren().add(bullets.get(bullets.size() - 1));
            bullets.get(bullets.size() - 1).setImage(start);
        }
    }

    public void checkPlatforms() {
        for(int i = 0; i < platforms.size(); i++) {
            if(platformList.getChildren().get(i).intersects(characterView.getBoundsInLocal())){
                System.out.println("interstected");
            }
        }
    }

    public void bullet() {
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move(true);
            bulletList.getChildren().get(i).setLayoutX(bullets.get(i).getBulletX() + 55);
            bulletList.getChildren().get(i).setLayoutY(bullets.get(i).getBulletY() + 100);
            bullets.get(i).setFitWidth(10);
            bullets.get(i).setFitHeight(10);

        }
    }

    public void addPlatforms(int x, int y, int width, int height) {
        platforms.add(new Platform(x, y, width, height, false, false));
        platformList.getChildren().add(platforms.get(platforms.size() - 1).platform());
    }

    public void addBackgrounds() {
        for (int i = 0; i < 200; i++) {
            backgroundViewer.add(new ImageView(background));
        }
        renderBackgrounds();
    }

    public void renderBackgrounds() {
        for (int i = 0; i < backgroundViewer.size(); i++) {
            root.getChildren().add(backgroundViewer.get(i));
            backgroundViewer.get(i).setFitHeight(window.getHeight());
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

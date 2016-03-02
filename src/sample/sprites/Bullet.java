package sample.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bullet {
    private int x, y;
    private int yOffset = 20;
    private int xOffset = 20;
    public boolean visible = true;
    public boolean deleteBullet;
    boolean up, down, noRecoil;
    int thresh;
    Rectangle rect;

    public Bullet(int tempX, int tempY, int width, int height, int recoil) {
        x = tempX + xOffset;
        y = tempY + yOffset;
        recoil(recoil);
        rect = new Rectangle(x, y, width, height);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getVisible() {
        return visible;
    }

    public void delete() {

    }

    public Rectangle rectangle() {
        return rect;
    }

    public void move(boolean bool) {

        x += 10;
        if (x < 100000) {
            visible = true;
        } else {
            visible = false;
        }
        if (up) {
            y -= thresh;
        }
        if (down) {
            y += thresh;
        }


    }

    public void recoil(int intensity) {
        Random rn = new Random();
        int x = rn.nextInt(2);
        if (intensity != 0) {
            thresh = rn.nextInt(intensity);
            down = false;
            up = false;
        }
        if (x == 0) {
            down = true;
        } else {
            up = true;
        }

    }


}

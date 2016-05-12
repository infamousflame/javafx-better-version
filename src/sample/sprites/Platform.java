package sample.sprites;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Created by owenlevin on 1/28/16.
 */



public class Platform extends ImageView{
    int x, y, width, height;
    boolean rtouch, ltouch;

    public Platform(int tempX, int tempY, int tempWidth, int tempHeight, boolean ltouchtemp, boolean rtouchtemp) {
        x = tempX;
        y = tempY;
        width = tempWidth;
        height = tempHeight;

    }


    public int getPlatformX() {
        return x;
    }

    public int getPlatformY() {
        return y;
    }

    public void setX(int tempX) {
        x = tempX;
    }

    public void setY(int tempY) {
        y = tempY;
    }

    public void plusX(int tempX) {
        x += tempX;
    }

    public void plusY(int tempY) {
        y += tempY;
    }

    public boolean getLTouch() {
        return ltouch;
    }

    public boolean getRTouch() {
        return rtouch;
    }

    public void setRTouch(boolean in) {
        rtouch = in;
    }

    public void setLTouch(boolean in) {
        ltouch = in;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int tempHeight) {
        x = tempHeight;
    }

    public void setWidth(int tempWidth) {
        y = tempWidth;
    }

    public Rectangle left() {
        return new Rectangle(getX(), getY(), 1, getHeight());
    }

    public Rectangle right() {
        return new Rectangle(getX() + getWidth(), getY(), 1, getHeight());
    }

    public Rectangle top() {
        return new Rectangle(getX(), getY(), getWidth(), getWidth());
    }

    public Rectangle bot() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle platform() {
        return  new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}




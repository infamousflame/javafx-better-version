package sample.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Bullet {
    private int x, y;
    private int yOffset = 20;
    private int xOffset = 20;
    public boolean visible = true;
    public boolean deleteBullet;
    boolean up, down;
    int thresh;
    Image image;
    ImageView imageView = new ImageView();
    public Bullet(int tempX, int tempY, int width, int height, int recoil){
        x = tempX + xOffset;
        y = tempY + yOffset;
        recoil(recoil);
        setImageView();
    }
    public void setImage(String path){
        image = new Image(path);
        setImageView();
    }
    public void setImageView(){
        imageView = new ImageView(image);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean getVisible(){
        return visible;
    }
    public void delete(){

    }
    public ImageView getImageViewer(){
        return imageView;
    }
    public void move(boolean bool){
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        x += 10;
        if(x < 100000){
            visible = true;
        }else{
            visible = false;
        }
        if(up){
            y -= thresh;
        }
        if(down){
            y += thresh;
        }
        imageView.setX(x);
        imageView.setY(y);
    }

    public void recoil(int intensity){
        Random rn = new Random();
        int x = rn.nextInt(2);
        thresh = rn.nextInt(intensity);
        if(x == 0){
            down = true;
        }else{
            up = true;
        }
    }
    

}

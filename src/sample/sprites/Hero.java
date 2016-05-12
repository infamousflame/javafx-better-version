package sample.sprites;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by owenlevin on 5/2/16.
 */
public class Hero implements Runnable{
    double x;
    double y;

    Group bulletGroup = new Group();
    ArrayList<ImageView> backgroundViewer = new ArrayList<>();
    ArrayList<Bullet> bulletList = new ArrayList<>();
    boolean allowShoot;
    int shootCounter;
    int shootSpeed = 2;

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    double offset;
    ImageView view;

    public Hero(ImageView image){
        view = image;
    }

    public void run() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public ImageView getImageView(){
        return view;
    }

    public void setY(double y) {
        this.y = y;
    }


    public void setKey(String input){
        if(input.equals("l")){
            x -= 5;
        }
        if(input.equals("r")){
            x += 5;
        }
        if(input.equals("u")){

        }
        /*
        if(input.equals("e")){
            bulletList.add(new Bullet(((int) (-x + offset)), (int) y, 100, 100, 2));
            bulletGroup.getChildren().add(bulletList.get(bulletList.size() - 1));
            bulletList.get(bulletList.size() - 1).setImage(view.getImage());
            allowShoot = false;
        }
*/
    }
    public void main(){
        view.setX(-x + offset);
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
}

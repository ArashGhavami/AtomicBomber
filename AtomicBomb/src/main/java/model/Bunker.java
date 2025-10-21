package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bunker extends Rectangle {

    private boolean isHit;

    public Bunker(){
        super(100, 60);
        this.isHit = false;
        Random random = new Random();
        setX(random.nextDouble(825) +1);
        setY(635);
        this.setFill(new ImagePattern(new Image(Building.class.getResource("/Images/bunker1.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}

package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Truck extends Rectangle {


    private boolean isHit;

    public Truck(Game game, Pane pane){
        super(70, 40);
        this.isHit = false;
        this.setFill(new ImagePattern(new Image(Truck.class.getResource("/Images/jeep.png").toExternalForm())));
        Random random = new Random();
        setX(random.nextDouble(825) + 1);
        setY(650);

    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}

package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Building extends Rectangle {

    private static final double width = 50;
    private static final double height = 100;

    private boolean isHit;

    public Building(Game game, Pane pane){
        super(width, height);
        this.isHit = false;
        Random random = new Random();
        setX(random.nextDouble(825) +1);
        setY(595);
        this.setFill(new ImagePattern(new Image(Building.class.getResource("/Images/building.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}

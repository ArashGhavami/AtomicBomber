package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class Bomb extends Rectangle {

    private final double WIDTH = 8;
    private final double HEIGHT = 10;

    private boolean appliedForTarget;

    public Bomb (Airplane airplane){
        super(8,10);
        setX(airplane.getX());
        setY(airplane.getY());
        this.appliedForTarget = false;
        this.setFill(new ImagePattern(new Image( Bomb.class.getResource("/Images/aaaa.png").toExternalForm())));
    }

    public boolean isAppliedForTarget() {
        return appliedForTarget;
    }

    public void setAppliedForTarget(boolean appliedForTarget) {
        this.appliedForTarget = appliedForTarget;
    }
}

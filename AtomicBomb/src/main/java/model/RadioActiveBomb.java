package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.movingAnimation.RadioActiveMovingAnimation;

public class RadioActiveBomb extends Rectangle {

    private boolean isItInTarget;

    private final double WIDTH = 10;
    private final double HEIGHT = 20;

    private RadioActiveMovingAnimation radioActiveMovingAnimation;


    public RadioActiveBomb(Airplane airplane){
        super(60, 60);
        setX(airplane.getX());
        setY(airplane.getY());
        this.setFill(new ImagePattern(new Image( Bomb.class.getResource("/Images/bonusnuke.png").toExternalForm())));
    }

    public RadioActiveMovingAnimation getRadioActiveMovingAnimation() {
        return radioActiveMovingAnimation;
    }

    public void setShootingRadioActiveAnimation(RadioActiveMovingAnimation radioActiveMovingAnimation) {
        this.radioActiveMovingAnimation = radioActiveMovingAnimation;
    }

    public boolean isItInTarget() {
        return isItInTarget;
    }

    public void setItInTarget(boolean itInTarget) {
        isItInTarget = itInTarget;
    }
}
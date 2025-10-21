package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.movingAnimation.TankMovingAnimation;

import java.util.Random;

public class Tank extends Rectangle {

    private TankMovingAnimation tankMovingAnimation;

    private final static double WIDTH = 80;
    private final static double HEIGH = 65;
    private boolean isHit;

    public Tank (Game game, Pane pane){
        super(WIDTH, HEIGH);
        this.isHit = false;
        Random random = new Random();
        int x = random.nextInt(825);
        setX(x);
        setY(630);
        this.setFill(new ImagePattern(new Image(Tank.class.getResource("/Images/zsu572.png").toExternalForm())));
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public TankMovingAnimation getTankMovingAnimation() {
        return tankMovingAnimation;
    }

    public void setTankMovingAnimation(TankMovingAnimation tankMovingAnimation) {
        this.tankMovingAnimation = tankMovingAnimation;
    }
}

package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.movingAnimation.ClusterMovingAnimation;

public class ClusterBomb extends Rectangle {

    private ClusterMovingAnimation clusterMovingAnimation;

    private boolean isItInTarget;

    public ClusterBomb(Airplane airplane){
        super(60, 45);
        setX(airplane.getX());
        this.isItInTarget = false;
        setY(airplane.getY());
        this.setFill(new ImagePattern(new Image( Bomb.class.getResource("/Images/nukebomb.png").toExternalForm())));
    }

    public ClusterMovingAnimation getClusterMovingAnimation() {
        return clusterMovingAnimation;
    }

    public void setClusterMovingAnimation(ClusterMovingAnimation clusterMovingAnimation) {
        this.clusterMovingAnimation = clusterMovingAnimation;
    }

    public boolean isItInTarget() {
        return isItInTarget;
    }

    public void setItInTarget(boolean itInTarget) {
        isItInTarget = itInTarget;
    }
}

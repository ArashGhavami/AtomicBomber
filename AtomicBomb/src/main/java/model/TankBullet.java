package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class TankBullet extends Rectangle {

    private final double WIDTH = 8;
    private final double HEIGHT = 10;

    public TankBullet (Tank tank){
        super(8,10);
        setX(tank.getX());
        setY(tank.getY());
        this.setFill(new ImagePattern(new Image( Bomb.class.getResource("/Images/tankBullet.png").toExternalForm())));
    }




}

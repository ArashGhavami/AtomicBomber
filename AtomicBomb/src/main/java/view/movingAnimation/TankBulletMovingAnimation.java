package view.movingAnimation;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Game;
import model.RadioActiveBomb;
import model.TankBullet;
import view.GameLauncher;

public class TankBulletMovingAnimation extends Transition {

    private final Game game;
    private final Pane pane;
    private final int duration = 1000;
    private final TankBullet tankBullet;
    private final double targetX;
    private final double targetY;
    private final double startX;
    private final double startY;
    private final double speed = 5;



    public TankBulletMovingAnimation(Game game, Pane pane, Airplane airplane, TankBullet tankBullet) {
        this.game = game;
        this.pane = pane;
        this.startX = tankBullet.getX();
        this.startY = tankBullet.getY();
        this.targetX = airplane.getX();
        this.targetY = airplane.getY();
        this.tankBullet = tankBullet;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {


        tankBullet.setX(tankBullet.getX() + (targetX - startX)/speed);
        tankBullet.setY(tankBullet.getY() + (targetY - startY)/speed);


    }
}

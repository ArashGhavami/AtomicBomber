package view.movingAnimation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Tank;

public class TankMovingAnimation extends Transition {

    private final Pane pane;
    private final Game game;
    private final Tank tank;

    private double speed;
    private final int duration = 100;

    public TankMovingAnimation(Pane pane, Game game, Tank tank, double tankSpeed) {
        this.pane = pane;
        this.game = game;
        this.speed = tankSpeed;
        this.tank = tank;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {
        double x = speed + tank.getX();

        if(x >=825 || x <= 0){
            speed  = -speed;
        }
        tank.setX(x);

    }
}

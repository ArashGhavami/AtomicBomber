package view.movingAnimation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Truck;

public class TruckMovingAnimation extends Transition {


    private double speed = 1.25;
    private final int duration = 100;

    private final  Pane pane;
    private final  Game game;
    private final Truck truck;

    public TruckMovingAnimation(Pane pane, Game game, Truck truck) {
        this.pane = pane;
        this.game = game;
        this.truck = truck;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
        game.addTransitionsToAnimation(this);
    }

    @Override
    protected void interpolate(double v) {


        double x = speed + truck.getX();

        if(x >=825 || x <= 0){
            speed  = -speed;
        }
        if(speed < 0 && truck.getScaleX() > 0){
            truck.setScaleX(-truck.getScaleX());
        }
        if(speed>0 && truck.getScaleX() < 0){
            truck.setScaleX(-truck.getScaleX());
        }


        truck.setX(x);

    }
}

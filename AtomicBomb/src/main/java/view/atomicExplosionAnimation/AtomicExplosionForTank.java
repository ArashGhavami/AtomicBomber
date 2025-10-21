package view.atomicExplosionAnimation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Game;
import model.Tank;
import model.User;
import view.explosionAnimation.ExplosionAnimationForTree;

public class AtomicExplosionForTank extends Transition {


    private final Tank tank;
    private final Pane pane;
    private final int status;
    private final Group tanks;

    public AtomicExplosionForTank(Tank tank, Pane pane, Group tanks,int status) {
        this.tank = tank;
        this.status = status;
        this.pane = pane;
        this.tanks = tanks;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(2000));
        Game game = User.getCurrentUser().getLastGameLauncher().getGame();
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {
        if(status == 1) {
            int number = 1;
            if (v >= 0 && v <= 0.25) number = 1;
            else if (v > 0.25 && v < 0.5) number = 2;
            else if (v >= 0.5 && v < 0.75) number = 3;
            else if (v >= 0.75 && v < 1) number = 4;
            tank.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/bigblast" + number + ".png").toExternalForm())));
        }
        else{
            int number = 1;
            if(v >= 0 && v < 0.33)number = 1;
            else if(v >= 0.33 && v < 0.66) number =2;
            else number = 3;
            tank.setFill(new ImagePattern(new Image(AtomicExplosionForBuilding.class.getResource("/Images/blast" + number +".png").toExternalForm())));

        }
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tanks.getChildren().remove(tank);

            }
        });

    }

}

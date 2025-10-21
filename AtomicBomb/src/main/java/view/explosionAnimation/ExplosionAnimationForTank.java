package view.explosionAnimation;

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

public class ExplosionAnimationForTank extends Transition {

    private final Tank tank;
    private final Pane pane;
    private final Group tanks;

    public ExplosionAnimationForTank(Tank tank, Pane pane, Group tanks) {
        this.tank = tank;
        this.pane = pane;
        this.tanks = tanks;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
        Game game = User.getCurrentUser().getLastGameLauncher().getGame();
        game.addTransitionsToAnimation(this);
    }




    @Override
    protected void interpolate(double v) {
        int number = 1;
        if(v >=0 && v <= 0.33) number = 1;
        else if (v> 0.33 && v <0.66) number = 2;
        else if(v >=0.66 && v <1) number =3;
        else if (v == 1) {
            return;
        }


        tank.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/deadtank" + number +".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(tank);
                tanks.getChildren().remove(tank);
            }
        });


    }
}

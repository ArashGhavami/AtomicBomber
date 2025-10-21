package view.explosionAnimation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Bunker;
import model.Game;
import model.User;

public class ExplosionAnimationForBunker extends Transition {

    private final Bunker bunker;
    private final Pane pane;
    private final Group bunkers;

    public ExplosionAnimationForBunker(Bunker bunker, Pane pane, Group bunkers) {
        this.bunker = bunker;
        this.pane = pane;
        this.bunkers = bunkers;
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
        bunker.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/fire" + number +".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(bunker);
                bunkers.getChildren().remove(bunker);
            }
        });
    }
}

package view.atomicExplosionAnimation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Building;
import model.Game;
import model.User;
import view.explosionAnimation.ExplosionAnimationForTree;

public class AtomicExplosionForBuilding extends Transition {

    private final Building building;
    private final Pane pane;
    private final Group buildings;
    private final int status;

    public AtomicExplosionForBuilding(Building building, Pane pane, Group buildings, int status) {
        this.building = building;
        this.pane = pane;
        this.buildings = buildings;
        this.status = status;
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
            else if (v >= 0.75 && v <= 1) number = 4;
            building.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/bigblast" + number + ".png").toExternalForm())));
        }
        else {
            int number = 1;
            if(v >= 0 && v < 0.33) number =1;
            else if (v >= 0.33 && v < 0.66) number =2;
            else number = 3;

            building.setFill(new ImagePattern(new Image(AtomicExplosionForBuilding.class.getResource("/Images/blast" + number +".png").toExternalForm())));

        }

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(building);
                buildings.getChildren().remove(building);
            }
        });

    }

}

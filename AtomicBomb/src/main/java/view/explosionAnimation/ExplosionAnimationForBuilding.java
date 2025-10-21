package view.explosionAnimation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Airplane;
import model.Building;
import model.Game;
import model.User;
import view.GameLauncher;

public class ExplosionAnimationForBuilding extends Transition {

    private final Building building;
    private final Pane pane;
    private final Group buildings;
    private final Airplane airplane;
    private final GameLauncher gameLauncher;

    public ExplosionAnimationForBuilding(Building building, Pane pane, Group buildings, Airplane airplane, GameLauncher gameLauncher) {
        this.building = building;
        this.pane = pane;
        this.airplane = airplane;
        this.buildings = buildings;
        this.gameLauncher = gameLauncher;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(250));
        gameLauncher.getGame().addTransitionsToAnimation(this);
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
        building.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/fire" + number +".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(building);
                buildings.getChildren().remove(building);
            }
        });


    }
}

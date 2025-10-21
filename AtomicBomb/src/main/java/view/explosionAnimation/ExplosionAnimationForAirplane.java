package view.explosionAnimation;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Airplane;
import model.Game;
import model.User;
import view.GameLauncher;
import view.menus.EndgameMenu;
import view.menus.MainMenu;

public class ExplosionAnimationForAirplane extends Transition {


    private final Airplane airplane;
    private final Game game;
    private final Stage stage;
    private final Pane pane;

    public ExplosionAnimationForAirplane(Airplane airplane, Game game, Pane pane, Stage stage) {
        this.airplane = airplane;
        this.stage = stage;
        this.game = game;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(3000));
        this.pane = pane;
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {

        int number = 1;
        if(v>=0 && v <0.25) number =1;
        else if (v >= 0.25 && v < 0.5) number = 2;
        else if(v >= 0.5 && v < 0.75) number =3;
        else if(v>= 0.75 && v <=1 )number = 4;

        airplane.setFill(new ImagePattern(new Image(ExplosionAnimationForAirplane.class.getResource("/Images/airblast" + number +".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(airplane);
                User.getCurrentUser().getLastGameLauncher().getPane().getChildren().remove(airplane);
//                MainMenu mainMenu = User.getCurrentUser().getMainMenu();
                EndgameMenu endgameMenu = new EndgameMenu();
                stage.setScene(null);
                try {
//                    mainMenu.start(stage);
                    endgameMenu.start(stage);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}

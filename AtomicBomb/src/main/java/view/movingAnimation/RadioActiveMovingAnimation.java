package view.movingAnimation;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.Game;
import model.RadioActiveBomb;
import view.GameLauncher;

public class RadioActiveMovingAnimation extends Transition  {

    private final Game game;
    private final Pane pane;
    private final Airplane airplane;
    private final int duration = 1000;
    private final RadioActiveBomb radioActiveBomb;
    private final GameLauncher gameLauncher;
    private final Label label ;

    public RadioActiveMovingAnimation(Game game, Pane pane, Airplane airplane, RadioActiveBomb radioActiveBomb , GameLauncher gameLauncher, Label label){
        this.game = game;
        this.gameLauncher = gameLauncher;
        this.pane = pane;
        this.radioActiveBomb = radioActiveBomb;
        this.label = label;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
        game.addTransitionsToAnimation(this);
    }

    @Override
    protected void interpolate(double v) {

        double y = radioActiveBomb.getY() - 0.5;

        if(radioActiveBomb.getBoundsInParent().intersects(airplane.getBoundsInParent())){
            pane.getChildren().remove(radioActiveBomb);
            if(radioActiveBomb.getRadioActiveMovingAnimation()!=null){
                gameLauncher.setNumberOfRadioActives(gameLauncher.getNumberOfRadioActives()+1);
                radioActiveBomb.setShootingRadioActiveAnimation(null);
                String number = label.getText();
                int num = Integer.parseInt(number) + 1;
                label.setText("" + num);
            }
        }


        if(y<=0){
            pane.getChildren().remove(radioActiveBomb);
        }
        radioActiveBomb.setY(y);

    }

}

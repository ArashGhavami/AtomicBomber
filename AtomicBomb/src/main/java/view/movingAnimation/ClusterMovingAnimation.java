package view.movingAnimation;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Airplane;
import model.ClusterBomb;
import model.Game;
import view.GameLauncher;

public class ClusterMovingAnimation extends Transition {



    private final Game game;
    private final Pane pane;
    private final Airplane airplane;
    private final int duration = 1000;
    private final ClusterBomb clusterBomb;
    private final GameLauncher gameLauncher;
    private final Label label ;

    public ClusterMovingAnimation(Game game, Pane pane, Airplane airplane, ClusterBomb clusterBomb, GameLauncher gameLauncher, Label label) {
        this.game = game;
        this.pane = pane;
        this.airplane = airplane;
        this.clusterBomb = clusterBomb;
        this.gameLauncher = gameLauncher;
        this.label = label;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(duration));
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {

        double y = clusterBomb.getY() - 0.5;

        if(clusterBomb.getBoundsInParent().intersects(airplane.getBoundsInParent())){
            pane.getChildren().remove(clusterBomb);
            if(clusterBomb.getClusterMovingAnimation()!=null){
                gameLauncher.setNumberOfClusters(gameLauncher.getNumberOfClusters() +1);
                clusterBomb.setClusterMovingAnimation(null);
                String number = label.getText();
                int num = Integer.parseInt(number) + 1;
                label.setText("" + num);
            }
        }


        if(y<=0){
            pane.getChildren().remove(clusterBomb);
        }
        clusterBomb.setY(y);
    }
}

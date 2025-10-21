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
import model.Tree;
import model.User;
import view.explosionAnimation.ExplosionAnimationForTree;

public class AtomicExplosionAnimationForTree extends Transition {


    private final Tree tree;
    private final Pane pane;
    private final Group trees;
    private int status;

    public AtomicExplosionAnimationForTree(Tree tree, Pane pane, Group trees, int status) {
        this.tree = tree;
        this.pane = pane;
        this.status =status;
        this.trees = trees;
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
            tree.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/bigblast" + number + ".png").toExternalForm())));
        }else{
            int number = 1;
            if(v >= 0 && v <= 0.33)number = 1;
            else if (v>= 0.33 && v <= 0.66) number = 2;
            else number = 3;

            tree.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/blast" + number + ".png").toExternalForm())));
        }


        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                trees.getChildren().remove(tree);

            }
        });

    }


    }

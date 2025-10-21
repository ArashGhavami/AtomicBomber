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
import model.Tree;
import model.User;

public class ExplosionAnimationForTree extends Transition {

    private final Tree tree;
    private final Pane pane;
    private final Group trees;

    public ExplosionAnimationForTree(Tree tree, Pane pane, Group trees) {
        this.tree = tree;
        this.pane = pane;
        this.trees = trees;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(250));
        Game game = User.getCurrentUser().getLastGameLauncher().getGame();
        game.addTransitionsToAnimation(this);
    }




    @Override
    protected void interpolate(double v) {
        int number = 1;
        if(v >=0 && v <= 0.33) number = 1;
        else if (v> 0.33 && v <0.66) number = 2;
        else if(v >=0.66 && v < 1) number =3;

        tree.setFill(new ImagePattern(new Image(ExplosionAnimationForTree.class.getResource("/Images/fire" + number +".png").toExternalForm())));

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                trees.getChildren().remove(tree);

            }
        });


    }
}

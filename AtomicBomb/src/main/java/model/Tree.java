package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Tree extends Rectangle {

    private final static double WIDTH = 80;
    private final static double HEIGH = 110;
    private boolean isHit;

    public Tree(Pane pane, Game game, int treeType){
        super(WIDTH,HEIGH);
        this.isHit = false;
        Random random = new Random();
        setX(random.nextDouble(825) +1);
        setY(580);
        if(treeType == 1) {
            this.setFill(new ImagePattern(new Image(Tree.class.getResource("/Images/tree3.png").toExternalForm())));
        }
        if(treeType == 2){
            this.setFill(new ImagePattern(new Image(Tree.class.getResource("/Images/tree2.png").toExternalForm())));
        }
        if(treeType == 3){
            this.setFill(new ImagePattern( new Image(Tree.class.getResource("/Images/tree.png").toExternalForm())));
        }
    }


    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}

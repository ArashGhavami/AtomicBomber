package view.shootingAnimation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.*;
import view.GameLauncher;
import view.atomicExplosionAnimation.*;

public class ShootingRadioActiveAnimation extends Transition {


    private final Game game;
    private final Pane pane;
    private final RadioActiveBomb radioActiveBomb;
    private final double speed = 1.5;
    private final int duration = 100;
    private final Airplane airplane;


    public ShootingRadioActiveAnimation(Game game, Pane pane, RadioActiveBomb radioActiveBomb, Airplane airplane) {
        this.game = game;
        this.pane = pane;
        this.radioActiveBomb = radioActiveBomb;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {
        GameLauncher userGameLauncher = User.getCurrentUser().getLastGameLauncher();
        double y = radioActiveBomb.getY() + 2;

        for(Node child: game.getTrees().getChildren()){
            Tree tree = (Tree) child;
            if(radioActiveBomb.getBoundsInParent().intersects(tree.getBoundsInParent())){
                if (tree.isHit()) continue;
                tree.setHit(true);
                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(0);
                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/atomicSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                AtomicExplosionAnimationForTree atomicExplosionAnimationForTree = new AtomicExplosionAnimationForTree(tree, pane, game.getTrees(), 1);
                atomicExplosionAnimationForTree.play();
                pane.getChildren().remove(tree);
                pane.getChildren().remove(radioActiveBomb);
                break;
            }
        }

        for(Node child: game.getTrucks().getChildren()){
            Truck truck = (Truck) child;
            if(radioActiveBomb.getBoundsInParent().intersects(truck.getBoundsInParent())){
                if (truck.isHit()) continue;
                truck.setHit(true);
                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(10);
                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/atomicSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                AtomicExplosionForTruck atomicExplosionForTruck = new AtomicExplosionForTruck(truck, pane , game.getTrucks(), 1);
                atomicExplosionForTruck.play();
                pane.getChildren().remove(truck);
                pane.getChildren().remove(radioActiveBomb);
                break;
            }
        }

        for(Node child: game.getBunkers().getChildren()){
            Bunker bunker = (Bunker) child;
            if(radioActiveBomb.getBoundsInParent().intersects(bunker.getBoundsInParent())){
                if (bunker.isHit()) continue;
                bunker.setHit(true);
                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(15);
                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/atomicSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                AtomicExplosionForBunker atomicExplosionForBunker = new AtomicExplosionForBunker(bunker, pane, game.getBunkers(),1);
                atomicExplosionForBunker.play();
                pane.getChildren().remove(bunker);
                pane.getChildren().remove(radioActiveBomb);
                break;
            }
        }

        for(Node child: game.getBuildings().getChildren()){
            Building building = (Building) child;
            if(radioActiveBomb.getBoundsInParent().intersects(building.getBoundsInParent())){
                if (building.isHit()) continue;
                building.setHit(true);
                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(25);
                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/atomicSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                AtomicExplosionForBuilding atomicExplosionForBuilding = new AtomicExplosionForBuilding(building, pane, game.getBuildings(),1);
                atomicExplosionForBuilding.play();
                pane.getChildren().remove(building);
                pane.getChildren().remove(radioActiveBomb);
                break;
            }
        }

        for(Node child: game.getTanks().getChildren()){
            Tank tank = (Tank) child;
            if(radioActiveBomb.getBoundsInParent().intersects(tank.getBoundsInParent())){
                if (tank.isHit()) continue;
                tank.setHit(true);
                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(20);
                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/atomicSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                AtomicExplosionForTank atomicExplosionForTank = new AtomicExplosionForTank(tank, pane, game.getTanks(),1);
                atomicExplosionForTank.play();
                pane.getChildren().remove(tank);
                pane.getChildren().remove(radioActiveBomb);
                break;
            }
        }


        radioActiveBomb.setY(y);
        try {
            userGameLauncher.checkIfWaveIsFinished();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addTargetBombs(){
        if(!radioActiveBomb.isItInTarget()){
            radioActiveBomb.setItInTarget(true);
            GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
            gameLauncher.setShotsInTarget(gameLauncher.getShotsInTarget() + 1);
        }
    }


}

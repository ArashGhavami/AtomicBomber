package view.shootingAnimation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import model.*;
import view.explosionAnimation.*;
import view.GameLauncher;
import view.movingAnimation.ClusterMovingAnimation;
import view.movingAnimation.RadioActiveMovingAnimation;

public class ShootingBombAnimation extends Transition {

    private final Game game;
    private final Pane pane;
    private final Bomb bomb;
    private final double speed = 1.5;
    private final int duration = 100;
    private final Airplane airplane;
    private final Label label;
    private final GameLauncher gameLauncher;
    private final Label numberOfClusters;


    public ShootingBombAnimation(Game game, Pane pane, Bomb bomb, Airplane airplane, GameLauncher gameLauncher, Label label, Label numberOfClusters) {
        this.game = game;
        this.numberOfClusters = numberOfClusters;
        this.pane = pane;
        this.label = label;
        this.bomb = bomb;
        this.gameLauncher = gameLauncher;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
        game.addTransitionsToAnimation(this);
    }


    @Override
    protected void interpolate(double v) {
        if(User.getCurrentUser() == null) return;
        GameLauncher userGameLauncher = User.getCurrentUser().getLastGameLauncher();
        double y = bomb.getY() + 2;
        double x = bomb.getX() + 0.5;
        for (Node child : game.getTrees().getChildren()) {
            Tree tree = (Tree) child;

            if (tree.getBoundsInParent().intersects(bomb.getBoundsInParent())) {


                if (tree.isHit()) continue;
                tree.setHit(true);
                userGameLauncher.addKillsAfteraBombDropped(0);
                addTargetBombs();
                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/BombExplosion.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                ExplosionAnimationForTree explosionAnimation = new ExplosionAnimationForTree(tree, pane, game.getTrees());
                explosionAnimation.play();
                pane.getChildren().remove(tree);
                pane.getChildren().remove(bomb);
                this.stop();
                break;
            }


        }

        for (Node child : game.getTanks().getChildren()) {
            Tank tank = (Tank) child;
            if (tank.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
                if (tank.isHit()) continue;

                addTargetBombs();
                tank.setHit(true);
                userGameLauncher.addKillsAfteraBombDropped(20);

                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/BombExplosion.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);


                tank.getTankMovingAnimation().stop();
                ExplosionAnimationForTank explosionAnimationForTank = new ExplosionAnimationForTank(tank, pane, game.getTanks());
                explosionAnimationForTank.play();
                pane.getChildren().remove(tank);
                pane.getChildren().remove(bomb);
                this.stop();
                break;
            }

        }

        for (Node child : game.getBuildings().getChildren()) {
            Building building = (Building) child;

            if (building.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
                if (building.isHit()) continue;

                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(25);

                RadioActiveBomb radioActiveBomb = new RadioActiveBomb(airplane);
                radioActiveBomb.setX(building.getX());
                radioActiveBomb.setY(building.getY());
                pane.getChildren().add(radioActiveBomb);
                RadioActiveMovingAnimation radioActiveMovingAnimation = new RadioActiveMovingAnimation(game, pane, airplane, radioActiveBomb, gameLauncher, label);
                radioActiveBomb.setShootingRadioActiveAnimation(radioActiveMovingAnimation);
                radioActiveMovingAnimation.play();
                building.setHit(true);


                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/BombExplosion.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                ExplosionAnimationForBuilding explosionAnimationForBuilding = new ExplosionAnimationForBuilding(building, pane, game.getBuildings(), airplane, gameLauncher);
                explosionAnimationForBuilding.play();

                pane.getChildren().remove(building);
                pane.getChildren().remove(bomb);
                this.stop();
                break;

            }


        }

        for(Node child : game.getBunkers().getChildren()){
            Bunker bunker = (Bunker) child;
            if (bunker.getBoundsInParent().intersects(bomb.getBoundsInParent())) {
                if (bunker.isHit()) continue;

                addTargetBombs();
                userGameLauncher.addKillsAfteraBombDropped(15);

                ClusterBomb clusterBomb = new ClusterBomb(airplane);
                clusterBomb.setY(bunker.getY());
                clusterBomb.setX(bunker.getX());
                pane.getChildren().add(clusterBomb);

                ClusterMovingAnimation clusterMovingAnimation = new ClusterMovingAnimation(game, pane, airplane, clusterBomb, gameLauncher, numberOfClusters);
                clusterBomb.setClusterMovingAnimation(clusterMovingAnimation);
                clusterMovingAnimation.play();

                bunker.setHit(true);

                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/BombExplosion.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);




                ExplosionAnimationForBunker explosionAnimationForBunker = new ExplosionAnimationForBunker(bunker, pane, game.getBunkers());
                explosionAnimationForBunker.play();

                pane.getChildren().remove(bunker);
                pane.getChildren().remove(bomb);
                this.stop();
                break;

            }

        }


        for(Node child : game.getTrucks().getChildren()){
            Truck truck = (Truck) child;
            if (truck.getBoundsInParent().intersects(bomb.getBoundsInParent())) {

                if (truck.isHit()) continue;
                truck.setHit(true);

                addTargetBombs();

                userGameLauncher.addKillsAfteraBombDropped(10);

                Media media = new Media(ShootingBombAnimation.class.getResource("/Music/BombExplosion.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                ExplosionAnimationForTruck explosionAnimationForTruck = new ExplosionAnimationForTruck(truck, pane, game.getTrucks());
                explosionAnimationForTruck.play();

                pane.getChildren().remove(truck);
                pane.getChildren().remove(bomb);
                this.stop();
                break;

            }

        }

        bomb.setY(y);
        try {
            if(userGameLauncher == null) return;
            userGameLauncher.checkIfWaveIsFinished();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void addTargetBombs(){
        if(!bomb.isAppliedForTarget()){
            bomb.setAppliedForTarget(true);
            gameLauncher.setShotsInTarget(gameLauncher.getShotsInTarget() + 1);
        }
    }

}

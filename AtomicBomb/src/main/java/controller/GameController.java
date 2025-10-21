package controller;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import view.GameLauncher;
import view.movingAnimation.AirplaneMovingAnimation;
import view.movingAnimation.TankMovingAnimation;
import view.movingAnimation.TruckMovingAnimation;
import view.shootingAnimation.ShootingBombAnimation;
import view.shootingAnimation.ShootingClusterAnimation;
import view.shootingAnimation.ShootingRadioActiveAnimation;

import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public static void moveDown(Airplane airplane) {
//        if (airplane.getRotate() == 270) return;
//        if (airplane.getRotate() == 90) return;
//        if (airplane.getRotate() % 360 > 90 && airplane.getRotate() % 360 < 270) {
//            airplane.setRotate(airplane.getRotate() - 20);
//            return;
//        }
//        airplane.setRotate(airplane.getRotate() + 20);
//
//        if (airplane.getRotate() % 360 == 270) return;
//        airplane.setRotate(airplane.getRotate() + 10);


        airplane.setY(airplane.getY() + 30);

    }

    public static void moveUp(Airplane airplane) {
//        if (airplane.getRotate() == 270) return;
//        if (airplane.getRotate() == 90) return;
//        if (airplane.getRotate() % 360 > 90 && airplane.getRotate() % 360 < 270) {
//            airplane.setRotate(airplane.getRotate() + 20);
//            return;
//        }
//        airplane.setRotate(airplane.getRotate() - 20);

//        if(airplane.getRotate() % 360 == 90) return;
//        airplane.setRotate(airplane.getRotate() + 10);
        airplane.setY(airplane.getY() - 30);

    }

    public static void firstMoveAirplane(Game game, Pane pane, Airplane airplane, Stage stage) {
        AirplaneMovingAnimation airplaneMovingAnimation = new AirplaneMovingAnimation(game, pane, airplane, stage);
        airplaneMovingAnimation.play();
    }

    public static void moveRigth(Airplane airplane) {
//        if (airplane.getRotate() % 360 == 0 || airplane.getRotate() % 360 == 180) return;
//        airplane.setRotate(airplane.getRotate() - 20);

//        if(airplane.getRotate()%360 == 0) return;
//        airplane.setRotate(airplane.getRotate() + 10);
    }

    public static void moveLeft(Airplane airplane) {
//        if (airplane.getRotate() % 360 == 0 || airplane.getRotate() % 360 == 180) return;
//        airplane.setRotate(airplane.getRotate() + 20);
//
//        if(airplane.getRotate()%360 == 180) return;
//        airplane.setRotate(airplane.getRotate() + 10);
    }

    public static void shootBomb(Pane pane, Airplane airplane, Game game, GameLauncher gameLauncher, Label label, Label clusters) {
        Bomb bomb = new Bomb(airplane);
        pane.getChildren().add(bomb);
        ShootingBombAnimation shootingAnimation = new ShootingBombAnimation(game, pane, bomb, airplane, gameLauncher, label, clusters);
        shootingAnimation.play();
    }

    public static void shootRadioActive(Pane pane, Airplane airplane, Game game, GameLauncher gameLauncher, Label label) {
        if (gameLauncher.getNumberOfRadioActives() == 0) {
            return;
        }
        gameLauncher.setNumberOfRadioActives(gameLauncher.getNumberOfRadioActives() - 1);
        RadioActiveBomb atomicBomb = new RadioActiveBomb(airplane);
        pane.getChildren().add(atomicBomb);
        ShootingRadioActiveAnimation shootingRadioActiveAnimation = new ShootingRadioActiveAnimation(game, pane, atomicBomb, airplane);
        shootingRadioActiveAnimation.play();
        String number = label.getText();
        int num = Integer.parseInt(number) - 1;
        label.setText("" + num);
    }

    public static void firstMoveTank(Game game, Pane pane, Tank tank, double speed) {
        TankMovingAnimation tankMovingAnimation = new TankMovingAnimation(pane, game, tank, speed);
        tank.setTankMovingAnimation(tankMovingAnimation);
        tankMovingAnimation.play();
    }

    public static void firstMoveTruck(Game game, Pane pane, Truck truck) {
        TruckMovingAnimation truckMovingAnimation = new TruckMovingAnimation(pane, game, truck);
        truckMovingAnimation.play();
    }

    public static void addRadioActive(GameLauncher gameLauncher, Label label) {
        gameLauncher.setNumberOfRadioActives(gameLauncher.getNumberOfRadioActives() + 1);
        label.setText("" + gameLauncher.getNumberOfRadioActives());
    }

    public static void addClusterBomb(GameLauncher gameLauncher, Label label) {
        gameLauncher.setNumberOfClusters(gameLauncher.getNumberOfClusters() + 1);
        label.setText(gameLauncher.getNumberOfClusters() + "");
    }

    public static void shootCluster(Pane pane, Game game, Airplane airplane, GameLauncher gameLauncher, Label cluster) {
        if (gameLauncher.getNumberOfClusters() == 0) return;
        gameLauncher.setNumberOfClusters(gameLauncher.getNumberOfClusters() - 1);
        ClusterBomb clusterBomb = new ClusterBomb(airplane);
        pane.getChildren().add(clusterBomb);
        ShootingClusterAnimation shootingClusterAnimation = new ShootingClusterAnimation(game, pane, clusterBomb, airplane);
        shootingClusterAnimation.play();
        String number = cluster.getText();
        int num = Integer.parseInt(number) - 1;
        cluster.setText("" + num);
    }

    public static void goToNextWave() throws Exception {

        GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
        Game game = gameLauncher.getGame();
        for (Transition transition : game.getAnimations()) {
            transition.stop();
        }
        game.getTanks().getChildren().clear();
        game.getTrees().getChildren().clear();
        game.getBunkers().getChildren().clear();
        game.getBuildings().getChildren().clear();
        game.getTrucks().getChildren().clear();

        gameLauncher.setGoToNextWave(true);
        gameLauncher.checkIfWaveIsFinished();

    }

    public static void pauseAnimations() {
        ArrayList<Transition> animations = User.getCurrentUser().getLastGameLauncher().getGame().getAnimations();
        for (Transition transition : animations) {
            transition.stop();
        }
    }

    public static void unPauseAnimations() {
        ArrayList<Transition> animations = User.getCurrentUser().getLastGameLauncher().getGame().getAnimations();
        for (Transition transition : animations)
            transition.play();
    }

    public static void addTank(Game game, Pane pane, double tankSpeed) {
        Tank tank = new Tank(game, pane);
        game.addToTankGroup(tank);
        pane.getChildren().add(tank);
        Random random = new Random();
        int num = random.nextInt(2) + 1;
        if (num == 1) {
            TankMovingAnimation tankMovingAnimation = new TankMovingAnimation(pane, game, tank, tankSpeed);
            tankMovingAnimation.play();
        } else {
            TankMovingAnimation tankMovingAnimation = new TankMovingAnimation(pane, game, tank, -tankSpeed);
            tankMovingAnimation.play();
        }
    }


}

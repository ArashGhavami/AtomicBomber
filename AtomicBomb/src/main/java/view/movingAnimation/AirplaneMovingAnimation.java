package view.movingAnimation;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.GameLauncher;
import view.explosionAnimation.ExplosionAnimationForAirplane;

public class AirplaneMovingAnimation extends Transition {

    private final Game game;

    private final Stage stage;

    private final Pane pane;

    private final double speed = 2;

    private final int duration = 100;

    private final Airplane airplane;

    private boolean doesTheTankShoot;

    private TankBullet tankBullet;

    public AirplaneMovingAnimation(Game game, Pane pane, Airplane airplane, Stage stage){
        this.game = game;
        this.doesTheTankShoot = false;
        this.stage = stage;
        this.pane = pane;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
        game.addTransitionsToAnimation(this);
    }

    @Override
    protected void interpolate(double v) {


        double x = airplane.getX() + speed*(Math.cos(airplane.getRotate()));
        double y = airplane.getY() - speed*(Math.sin(airplane.getRotate()));
        if(airplane.getX() + speed*(Math.cos(airplane.getRotate())) > 825){
            airplane.setX(0);
            return;
        }
        if(airplane.getX() + speed*(Math.cos(airplane.getRotate())) <= 0 ){
            airplane.setX(825);
            return;
        }
        if(airplane.getY() - speed*Math.sin(airplane.getRotate()) <= 0){
            airplane.setRotate(0);
            airplane.setY(0);
            return;
        }
        airplane.setX(x);
        airplane.setY(y);
        checkIfAirplaneIntersects();

        if(airplane.getGameLauncher().getWhichWave() == 2){
            GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
            for(Node node : game.getTanks().getChildren()){
                Tank tank= (Tank) node;
                try {
                    shootToAirplaneFromTank(tank, gameLauncher);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        airplane.requestFocus();
    }

    public void shootToAirplaneFromTank(Tank tank, GameLauncher gameLauncher) throws Exception {
        double deltaX = tank.getX() - airplane.getX();
        double deltaY = tank.getY() - airplane.getY();
        double radius = gameLauncher.getTankRadius();
        deltaY *= deltaY;
        deltaX *= deltaX;
        radius *= radius;

        if(radius >= deltaX + deltaY){
            if(!doesTheTankShoot) {
                doesTheTankShoot = true;
                TankBullet tankBullet = new TankBullet(tank);
                this.tankBullet = tankBullet;
                pane.getChildren().add(tankBullet);
                TankBulletMovingAnimation tankBulletMovingAnimation = new TankBulletMovingAnimation(game, pane, airplane, tankBullet);
                tankBulletMovingAnimation.play();
            }
        }
        else doesTheTankShoot = false;

        if(tankBullet != null) {
            if (tankBullet.getBoundsInParent().intersects(airplane.getBoundsInParent())) {
                GameLauncher usersGameLauncher = User.getCurrentUser().getLastGameLauncher();
                usersGameLauncher.setIsWin(2);
                ExplosionAnimationForAirplane explosionAnimationForAirplane = new ExplosionAnimationForAirplane(airplane, game, pane, stage);
                explosionAnimationForAirplane.play();
//                gameLauncher.stopMusic();

            }
        }
    }

    public void checkIfAirplaneIntersects(){

        boolean intersected = false;

        for(Node node : game.getTrees().getChildren()){
            Tree tree = (Tree) node;
            if(tree.getBoundsInParent().intersects(airplane.getBoundsInParent())){
                intersected = true;
            }
        }

        for(Node node : game.getTrucks().getChildren()){
            Truck truck = (Truck) node;
            if(truck.getBoundsInParent().intersects(airplane.getBoundsInParent())){
                intersected = true;
            }
        }

        for(Node node : game.getTanks().getChildren()){
            Tank tank = (Tank) node;
            if(tank.getBoundsInParent().intersects(airplane.getBoundsInParent())){
                intersected = true;
            }
        }

        for(Node node : game.getBuildings().getChildren()){
            Building building = (Building) node;
            if(building.getBoundsInParent().intersects(airplane.getBoundsInParent())){
                intersected = true;
            }
        }

        for(Node node : game.getBunkers().getChildren()){
            Bunker bunker = (Bunker) node;
            if(bunker.getBoundsInParent().intersects(airplane.getBoundsInParent())){
                intersected = true;
            }
        }


        if(airplane.getY() <= 0){
            intersected = true;
        }

        if(intersected){
            GameLauncher usersGameLauncher = User.getCurrentUser().getLastGameLauncher();
            usersGameLauncher.setIsWin(2);
            ExplosionAnimationForAirplane explosionAnimationForAirplane = new ExplosionAnimationForAirplane(airplane, game, pane, stage);
            explosionAnimationForAirplane.play();
//            usersGameLauncher.stopMusic();
        }

    }




}

package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.GameLauncher;

public class Airplane extends Rectangle {

    private final double WIDTH = 50;
    private final double HEIGHT = 20;
    private final Game game;
    private double airplaneRotation = 0;
    private static final double ROTATION_SPEED = 2;

    private final GameLauncher gameLauncher;


    public Airplane(Game game, GameLauncher gameLauncher) {
        super(50, 20);
        this.game = game;
        setX((game.getWIDTH() - WIDTH) / 2);
        setY(game.getHEIGHT() - WIDTH - 600);
        this.gameLauncher =gameLauncher;
        setFill(new ImagePattern(new Image(Airplane.class.getResource("/Images/plane.png").toExternalForm())));
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public Game getGame() {
        return game;
    }

    public double getAirplaneRotation() {
        return airplaneRotation;
    }

    public GameLauncher getGameLauncher() {
        return gameLauncher;
    }
}

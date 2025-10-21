package view;

import controller.GameController;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;
import view.menus.EndgameMenu;
import view.menus.LoginMenu;
import view.menus.MainMenu;
import view.menus.PauseMenu;
import view.shootingAnimation.ShootingBombAnimation;

import java.net.URL;


public class GameLauncher extends Application {


    private URL mediaURL;

    @FXML
    private Button pause;

    private int shotsUsed;

    private int shotsInTarget;

    private boolean goToNextWave;

    private int isWin; // 1 = win; 2 = lose; 3 = null

    private int whichWave;

    @FXML
    private Label waveNumber;
    @FXML
    private Label numberOfClustersInBoard;
    @FXML
    private ImageView clusterBombIcon;
    @FXML
    private ImageView howManyRadioActives;
    @FXML
    private Label numberOfRadios;
    @FXML
    private StackPane radioActiveBomb;
    private int numberOfRadioActives;
    private Label killsInBoard;
    private int numberOfClusters;
    private int kills;
    private int killsInFirstWave;
    private int killsInSecondWave;
    private int killsInThirdWave;


    private final double WIDTH = 900;
    private final double HEIGHT = 700;
    private Game game;
    private User user;
    private Pane pane;
    private Airplane airplane;
    private static Stage stage;


    private double tankSpeed;
    private double tankRadius;
    private double migSpeed;
    private double migRadius;
    private int hardness;


    public GameLauncher() {
        this.isWin = 3;
        this.whichWave = 1;
        this.mediaURL = GameLauncher.class.getResource("/Music/FirstMusic.mp3");
        this.shotsInTarget = 0;
        this.game = new Game();
        this.kills = 0;
        this.numberOfRadioActives = 0;
        this.shotsUsed = 0;
        this.numberOfClusters = 0;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (!User.getCurrentUser().getSetting().isMute())
                    playMusic();
                return null;
            }
        };
        new Thread(task).start();
        stage.setResizable(false);

        stage.centerOnScreen();
        GameLauncher.stage = stage;

        URL url = GameLauncher.class.getResource("/FXML/GameLauncherMenu.fxml");
        assert url != null;
        pane = FXMLLoader.load(url);
        setSize(pane);
        setIconsForPane();
        createAirplane();
        setGameBasics();
        GameController.firstMoveAirplane(game, pane, airplane, stage);
        for (Node child : game.getTanks().getChildren()) {
            GameController.firstMoveTank(game, pane, (Tank) child, tankSpeed);
        }
        for (Node child : game.getTrucks().getChildren()) {
            GameController.firstMoveTruck(game, pane, (Truck) child);
        }
        pane.getChildren().add(airplane);
        BackgroundImage backgroundImage = createBackgroundImage();
        pane.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        airplane.requestFocus();
    }

    public void setTankSpeed(double tankSpeed) {
        this.tankSpeed = tankSpeed;
    }

    public void setWhichWave(int whichWave) {
        this.whichWave = whichWave;
    }

    public void setWaveNumber(Label waveNumber) {
        this.waveNumber = waveNumber;
    }

    public void setNumberOfClustersInBoard(Label numberOfClustersInBoard) {
        this.numberOfClustersInBoard = numberOfClustersInBoard;
    }

    public int getShotsUsed() {
        return shotsUsed;
    }

    public void setShotsUsed(int shotsUsed) {
        this.shotsUsed = shotsUsed;
    }

    public int getShotsInTarget() {
        return shotsInTarget;
    }

    public void setShotsInTarget(int shotsInTarget) {
        this.shotsInTarget = shotsInTarget;
    }

    public void setClusterBombIcon(ImageView clusterBombIcon) {
        this.clusterBombIcon = clusterBombIcon;
    }

    public void setHowManyRadioActives(ImageView howManyRadioActives) {
        this.howManyRadioActives = howManyRadioActives;
    }

    public void setNumberOfRadios(Label numberOfRadios) {
        this.numberOfRadios = numberOfRadios;
    }

    public void setRadioActiveBomb(StackPane radioActiveBomb) {
        this.radioActiveBomb = radioActiveBomb;
    }

    public void setKillsInBoard(Label killsInBoard) {
        this.killsInBoard = killsInBoard;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setKillsInFirstWave(int killsInFirstWave) {
        this.killsInFirstWave = killsInFirstWave;
    }

    public void setKillsInSecondWave(int killsInSecondWave) {
        this.killsInSecondWave = killsInSecondWave;
    }

    public void setKillsInThirdWave(int killsInThirdWave) {
        this.killsInThirdWave = killsInThirdWave;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public void setTankRadius(double tankRadius) {
        this.tankRadius = tankRadius;
    }

    public void setMigSpeed(double migSpeed) {
        this.migSpeed = migSpeed;
    }

    public void setMigRadius(double migRadius) {
        this.migRadius = migRadius;
    }

    public int getHardness() {
        return hardness;
    }

    public void setGameBasics() {
        setTreeForGame();
        setTankForGame();
        setBuildingForGame();
        setTruckForGame();
        setBunkerForGame();
    }

    public void setIconsForPane() {
        setRadioActiveBomb();
        setNumberOfRadioActivesInBoard();
        setSkullIcon();
        setClusterIcon();
        setNumberOfClustersInBoardIcon();
        putKillsInBoard();
        printWaveIcon();
    }

    public void setSize(Pane pane) {
        pane.setMaxWidth(WIDTH);
        pane.setMinWidth(WIDTH);
        pane.setMaxHeight(HEIGHT);
        pane.setMinHeight(HEIGHT);
    }

    public boolean isGoToNextWave() {
        return goToNextWave;
    }

    public void setGoToNextWave(boolean goToNextWave) {
        this.goToNextWave = goToNextWave;
    }

    public void createAirplane() {
        airplane = new Airplane(game, this);
        airplane.setOnKeyPressed(KeyEvent -> {
            if (KeyEvent.getCode() == KeyCode.UP || KeyEvent.getCode() == KeyCode.W) {
                GameController.moveUp(airplane);
            } else if (KeyEvent.getCode() == KeyCode.DOWN || KeyEvent.getCode() == KeyCode.S) {
                GameController.moveDown(airplane);
            } else if (KeyEvent.getCode() == KeyCode.RIGHT || KeyEvent.getCode() == KeyCode.D) {
                GameController.moveRigth(airplane);
            } else if (KeyEvent.getCode() == KeyCode.LEFT || KeyEvent.getCode() == KeyCode.A) {
                GameController.moveLeft(airplane);
            } else if (KeyEvent.getCode() == KeyCode.SPACE) {
                GameController.shootBomb(pane, airplane, game, this, numberOfRadios, numberOfClustersInBoard);
                shotsUsed++;
            } else if (KeyEvent.getCode() == KeyCode.R) {
                GameController.shootRadioActive(pane, airplane, game, this, numberOfRadios);
                shotsUsed++;
            } else if (KeyEvent.getCode() == KeyCode.G) {
                GameController.addRadioActive(this, numberOfRadios);
            } else if (KeyEvent.getCode() == KeyCode.CONTROL) {
                GameController.addClusterBomb(this, numberOfClustersInBoard);
            } else if (KeyEvent.getCode() == KeyCode.C) {
                GameController.shootCluster(pane, game, airplane, this, numberOfClustersInBoard);
                shotsUsed++;
            } else if (KeyEvent.getCode() == KeyCode.P) {
                try {
                    GameController.goToNextWave();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (KeyEvent.getCode() == KeyCode.T) {
                GameController.addTank(game, pane, tankSpeed);
            }
        });
    }

    private BackgroundImage createBackgroundImage() {
        Image image = new Image(LoginMenu.class.getResource("/Images/gameBackground.png").toExternalForm(), WIDTH, HEIGHT, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        return backgroundImage;

    }

    public int getNumberOfRadioActives() {
        return numberOfRadioActives;
    }

    public void setNumberOfRadioActives(int numberOfRadioActives) {
        this.numberOfRadioActives = numberOfRadioActives;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getWhichWave() {
        return whichWave;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        GameLauncher.stage = stage;
    }

    public void setTreeForGame() {
        for (int i = 0; i < 3; i++) {
            Tree tree = new Tree(pane, game, 1);
            game.addToTreeGroup(tree);
        }
        for (int i = 0; i < 3; i++) {
            Tree tree = new Tree(pane, game, 2);
            game.addToTreeGroup(tree);
        }
        for (int i = 0; i < 3; i++) {
            Tree tree = new Tree(pane, game, 3);
            game.addToTreeGroup(tree);
        }
        pane.getChildren().add(game.getTrees());
    }

    public void setTankForGame() {
        Tank tank = new Tank(game, pane);
        game.addToTankGroup(tank);
        pane.getChildren().add(game.getTanks());
    }

    public void setBuildingForGame() {
        for (int i = 0; i < 4; i++) {
            Building building = new Building(game, pane);
            game.addToBuildingGroup(building);

        }
        pane.getChildren().add(game.getBuildings());
    }

    public void setBunkerForGame() {
        for (int i = 0; i < 2; i++) {
            Bunker bunker = new Bunker();
            game.addToBunkerGroup(bunker);
        }
        pane.getChildren().add(game.getBunkers());
    }

    public void setTruckForGame() {
        for (int i = 0; i < 2; i++) {
            Truck truck = new Truck(game, pane);
            game.addToTruckGroup(truck);
        }
        pane.getChildren().add(game.getTrucks());
    }

    private void printWaveIcon() {
        waveNumber = new Label();
        if (whichWave == 1) waveNumber.setText("Wave #1");
        if (whichWave == 2) waveNumber.setText("Wave #2");
        if (whichWave == 3) waveNumber.setText("Wave #3");
        waveNumber.setStyle("-fx-font-weight: bold;");
        waveNumber.setStyle("-fx-font-size: 20px;");
        double layoutX = 90;
        double layoutY = 31;
        waveNumber.setLayoutX(layoutX);
        waveNumber.setLayoutY(layoutY);
        pane.getChildren().add(waveNumber);
    }

    public void setRadioActiveBomb() {
        Image image = new Image(GameLauncher.class.getResource("/Images/nukeicon.png").toExternalForm());
//        ImageView imageView = new ImageView(image);
        howManyRadioActives = new ImageView(image);
        double x = 27;
        double y = 31;
        howManyRadioActives.setLayoutX(x);
        howManyRadioActives.setLayoutY(y);
        StackPane root = new StackPane();
        root.getChildren().add(howManyRadioActives);
        pane.getChildren().add(root);


    }

    public void setNumberOfRadioActivesInBoard() {
        numberOfRadios = new Label();
        numberOfRadios.setText("" + numberOfRadioActives);
        numberOfRadios.setStyle("-fx-font-weight: bold;");
        numberOfRadios.setStyle("-fx-font-size: 20px;");
        double layoutX = 46;
        double layoutY = 5;
        numberOfRadios.setLayoutY(layoutY);
        numberOfRadios.setLayoutX(layoutX);
        pane.getChildren().add(numberOfRadios);
    }

    public void setSkullIcon() {
        Image image = new Image(GameLauncher.class.getResource("/Images/skullIcon.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setLayoutY(31);
        imageView.setLayoutX(28);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        StackPane root = new StackPane();
        root.setLayoutX(100);
        root.getChildren().add(imageView);
        pane.getChildren().add(root);
    }

    public void setClusterIcon() {
        Image image = new Image(GameLauncher.class.getResource("/Images/rocketicon.png").toExternalForm());
        clusterBombIcon = new ImageView(image);
        clusterBombIcon.setTranslateX(-2);
        clusterBombIcon.setTranslateY(36);
        StackPane root = new StackPane();
        root.getChildren().add(clusterBombIcon);
        pane.getChildren().add(root);
    }

    public void setNumberOfClustersInBoardIcon() {
        numberOfClustersInBoard = new Label();
        numberOfClustersInBoard.setText("" + numberOfClusters);
        numberOfClustersInBoard.setStyle("-fx-font-weight: bold;");
        numberOfClustersInBoard.setStyle("-fx-font-size: 20px;");
        numberOfClustersInBoard.setLayoutX(46);
        numberOfClustersInBoard.setLayoutY(35);
        pane.getChildren().add(numberOfClustersInBoard);
    }

    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public void putKillsInBoard() {
        killsInBoard = new Label();
        killsInBoard.setText(kills + "");
        killsInBoard.setStyle("-fx-font-weight: bold;");
        killsInBoard.setStyle("-fx-font-size: 20px;");
        killsInBoard.setLayoutY(5);
        killsInBoard.setLayoutX(137);
        pane.getChildren().add(killsInBoard);
    }

    public Label getWaveNumber() {
        return waveNumber;
    }

    public Label getNumberOfClustersInBoard() {
        return numberOfClustersInBoard;
    }

    public ImageView getClusterBombIcon() {
        return clusterBombIcon;
    }

    public ImageView getHowManyRadioActives() {
        return howManyRadioActives;
    }

    public Label getNumberOfRadios() {
        return numberOfRadios;
    }

    public StackPane getRadioActiveBomb() {
        return radioActiveBomb;
    }

    public Label getKillsInBoard() {
        return killsInBoard;
    }

    public int getKills() {
        return kills;
    }

    public int getKillsInFirstWave() {
        return killsInFirstWave;
    }

    public int getKillsInSecondWave() {
        return killsInSecondWave;
    }

    public int getKillsInThirdWave() {
        return killsInThirdWave;
    }

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public User getUser() {
        return user;
    }

    public double getMigRadius() {
        return migRadius;
    }

    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }

    public double getMigSpeed() {
        return migSpeed;
    }

    public double getTankRadius() {
        return tankRadius;
    }

    public double getTankSpeed() {
        return tankSpeed;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void addKillsAfteraBombDropped(int kills) {
        if (whichWave == 1) killsInFirstWave += kills;
        if (whichWave == 2) killsInSecondWave += kills;
        if (whichWave == 3) killsInThirdWave += kills;
        this.kills += kills;
        killsInBoard.setText(this.kills + "");

    }

    public void checkIfWaveIsFinished() throws Exception {

        if (!goToNextWave) {
            if (!game.getTanks().getChildren().isEmpty()) return;
            if (!game.getBuildings().getChildren().isEmpty()) return;
            if (!game.getTrucks().getChildren().isEmpty()) return;
            if (!game.getBunkers().getChildren().isEmpty()) return;
            if (!game.getTrees().getChildren().isEmpty()) return;
        }
        goToNextWave = false;

        if (whichWave == 3) {
            isWin = 1;
            MainMenu mainMenu = User.getCurrentUser().getMainMenu();
            stage.setScene(null);
            EndgameMenu endgameMenu = new EndgameMenu();
            endgameMenu.start(stage);
//            stopMusic();
            return;
        }

        //TODO: finish the game

        whichWave++;
        printWaveIcon();
        stage.setScene(null);
        pane.getChildren().clear();
        start(stage);
    }


    public void pause(MouseEvent mouseEvent) throws Exception {
        GameController.pauseAnimations();
        PauseMenu pauseMenu = new PauseMenu();
        pauseMenu.start(new Stage());
    }

    public void playMusic() {
        if (mediaURL == null) return;
        if(User.getCurrentUser().getSetting().isMute()) return;

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (!User.getCurrentUser().getSetting().isMute()){


                    Media media = new Media(mediaURL.toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                }
                return null;
            }
        };
        new Thread(task).start();



    }



    public void stopMusic(){
        if(mediaURL == null ) return;
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (!User.getCurrentUser().getSetting().isMute()){
                    Media media = new Media(mediaURL.toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.stop();
                }
                return null;
            }
        };
        new Thread(task).start();
    }


}
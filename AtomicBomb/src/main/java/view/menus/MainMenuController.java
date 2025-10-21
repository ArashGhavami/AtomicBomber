package view.menus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Setting;
import model.User;
import view.GameLauncher;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    @FXML
    private   Label usernamePlace;
    @FXML
    private StackPane avatarPlace;
    @FXML
    private Circle circle;

    public void exit(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("are you sure you want to exit?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        usernamePlace.setText("Username: " + User.getCurrentUser().getUsername());
        setAvatar(mainMenu.getAvatarURL());
    }


    public void setAvatar(URL url){
        Image avatarImage = new Image(String.valueOf(url));
        ImagePattern imagePattern = new ImagePattern(avatarImage);
        circle.setFill(imagePattern);
    }


    public void openAvatarMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Stage stage = mainMenu.getStage();
        stage.setScene(null);
        AvatarMenu avatarMenu = new AvatarMenu();
        avatarMenu.start(stage);
    }

    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception{
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Stage stage = mainMenu.getStage();
        stage.setScene(null);
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(stage);
    }

    public void showScoreBoard(MouseEvent mouseEvent) {
        
    }

    public void startNewGame(MouseEvent mouseEvent) throws Exception{
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Setting setting = User.getCurrentUser().getSetting();
        GameLauncher gameLauncher = new GameLauncher();
        gameLauncher.setTankRadius(setting.getTankRadius());
        gameLauncher.setTankSpeed(setting.getTankSpeed());
        gameLauncher.setHardness(setting.getHardness());
        gameLauncher.setMigRadius(setting.getMigRadius());
        gameLauncher.setMigSpeed(setting.getMigSpeed());
        Stage stage = mainMenu.getStage();
        stage.setScene(null);
        User.addGameLauncher(gameLauncher);
        gameLauncher.start(stage);
    }

    public void enterSettings(MouseEvent mouseEvent)throws  Exception {
        MainMenu mainMenu =  User.getCurrentUser().getMainMenu();
        Stage stage = mainMenu.getStage();
        stage.setScene(null);
        SettingsMenu settingsMenu = new SettingsMenu();
        settingsMenu.start(stage);
    }


    public void enterScoreBoard(MouseEvent mouseEvent) throws Exception {
        ScoreBoardMenu scoreBoardMenu = new ScoreBoardMenu();
        Stage stage = User.getCurrentUser().getMainMenu().getStage();
        stage.setScene(null);
        scoreBoardMenu.start(stage);
    }

    public void continueLastGame(MouseEvent mouseEvent) throws Exception{

        if(User.getCurrentUser().getLastGameLauncher() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Continue Game Error");
            alert.setContentText("there is no game that you have saved before");
            alert.show();
            return;
        }
        GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
        Stage stage = User.getCurrentUser().getMainMenu().getStage();
        stage.setScene(null);
        gameLauncher.start(stage);
    }
}




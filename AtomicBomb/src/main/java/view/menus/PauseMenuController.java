package view.menus;

import controller.GameController;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Game;
import model.User;
import view.GameLauncher;

public class PauseMenuController {

    @FXML
    private static Label up;
    @FXML
    private static Label down;
    @FXML
    private static Label left;
    @FXML
    private static Label right;

    public void resume(MouseEvent mouseEvent) {
        GameController.unPauseAnimations();
        Stage stage = PauseMenu.getStage();
        stage.close();
    }

    public void goToMainMenu(MouseEvent mouseEvent) throws Exception{
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setContentText("are you sure you want to quit game?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK) {
            MainMenu mainMenu = User.getCurrentUser().getMainMenu();
            Stage stage = PauseMenu.getStage();
            stage.close();
            Stage mainStage = GameLauncher.getStage();
            mainStage.setScene(null);
            mainMenu.start(mainStage);
            User user = User.getCurrentUser();
            user.getGameLaunchers().removeLast();
        }
    }


    public void guide(MouseEvent mouseEvent) throws Exception{
        Stage stage = PauseMenu.getStage();
        stage.setScene(null);
        GuideMenu guideMenu = new GuideMenu();
        guideMenu.start(stage);
    }

    public void saveGame(MouseEvent mouseEvent) throws  Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("save and quit");
        alert.setContentText("are you sure you want to quit?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK) {
            GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
            Game game = gameLauncher.getGame();
            for (Transition transition : game.getAnimations()) {
                transition.stop();
            }
            User.getCurrentUser().setLastGameLauncher(gameLauncher);
            MainMenu mainMenu = User.getCurrentUser().getMainMenu();
            Stage stage = GameLauncher.getStage();
            stage.setScene(null);
            mainMenu.start(stage);
            PauseMenu.getStage().close();
        }

    }

    public void mute(MouseEvent mouseEvent) {
        GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
        gameLauncher.stopMusic();
    }

    public void unmute(MouseEvent mouseEvent) {
        GameLauncher gameLauncher = User.getCurrentUser().getLastGameLauncher();
        gameLauncher.stopMusic();
        gameLauncher.playMusic();
    }
}

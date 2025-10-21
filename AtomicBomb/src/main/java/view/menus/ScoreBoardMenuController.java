package view.menus;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

public class ScoreBoardMenuController {


    public void showRanking(MouseEvent mouseEvent) throws Exception {
        String buttonId = returnIdByGettingEvent(mouseEvent);
        if (buttonId.equals("score")) showRankingBasedOnScore();
        else if (buttonId.equals("lastWave")) showRankingBasedOnLastWave();
        else if (buttonId.equals("kill")) showRankingBasedOnKill();
        else if (buttonId.equals("hardness")) showRankingBasedOnHardness();
        else if (buttonId.equals("accuracy")) showRankingBasedOnAccuracy();

    }

    public String returnIdByGettingEvent(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        return button.getId();
    }

    public void showRankingBasedOnScore() throws Exception {
        Stage stage = ScoreBoardMenu.getStage();
        stage.setScene(null);
        ShowRankingBasedOnScore showRankingBasedOnScore = new ShowRankingBasedOnScore();
        showRankingBasedOnScore.start(stage);
    }

    public void showRankingBasedOnLastWave()throws Exception {
        Stage stage = ScoreBoardMenu.getStage();
        stage.setScene(null);
        ShowRankingBasedOnLastWave showRankingBasedOnLastWave = new ShowRankingBasedOnLastWave();
        showRankingBasedOnLastWave.start(stage);
    }

    public void showRankingBasedOnKill() throws Exception {
        Stage stage = ScoreBoardMenu.getStage();
        stage.setScene(null);
        ShowRankingBasedOnKills showRankingBasedOnKills = new ShowRankingBasedOnKills();
        showRankingBasedOnKills.start(stage);
    }

    public void showRankingBasedOnHardness() throws Exception{
        Stage stage = ScoreBoardMenu.getStage();
        stage.setScene(null);
        ShowRankingBasedOnHardness showRankingBasedOnHardness = new ShowRankingBasedOnHardness();
        showRankingBasedOnHardness.start(stage);
    }

    public void showRankingBasedOnAccuracy() throws Exception{
        Stage stage = ScoreBoardMenu.getStage();
        stage.setScene(null);
        ShowRankingBasedOnAccuracy showRankingBasedOnAccuracy = new ShowRankingBasedOnAccuracy();
        showRankingBasedOnAccuracy.start(stage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Stage stage = ScoreBoardMenu.getStage();
        stage.setScene(null);
        mainMenu.start(stage);
    }
}

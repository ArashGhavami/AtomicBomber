package view.menus;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class EndgameMenu extends Application implements Initializable {

    @FXML
    private Label win;
    @FXML
    private Label accuracy;
    @FXML
    private Label kill;
    @FXML
    private Button home;

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        EndgameMenu.stage = stage;
        URL url = EndgameMenu.class.getResource("/FXML/Endgame.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(User.getCurrentUser().getLastGameLauncher().getIsWin() == 1)
            win.setText("winner!");
        else if(User.getCurrentUser().getLastGameLauncher().getIsWin() == 2)
            win.setText("loser!");
        setLabelColor(win);
        win.setStyle("-fx-font-size: 23px; -fx-text-fill: #CE373D;");
        if(User.getCurrentUser().getLastGameLauncher().getShotsUsed() != 0) {
            double accNum = (double) User.getCurrentUser().getLastGameLauncher().getShotsInTarget() / User.getCurrentUser().getLastGameLauncher().getShotsUsed();

            accNum *=100;
            String formattedValue1 = String.format("%.2f", accNum);
            accuracy.setText("accuracy : " + formattedValue1 + "%");
        }else{
            accuracy.setText("accuracy : 0.00%");
        }
        setLabelColor(accuracy);
        kill.setText("kills : " + User.getCurrentUser().getLastGameLauncher().getKills());

        setLabelColor(kill);
        home.setText("go to main menu");

    }


    public void goToMainMenu(MouseEvent mouseEvent) throws Exception {

        stage.setScene(null);
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        mainMenu.start(stage);

    }

    public void setLabelColor(Label label){
        label.setStyle("-fx-text-fill: #CE373D;");
    }
}

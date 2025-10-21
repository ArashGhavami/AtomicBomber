package view.menus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Setting;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {


    @FXML
    private ChoiceBox<String> hardness;
    @FXML
    private CheckBox muteOrNot;
    @FXML
    private CheckBox blackOrNot;
    @FXML
    private ChoiceBox<KeyCode> up;
    @FXML
    private ChoiceBox<KeyCode> down;
    @FXML
    private ChoiceBox<KeyCode> right;
    @FXML
    private ChoiceBox<KeyCode> left;

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        mainMenu.start(SettingsMenu.getStage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addOptionToChoiceBox(up);
        addOptionToChoiceBox(down);
        addOptionToChoiceBox(right);
        addOptionToChoiceBox(left);
        Setting setting = User.getCurrentUser().getSetting();
        up.setValue(setting.getUp());
        down.setValue(setting.getDown());
        left.setValue(setting.getLeft());
        right.setValue(setting.getRight());
        addOptionToHardnessChoiceBox(hardness);
        if(setting.getHardness() == 1) hardness.setValue("easy");
        else if(setting.getHardness() == 2) hardness.setValue("medium");
        else if(setting.getHardness() == 3) hardness.setValue("hard");

        if(setting.isMute()) muteOrNot.setSelected(true);
        else muteOrNot.setSelected(false);

        if(setting.isBlackAndWhite()) blackOrNot.setSelected(true);
        else blackOrNot.setSelected(false);


    }

    public void addOptionToChoiceBox(ChoiceBox<KeyCode> choiceBox){
        choiceBox.getItems().addAll(KeyCode.A, KeyCode.B, KeyCode.C, KeyCode.D, KeyCode.E, KeyCode.F, KeyCode.G, KeyCode.H, KeyCode.I, KeyCode.J,
                KeyCode.K,KeyCode.L, KeyCode.M, KeyCode.N, KeyCode.O, KeyCode.P, KeyCode.Q, KeyCode.R,KeyCode.S,KeyCode.T, KeyCode.U,
                KeyCode.V, KeyCode.W, KeyCode.X,KeyCode.Y,KeyCode.Z);
    }

    public void addOptionToHardnessChoiceBox(ChoiceBox<String> choiceBox){
        choiceBox.getItems().addAll("easy" , "medium" , "hard");
    }

    public void updateData(MouseEvent mouseEvent) throws Exception {
        Setting setting = User.getCurrentUser().getSetting();
        if(muteOrNot.isSelected()) setting.setMute(true);
        else setting.setMute(false);

        if(blackOrNot.isSelected()) setting.setBlackAndWhite(true);
        else setting.setBlackAndWhite(false);

        setting.setDown(down.getValue());
        setting.setUp(up.getValue());
        setting.setLeft(left.getValue());
        setting.setRight(right.getValue());

        if(hardness.getValue().equals("easy")) setting.setHardness(1);
        else if(hardness.getValue().equals("medium")) setting.setHardness(2);
        else if(hardness.getValue().equals("hard")) setting.setHardness(3);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("changes has been updated");
        alert.showAndWait();
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Stage stage = SettingsMenu.getStage();
        stage.setScene(null);
        mainMenu.start(stage);
    }
}


package view.menus;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Setting;
import model.User;
import view.RegisterMenu;


public class RegisterMenuController {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    public void signUp(MouseEvent mouseEvent) {
        if (username.getText() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("username and passwors should atleast have 4 characters!");
            alert.show();
            return;
        }
        if(username.getText().length() < 4 || password.getText().length() < 4){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("username and passwors should atleast have 4 characters!");
            alert.show();
            return;
        }
        MainMenu mainMenu = new MainMenu();
        User user = new User(username.getText(), password.getText(), mainMenu, new Setting());
        mainMenu.setUser(user);
        if (User.doesUserAlreadyExist(user)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Information");
            alert.setContentText("this username and password has already taken!");
            alert.showAndWait();
            return;
        }
        User.addUser(user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Signed up successfully");
        alert.setContentText("Signed up successfully");
        alert.showAndWait();
    }

    public void reset(MouseEvent mouseEvent) {
        password.setText(null);
        username.setText(null);
    }

    public void exitProgram(MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK)
            System.exit(0);
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        Stage stage = RegisterMenu.getStage();
        stage.setScene(null);
        loginMenu.start(stage);
    }




}

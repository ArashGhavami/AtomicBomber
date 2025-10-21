package view.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Setting;
import model.User;
import view.RegisterMenu;

import java.util.Objects;
import java.util.Random;

public class LoginMenuController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void login(MouseEvent mouseEvent) throws Exception {
        if(username.getText() == null || password.getText() == null || Objects.equals(username.getText(), "") || Objects.equals(password.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Failed");
            alert.setContentText("Enter your username and password");
            alert.showAndWait();
            return;
        }
        if(User.getUserByUsername(username.getText()) == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Username");
            alert.setContentText("this username does not exist");
            alert.show();
            return;
        }
        User user = User.getUserByUsername(username.getText());
        assert user != null;
        if(!user.getPassword().equals(password.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Password");
            alert.setContentText("this password does not match the username");
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Successful");
        alert.setContentText("you are logged in successfully");
        alert.showAndWait();
        User.addUser(user);
        User.setCurrentUser(user);
        enterMainMenu(user);
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText(null);
        password.setText(null);
    }

    public void enterSignUpMenu(MouseEvent mouseEvent) throws Exception {
        RegisterMenu registerMenu = new RegisterMenu();
        Stage stage = LoginMenu.getStage();
        stage.setScene(null);
        registerMenu.start(stage);
    }

    public void enterMainMenu(User user) throws Exception {
        Stage stage = LoginMenu.getStage();
        stage.setScene(null);
        MainMenu mainMenu = user.getMainMenu();
        mainMenu.start(stage);
    }


    public void loginAsGuest(MouseEvent mouseEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("login as guest");
        String username = "#guest" + (User.getGusets().size()+1);
        String password = generateRandomPassword() + "";
        alert.setContentText("your username is : " + username + "\n your password is : " + password);
        alert.showAndWait();
        MainMenu mainMenu = new MainMenu();
        User user = new User(username, password, mainMenu, new Setting());
        User.addUser(user);
        User.setCurrentUser(user);
        User.addAsGuest(user);
        enterMainMenu(user);
    }

    public int generateRandomPassword(){
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        return random.nextInt(max - min + 1) + min;
    }
}

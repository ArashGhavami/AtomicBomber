package view.menus;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import view.RegisterMenu;

public class ProfileMenuController {

    public void changeUsername(MouseEvent mouseEvent) throws  Exception{
        Stage stage = ProfileMenu.getStage();
        stage.setScene(null);
        ChangeUsernameMenu changeUsernameMenu = new ChangeUsernameMenu();
        changeUsernameMenu.start(stage);
    }

    public void removeAccount(MouseEvent mouseEvent) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Account");
        alert.setContentText("are you sure you want to remove this account?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            User.removeUser(User.getCurrentUser());
            User.setCurrentUser(null);
            RegisterMenu registerMenu = new RegisterMenu();
            Stage stage = ProfileMenu.getStage();
            stage.setScene(null);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Account Removed Successfully");
            alert1.setContentText("Account Removed Successfully");
            alert1.showAndWait();
            registerMenu.start(stage);
        }
        else return;
    }

    public void logout(MouseEvent mouseEvent) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("are you sure you want to logout?");
        alert.showAndWait();
        if(alert.getResult().equals(ButtonType.OK)){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Logged out Successfully");
            alert1.setContentText("Logged out Successfully");
            alert1.showAndWait();
            User.setCurrentUser(null);
            RegisterMenu registerMenu = new RegisterMenu();
            Stage stage = ProfileMenu.getStage();
            stage.setScene(null);
            registerMenu.start(stage);
        }
    }


    public void back(MouseEvent mouseEvent) throws Exception{
        MainMenu mainMenu = new MainMenu();
        Stage stage = ProfileMenu.getStage();
        stage.setScene(null);
        mainMenu.start(stage);
    }

}

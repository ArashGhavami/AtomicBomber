package view.menus;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

public class ChangeUsernameMenuController {


    public TextField newUsername;

    public TextField newPassword;

    public void changeData(MouseEvent mouseEvent) {
        if(newUsername.getText().length() < 4 || newPassword.getText().length() < 4){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("username and password should at least have 4 characters");
            alert.show();
            return;
        }
        if(User.getUserByUsername(newUsername.getText())== null ||  User.getUserByUsername(newUsername.getText()).equals(User.getCurrentUser().getUsername())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("username and password is changed successfully");
            alert.show();
            User user = User.getCurrentUser();
            user.setUsername(newUsername.getText());
            user.setPassword(newPassword.getText());
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("this username is already taken");
            alert.show();
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception{
        ProfileMenu profileMenu = new ProfileMenu();
        Stage stage = ChangeUsernameMenu.getStage();
        stage.setScene(null);
        profileMenu.start(stage);
    }


}

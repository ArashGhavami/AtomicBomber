package view.menus;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AvatarMenuController implements Initializable {
    @FXML
    private Circle firstAvatar;
    @FXML
    private Circle secondAvatar;
    @FXML
    private Circle thirdAvatar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL url = AvatarMenuController.class.getResource("/Images/firstCharacter.png");
        setAvatar(url, firstAvatar);
        url = AvatarMenuController.class.getResource("/Images/secondCharacter.jpeg");
        setAvatar(url, secondAvatar);
        url = AvatarMenuController.class.getResource("/Images/thirdCharacter.jpeg");
        setAvatar(url, thirdAvatar);
    }

    public void setAvatar(URL url, Circle circle) {
        Image avatarImage = new Image(String.valueOf(url));
        ImagePattern imagePattern = new ImagePattern(avatarImage);
        circle.setFill(imagePattern);
    }

    public void setAsMainAvatar(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Circle circle = (Circle) mouseEvent.getSource();
        String id = circle.getId();
        URL url;
        if (id.equals("firstAvatar")) {
            url = AvatarMenuController.class.getResource("/Images/firstCharacter.png");
            mainMenu.setAvatarURL(url);
        } else if (id.equals("secondAvatar")) {
            url = AvatarMenuController.class.getResource("/Images/secondCharacter.jpeg");
            mainMenu.setAvatarURL(url);
        } else if (id.equals("thirdAvatar")) {
            url = AvatarMenuController.class.getResource("/Images/thirdCharacter.jpeg");
            mainMenu.setAvatarURL(url);
        }
        Stage stage = AvatarMenu.getStage();
        stage.setScene(null);
        mainMenu.start(stage);
    }

    public void chooseRandomAvatar(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        URL url;
        switch (randomNumber) {
            case 1:
                url = AvatarMenuController.class.getResource("/Images/firstCharacter.png");
                mainMenu.setAvatarURL(url);
                break;
            case 2:
                url = AvatarMenuController.class.getResource("/Images/secondCharacter.jpeg");
                mainMenu.setAvatarURL(url);
                break;
            case 3:
                url = AvatarMenuController.class.getResource("/Images/thirdCharacter.jpeg");
                mainMenu.setAvatarURL(url);
                break;
        }
        Stage stage = AvatarMenu.getStage();
        stage.setScene(null);
        mainMenu.start(stage);
    }

    public void chooseMyOwnAvatar(MouseEvent mouseEvent) throws Exception {
        Stage stage = AvatarMenu.getStage();
        stage.setScene(null);
        MyOwnAvatarMenu myOwnAvatarMenu = new MyOwnAvatarMenu();
        myOwnAvatarMenu.start(stage);
    }

    public static void returnToMain(Stage stage) throws Exception {
        stage.setScene(null);
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        mainMenu.start(stage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        returnToMain(AvatarMenu.getStage());
    }
}

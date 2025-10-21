package view.menus;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import view.GameLauncher;

import java.net.URL;

public class MainMenu extends Application {

    private URL avatarURL;
    private Stage stage;
    private User user;

    {
        avatarURL = MainMenu.class.getResource("/Images/firstCharacter.png");
    }

    public void runMainMenu(Stage stage, User user) throws Exception {
        this.user= user;
        start(stage);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        stage.setResizable(false);
        BackgroundImage backgroundImage = createBackgroundImage();
        root.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public  Stage getStage() {
        return stage;
    }

    private BackgroundImage createBackgroundImage(){
        Image image = new Image(LoginMenu.class.getResource("/Images/MainMenuBackground.jpg").toExternalForm(), stage.getMaxWidth(), stage.getMaxHeight(), false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        return backgroundImage;

    }



    public URL getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(URL avatarURL) {
        this.avatarURL = avatarURL;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(User user){
        this.user = user;
    }



}

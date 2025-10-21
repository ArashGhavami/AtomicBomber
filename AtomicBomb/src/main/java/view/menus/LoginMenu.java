package view.menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public class LoginMenu extends Application  {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        URL url = LoginMenu.class.getResource("/FXML/LoginMenu.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        stage.setResizable(false);
        BackgroundImage backgroundImage = createBackgroundImage(stage.getWidth(), stage.getHeight());
        root.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    private BackgroundImage createBackgroundImage(double width, double height){
        Image image = new Image(LoginMenu.class.getResource("/Images/LoginMenuBackground.png").toExternalForm(), width, height+100, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        return backgroundImage;
    }






}
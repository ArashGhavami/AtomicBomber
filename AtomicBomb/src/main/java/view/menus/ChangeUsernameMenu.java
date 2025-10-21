package view.menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public class ChangeUsernameMenu extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        ChangeUsernameMenu.stage = stage;
        URL url = ChangeUsernameMenu.class.getResource("/FXML/ChangeUsernameMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        BackgroundImage backgroundImage = createBackgroundImage();
        pane.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    private BackgroundImage createBackgroundImage(){
        Image image = new Image(LoginMenu.class.getResource("/Images/MainMenuBackground.jpg").toExternalForm(), stage.getMaxWidth(), stage.getMaxHeight(), false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        return backgroundImage;

    }
}

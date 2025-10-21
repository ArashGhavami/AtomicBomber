package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;


public class RegisterMenu extends Application {
    private static Stage stage;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage;
        URL url = RegisterMenu.class.getResource("/FXML/RegisterMenu.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        stage.setResizable(false);
        BackgroundImage backgroundImage = createBackgroundImage();
        root.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage(){
        return RegisterMenu.stage;
    }

    private BackgroundImage createBackgroundImage(){
        Image image = new Image(RegisterMenu.class.getResource("/Images/background.jpeg").toExternalForm(), stage.getMaxWidth(), stage.getMaxHeight(), false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        return backgroundImage;

    }




}

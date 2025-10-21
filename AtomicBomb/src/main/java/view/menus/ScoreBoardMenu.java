package view.menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class ScoreBoardMenu extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardMenu.stage = stage;
        URL url = ScoreBoardMenu.class.getResource("/FXML/ScoreBoardMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}

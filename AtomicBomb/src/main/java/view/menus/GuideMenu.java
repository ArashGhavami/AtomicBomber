package view.menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class GuideMenu extends Application {

    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        GuideMenu.stage = stage;
        URL url = GuideMenu.class.getResource("/FXML/GuideMenu.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        VBox vBox = (VBox) pane.getChildren().stream().filter(node -> node instanceof VBox).findFirst().orElse(null);
        assert vBox != null;
        addLabel(vBox);
        Scene scene =new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    public void addLabel(VBox vBox){
        Label label = new Label();
        label.setText("Up : " + User.getCurrentUser().getSetting().getUp().toString());
        label.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 23px;");
        vBox.getChildren().add(label);

        label = new Label();
        label.setText("Down : " + User.getCurrentUser().getSetting().getDown().toString() );
        label.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 23px;");
        vBox.getChildren().add(label);

        label = new Label();
        label.setText("Left : " + User.getCurrentUser().getSetting().getLeft().toString());
        label.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 23px;");
        vBox.getChildren().add(label);

        label = new Label();
        label.setText("Right : " + User.getCurrentUser().getSetting().getRight());
        label.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 23px;");
        vBox.getChildren().add(label);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        Stage stage = GuideMenu.stage;
        stage.setScene(null);
        PauseMenu pauseMenu = new PauseMenu();
        pauseMenu.start(stage);

    }
}

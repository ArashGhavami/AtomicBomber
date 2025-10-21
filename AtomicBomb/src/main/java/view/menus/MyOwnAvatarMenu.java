package view.menus;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.net.URL;
import java.util.List;

public class MyOwnAvatarMenu extends Application  {
    private static Stage stage;
    @FXML
    private Label label; // for drag and drop

    @Override
    public void start(Stage stage) throws Exception {
        MyOwnAvatarMenu.stage = stage;
        URL url = MyOwnAvatarMenu.class.getResource("/FXML/MyOwnAvatarMenu.fxml");
        assert url != null;
        Pane root = FXMLLoader.load(url);
        BackgroundImage backgroundImage = createBackgroundImage();
        root.setBackground(new Background(backgroundImage));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void browseSystem(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = User.getCurrentUser().getMainMenu();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select file");
        File selectedFile = fileChooser.showOpenDialog(stage);
        URL url = null;
        if(selectedFile!= null){
            url  = selectedFile.toURI().toURL();
        }else {
            return;
        }
        mainMenu.setAvatarURL(url);
        stage.setScene(null);
        mainMenu.start(stage);
    }

    public void dragAndDrop(DragEvent dragEvent) {
        label.setOnDragOver(event -> handleDragOver(event));
        label.setOnDragDropped(event -> {
            try {
                handleDragDropped(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private BackgroundImage createBackgroundImage(){
        Image image = new Image(LoginMenu.class.getResource("/Images/MainMenuBackground.jpg").toExternalForm(), stage.getMaxWidth(), stage.getMaxHeight(), false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        return backgroundImage;

    }

    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != event.getSource() &&
                event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) throws Exception {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            List<File> files = db.getFiles();
            for (File file : files) {

                    MainMenu mainMenu = User.getCurrentUser().getMainMenu();
                    mainMenu.setAvatarURL(file.toURI().toURL());
                    stage.setScene(null);
                    mainMenu.start(stage);
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }



}
package view.menus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import view.GameLauncher;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class ShowRankingBasedOnScore extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = ShowRankingBasedOnScore.class.getResource("/FXML/ShowRanking.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        VBox vBox = (VBox) pane.getChildren().stream().filter(node -> node instanceof VBox).findFirst().orElse(null);

        ArrayList<User> allUsers = getArrayListOfUsersBasedOnScore();
        printColorFull(allUsers, vBox);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public ArrayList<User> getArrayListOfUsersBasedOnScore() {
        ArrayList<User> allUsers = User.getAllUsers();

        ArrayList<Integer> usersScore = new ArrayList<>();
        for (User user : allUsers) {
            usersScore.add(getUserScore(user));
        }
        for (int i = 0; i < usersScore.size(); i++) {
            for (int j = i; j < usersScore.size(); j++) {
                if (usersScore.get(i) < usersScore.get(j)) {
                    Integer temp = usersScore.get(i);
                    usersScore.set(i, usersScore.get(j));
                    usersScore.set(j, temp);

                    User tempUser = allUsers.get(i);
                    allUsers.set(i, allUsers.get(j));
                    allUsers.set(j, tempUser);
                } else if (Objects.equals(usersScore.get(i), usersScore.get(j))) {
                    if ((allUsers.get(i).getUsername().compareTo(allUsers.get(j).getUsername())) > 0) {
                        User tempUser = allUsers.get(i);
                        allUsers.set(i, allUsers.get(j));
                        allUsers.set(j, tempUser);
                    }
                }
            }
        }

        return allUsers;
    }

    public int getUserScore(User user) {
        int howManyWins = 0;
        for (GameLauncher gameLauncher : user.getGameLaunchers()) {
            if (gameLauncher.getIsWin() == 1) {
                howManyWins++;
            }
        }
        return howManyWins;
    }

    public void printColorFull(ArrayList<User> users, VBox vBox) {

        if(users.size() > 3){
            for(int i =0 ; i<3; i++){
                Label label = new Label();
                label.setText(users.get(i).getUsername() + " + " + getUserScore(users.get(i)));
                label.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 23px;");
                assert vBox != null;
                vBox.getChildren().add(label);
            }
            for(int i =3; i< Math.min(10, users.size()) ; i++){
                Label label = new Label();
                label.setText(users.get(i).getUsername() + " + " + getUserScore(users.get(i)));
                label.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 23px;");
                vBox.getChildren().add(label);
            }
        }
        else{
            for(int i =0 ; i<users.size(); i++){
                Label label = new Label();
                label.setText(users.get(i).getUsername() + " + " + getUserScore(users.get(i)));
                label.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 23px;");
                assert vBox != null;
                vBox.getChildren().add(label);
            }
        }


    }


}

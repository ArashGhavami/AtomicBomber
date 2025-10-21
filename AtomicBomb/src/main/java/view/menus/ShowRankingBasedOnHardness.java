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

public class ShowRankingBasedOnHardness extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = ShowRankingBasedOnScore.class.getResource("/FXML/ShowRanking.fxml");
        assert url != null;
        Pane pane = FXMLLoader.load(url);
        VBox vBox = (VBox) pane.getChildren().stream().filter(node -> node instanceof VBox).findFirst().orElse(null);

        ArrayList<User> allUsers = getArrayListOfUsersBasedOnHardness();
        printColorFull(allUsers, vBox);
        Scene scene =new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public ArrayList<User> getArrayListOfUsersBasedOnHardness(){
        ArrayList<User> allUsers = User.getAllUsers();
        ArrayList<Integer> usersKills = new ArrayList<>();
        for(User user : allUsers){
            usersKills.add(getUserKill(user));
        }
        for(int i=0; i<usersKills.size(); i++){
            for(int j=i; j<usersKills.size(); j++){
                if(usersKills.get(i) < usersKills.get(j)){
                    Integer temp = usersKills.get(i);
                    usersKills.set(i,usersKills.get(j));
                    usersKills.set(j, temp);

                    User tempUser = allUsers.get(i);
                    allUsers.set(i, allUsers.get(j));
                    allUsers.set(j, tempUser);
                }
                else if(Objects.equals(usersKills.get(i), usersKills.get(j))){
                    if((allUsers.get(i).getUsername().compareTo(allUsers.get(j).getUsername())) > 0){
                        User tempUser = allUsers.get(i);
                        allUsers.set(i, allUsers.get(j));
                        allUsers.set(j, tempUser);
                    }
                }
            }
        }

        return allUsers;
    }

    public int getUserKill(User user){
        int hardness = 0;
        for(GameLauncher gameLauncher : user.getGameLaunchers()){
            hardness += gameLauncher.getKills() * gameLauncher.getHardness();
        }
        return hardness;
    }

    public void printColorFull(ArrayList<User> users, VBox vBox) {

        if(users.size() > 3){
            for(int i =0 ; i<3; i++){
                Label label = new Label();
                label.setText(users.get(i).getUsername() + " + " + getUserKill(users.get(i)));
                label.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 23px;");
                assert vBox != null;
                vBox.getChildren().add(label);
            }
            for(int i =3; i< Math.min(10, users.size()) ; i++){
                Label label = new Label();
                label.setText(users.get(i).getUsername() + " + " + getUserKill(users.get(i)));
                label.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 23px;");
                vBox.getChildren().add(label);
            }
        }
        else{
            for(int i =0 ; i<users.size(); i++){
                Label label = new Label();
                label.setText(users.get(i).getUsername() + " + " + getUserKill(users.get(i)));
                label.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 23px;");
                assert vBox != null;
                vBox.getChildren().add(label);
            }
        }


    }


}

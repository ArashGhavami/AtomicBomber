package model;

import view.GameLauncher;
import view.menus.MainMenu;

import java.util.ArrayList;

public class User {

    private static User currentUser;

    private GameLauncher lastGameLauncher;

    private Setting setting;

    private static ArrayList<User> allUsers = new ArrayList<>();

    private static ArrayList<User> gusets = new ArrayList<>();

    private String username;

    private String password;

    private MainMenu mainMenu;

    private ArrayList<GameLauncher> gameLaunchers = new ArrayList<>();

    public User(String username, String password, MainMenu mainMenu, Setting setting){
        this.setting = setting;
        this.mainMenu = mainMenu;
        this.password = password;
        this.username = username;
        this.lastGameLauncher = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void addUser(User user){
        allUsers.add(user);
    }

    public static void removeUser(User user){
        allUsers.remove(user);
    }

    public static void setAllUsers(ArrayList<User> users){
        User.allUsers = users;
    }

    public static boolean doesUserAlreadyExist(User targetUser){
        if(allUsers.isEmpty())
            return false;
        for(User user : allUsers){
            if(user.getUsername().equals(targetUser.getUsername()) ){
                return true;
            }
        }
        return false;
    }

    public static User getUserByUsername(String username){
        for(User user : allUsers){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public MainMenu getMainMenu(){
        return mainMenu;
    }

    public static ArrayList<User> getGusets() {
        return gusets;
    }

    public static void setGusets(ArrayList<User> gusets) {
        User.gusets = gusets;
    }

    public static void addAsGuest(User user){
        gusets.add(user);
    }

    public Setting getSetting() {
        return setting;
    }

    public static void addGameLauncher(GameLauncher gameLauncher){
        User.getCurrentUser().getGameLaunchers().add(gameLauncher);
    }

    public ArrayList<GameLauncher> getGameLaunchers() {
        return gameLaunchers;
    }

    public GameLauncher getLastGameLauncher(){
        int num = gameLaunchers.size();
        if(gameLaunchers.isEmpty()) return null;
        return gameLaunchers.get(num -1);
    }

    public void setLastGameLauncher(GameLauncher lastGameLauncher) {
        this.lastGameLauncher = lastGameLauncher;
    }
}

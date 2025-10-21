package model;

import javafx.scene.input.KeyCode;

public class Setting {


    private int hardness;
    private boolean isMute;
    private boolean isBlackAndWhite;
    private KeyCode up;
    private KeyCode down;
    private KeyCode right;
    private KeyCode left;

    private final double tankFirstSpeed = 0.75;
    private final double tankFirstRadius = 120;
    private final double migFirstSpeed = 1.25;
    private final double migFirstRadius = 30;

    private double tankSpeed;
    private double migRadius;
    private double tankRadius;
    private double migSpeed;

    public Setting(){
        this.hardness = 1;
        this.isMute = false;
        this.isBlackAndWhite = false;
        this.up = KeyCode.W;
        this.down = KeyCode.S;
        this.right = KeyCode.D;
        this.left = KeyCode.A;
        setTankAndMigAsEasy();
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;

        if(hardness == 1){
            setTankAndMigAsEasy();
        }
        if(hardness == 2){
            setTankAndMigAsMedium();
        }
        if(hardness == 3){
            setTankAndMigAsHard();
        }
    }

    public boolean isMute() {
        return isMute;
    }

    public void setMute(boolean mute) {
        isMute = mute;
    }

    public boolean isBlackAndWhite() {
        return isBlackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        isBlackAndWhite = blackAndWhite;
    }

    public KeyCode getUp() {
        return up;
    }

    public void setUp(KeyCode up) {
        this.up = up;
    }

    public KeyCode getDown() {
        return down;
    }

    public void setDown(KeyCode down) {
        this.down = down;
    }

    public KeyCode getRight() {
        return right;
    }

    public void setRight(KeyCode right) {
        this.right = right;
    }

    public KeyCode getLeft() {
        return left;
    }

    public void setLeft(KeyCode left) {
        this.left = left;
    }

    public void setTankAndMigAsEasy(){
        this.tankSpeed = tankFirstSpeed;
        this.tankRadius = tankFirstRadius;
        this.migRadius = migFirstRadius;
        this.migSpeed = migFirstSpeed;
    }

    public void setTankAndMigAsMedium(){
        this.tankSpeed = 2*tankFirstSpeed;
        this.tankRadius = 2*tankFirstRadius;
        this.migRadius = 2*migFirstRadius;
        this.migSpeed = 4*migFirstSpeed/3;
    }

    public void setTankAndMigAsHard(){
        this.tankSpeed = 3*tankFirstSpeed;
        this.tankRadius = 3*tankFirstRadius;
        this.migRadius = 3*migFirstRadius;
        this.migSpeed = 2*migFirstSpeed;
    }

    public double getTankFirstSpeed() {
        return tankFirstSpeed;
    }

    public double getTankFirstRadius() {
        return tankFirstRadius;
    }

    public double getMigFirstSpeed() {
        return migFirstSpeed;
    }

    public double getMigFirstRadius() {
        return migFirstRadius;
    }

    public double getTankSpeed() {
        return tankSpeed;
    }

    public double getMigRadius() {
        return migRadius;
    }

    public double getTankRadius() {
        return tankRadius;
    }

    public double getMigSpeed() {
        return migSpeed;
    }
}
package model;

import javafx.animation.Transition;
import javafx.scene.Group;

import java.util.ArrayList;

public class Game {

    private final double WIDTH = 900;
    private final double HEIGHT = 700;

    private Group trees = new Group();
    private Group tanks = new Group();
    private Group buildings = new Group();
    private Group bunkers = new Group();
    private Group trucks = new Group();

    private ArrayList<Transition> animations = new ArrayList<>();

    public double getWIDTH() {
        return WIDTH;
    }

    public double getHEIGHT() {
        return HEIGHT;
    }

    public void addToTreeGroup(Tree tree){
        trees.getChildren().add(tree);
    }

    public Group getTrees() {
        return trees;
    }

    public void addToTankGroup(Tank tank){
        tanks.getChildren().add(tank);
    }

    public Group getTanks(){
        return tanks;
    }

    public Group getBuildings() {
        return buildings;
    }

    public void addToBuildingGroup(Building building){
        buildings.getChildren().add(building);
    }

    public void addToBunkerGroup(Bunker bunker){
        bunkers.getChildren().add(bunker);
    }

    public void addToTruckGroup(Truck truck){
        trucks.getChildren().add(truck);
    }

    public Group getBunkers() {
        return bunkers;
    }

    public Group getTrucks() {
        return trucks;
    }

    public void addTransitionsToAnimation(Transition transition){
        animations.add(transition);
    }

    public ArrayList<Transition> getAnimations() {
        return animations;
    }
}


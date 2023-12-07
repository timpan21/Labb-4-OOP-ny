import sun.misc.Signal;

import java.awt.*;
import java.util.ArrayList;

public abstract class Vehicles implements Movable{

    private int length;
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private Point position;
    private int direction;

    public static ArrayList<Vehicles> cars = new ArrayList<>();







    public Vehicles(int length, int nrDoors, String modelName, double enginePower, Color color) {
        this.length = length;
        this.nrDoors = nrDoors;
        this.modelName = modelName;
        this.enginePower = enginePower;
        this.color = color;
        this.position = new Point(0, 0);
        this.direction = 0;
        stopEngine();
        cars.add(this);
    }

    public abstract double speedFactor();

    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }
    public void move() {

        double newX = position.getX() + Math.cos(Math.toRadians(direction)) * getCurrentSpeed();
        double newY = position.getY() + Math.sin(Math.toRadians(direction)) * getCurrentSpeed();
        position.setLocation(newX, newY);





    }

    public int getLength(){return length;}

    public int getNrDoors(){
        return nrDoors;
    }

    public String getModelName() {
        return modelName;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public Color getColor() {
        return color;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Point getPosition() {
        return new Point(this.position);
    }

    public double getDirection() {
        return direction;
    }

    public void startEngine() {
        if (getCurrentSpeed() > 0) {
            System.out.println("Engine already ON");
        }
        else {
            currentSpeed = 0.1;}
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public void turnLeft() {
        direction = (direction - 90) % 360;
    }

    public void turnRight() {
        direction = (direction + 90) % 360;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    public void gas(double amount) {
        if (amount >= 0 && amount <= 1 && currentSpeed != 0) {
            incrementSpeed(amount);
            if (currentSpeed > enginePower) {
                currentSpeed = enginePower;
            } else if (currentSpeed < 0) {
                currentSpeed = 0;
            }
        }
    }
    public ArrayList<Vehicles> getCars() {
        return cars;
    }
    public void brake(double amount){
        if (amount >= 0 && amount <= 1 && currentSpeed - speedFactor()*amount >= 0) {
            decrementSpeed(amount);
        }
    }


}


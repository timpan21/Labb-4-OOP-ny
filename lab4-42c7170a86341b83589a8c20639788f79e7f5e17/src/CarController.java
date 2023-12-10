import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CarController {

    private final int vehicleLimit = 10;

    CarView frame;


    EventSource source;

    public ActionListener createTimerListener(){
        return new TimerListener();
    }


    public CarController() {
        this.source = new EventSource();
        this.frame = new CarView("CarSim 1.0", this);
        this.source.addObserver(frame);
    }

    public ArrayList<Vehicles> getVehicles() {

        return Vehicles.cars;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Vehicles.cars.isEmpty()) {
                frame.drawPanel.repaint();
            }
            for (Vehicles car : Vehicles.cars) {
                car.move();
                source.notifyObsevers(car.getPosition(), car);


            }


        }

    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicles car : Vehicles.cars
                ) {
            car.gas(gas);
        }
    }
    void startEngine() {

        for (Vehicles car : Vehicles.cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Vehicles car : Vehicles.cars) {
            car.stopEngine();
        }
    }
    void TurboOn() {
        for(Vehicles car : Vehicles.cars) {
            if (car instanceof Saab95) {
                 ((Saab95) car).setTurboOn();
            }
        }
    }
    void TurboOff() {
        for(Vehicles car : Vehicles.cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void changeBed(int amount) {
        for(Vehicles car : Vehicles.cars) {
            if (car instanceof Scania) {
            ((Scania) car).changeBedAngle(amount);}
        }
    }
    void brake(double amount) {

        for (Vehicles car : Vehicles.cars) {
            car.brake(amount/100);
        }
    }
    void addCar(){

       Random random = new Random();
       int pickedCar = random.nextInt(3);
        if (Vehicles.cars.size() < vehicleLimit) {
        switch (pickedCar) {
            case 0:
                new Scania(); break;
            case 1:
                new Saab95(); break;
            case 2:
                new Volvo240(); break;
        }}

    }
    void removeCar() {
        Random random1 = new Random();



        if (Vehicles.cars.size() <= 1) {
            Vehicles.cars.clear();
        }
        else {
            int pickCarToRemove = random1.nextInt(Vehicles.cars.size());
        Vehicles.cars.remove(pickCarToRemove);}
    }


    public ActionListener createGasListener(){
        return e -> gas(frame.gasAmount);
    }

    public ActionListener createStartListener(){
        return e -> startEngine();
    }

    public ActionListener createStopListener(){
        return e -> stopEngine();
    }

    public ActionListener createTurboOffListener(){
        return e -> TurboOff();
    }

    public ActionListener createTurboOnListener(){
        return e -> TurboOn();
    }

    public ActionListener createLiftBedListener(){
        return e -> changeBed(70);
    }

    public ActionListener createLowerBedListener(){
        return e -> changeBed(0);
    }

    public ActionListener createBrakeListener(){
        return e -> brake(frame.gasAmount);
    }

    public ActionListener createAddCarButton(){return e -> addCar();}

    public ActionListener createRemoveCarButton(){return e -> removeCar();}





}

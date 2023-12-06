import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed


    public ArrayList<Vehicles> vehicles = new ArrayList<>();
    EventSource source;


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Saab95());
        cc.vehicles.add(new Scania());
        cc.source = new EventSource();
        cc.source.addObserver(new CarView("hej",cc));

        // Start a new view and send a reference of self




        // Start the timer
        cc.timer.start();
        ;



    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Vehicles car : vehicles) {
                car.move();
                source.notifyObsevers(car.getPosition(),car);
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());


                ifAboutToHitWallStopEngineAndReverseDirection(car, x, y);




            }


        }

        private static void ifAboutToHitWallStopEngineAndReverseDirection(Vehicles car, int x, int y) {
            if (ifAboutToHitWall(car, x)) {
                if (x >= 684) {
                    car.setPosition(new Point(684, y));
                    x = 682;

                }

                else {
                    car.setPosition(new Point(0, y));
                    x = 0;
                }
                car.stopEngine();
                car.turnAround();
                car.startEngine();

            }
        }

        private static boolean ifAboutToHitWall(Vehicles car, int x) {
            return (x <= 0 && (car.getDirection() == 180 || car.getDirection() == -180)) || (x >= 684 && (int) car.getDirection() == 0);
        }


    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicles car : vehicles
                ) {
            car.gas(gas);
        }
    }
    void startEngine() {

        for (Vehicles car : vehicles) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Vehicles car : vehicles) {
            car.stopEngine();
        }
    }
    void TurboOn() {
        for(Vehicles car : vehicles) {
            if (car instanceof Saab95) {
                 ((Saab95) car).setTurboOn();
            }
        }
    }
    void TurboOff() {
        for(Vehicles car : vehicles) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void changeBed(int amount) {
        for(Vehicles car : vehicles) {
            if (car instanceof Scania) {
            ((Scania) car).changeBedAngle(amount);}
        }
    }
    void brake(double amount) {

        for (Vehicles car : vehicles) {
            car.brake(amount/100);
        }
    }


}


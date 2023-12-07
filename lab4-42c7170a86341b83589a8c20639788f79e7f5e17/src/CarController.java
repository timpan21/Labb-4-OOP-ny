import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarController {

    private final int delay = 50;

    private Timer timer = new Timer(delay, new TimerListener());

    private final int vehicleLimit = 10;

    CarView frame;


    EventSource source;


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

            for (Vehicles car : Vehicles.cars) {
                car.move();
                source.notifyObsevers(car.getPosition(),car);
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                //if (x <= frame.getSize().width)

                if (ifAboutToHitWall(car, x)) {
                    if (x >= 684) {
                        car.setPosition(new Point(684,y));
                        x = 684;

                    }

                    else {car.setPosition(new Point(0,y));
                        x = 0;
                    }
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();

                }

            }

        }

        private static boolean ifAboutToHitWall(Vehicles car, int x) {
            return (x <= 0 && (car.getDirection() == 180 || car.getDirection() == -180)) || (x >= 684 && (int) car.getDirection() == 0);
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
        if (vehicles.size() < vehicleLimit) {
            Vehicles createCar = Vehicles.createNewCar(car);
            vehicles.add(newCar);
        }
    }

    void removeCar(){
        if (!vehicles.isEmpty()){
            vehicles.remove(vehicles.size() - -1);
        }
    }

    public ActionListener createGasListener(){
        return e -> gas (frame.gasAmount);
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

    public static void main(String[] args) {
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Saab95());
        cc.vehicles.add(new Scania());

        // Start the timer
        cc.timer.start();

    }

}

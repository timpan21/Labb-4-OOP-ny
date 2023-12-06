import java.awt.*;
import java.util.LinkedList;

public class CarTransporter <T extends isCar> extends Vehicles{



    private boolean rampState = true;
    private final int capacity;

    public double speedFactor(){
        return getEnginePower() * 0.1;
    }

    private LinkedList<Vehicles> loadedCars = new LinkedList<>();



    public CarTransporter(int capacity) {
        super(10, 2, "Daf", 225, Color.gray);
        this.capacity = capacity;


    }



    public void updateLoadedCarsPosition() {

        for (Vehicles loadedCar : loadedCars) {
            loadedCar.setPosition((Point) getPosition().clone());

        }
    }


    @Override
    public void move() {
        super.move();
        updateLoadedCarsPosition();
    }





    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            rampState = false;
        }
    }


    public void raiseRamp() {
        rampState = true;
    }
    public void loadCar(Vehicles bil) {

        if (!rampState && loadedCars.size() < capacity && bil.getLength() <= 2) {

            double distance = calculateDistance(bil.getPosition(), getPosition());
            if (distance < 2) {
                loadedCars.add(bil);
                System.out.println("Bil lastad på transportör.");
            }
        }
    }

    private double calculateDistance(Point pos1, Point pos2) {
        return Math.sqrt(Math.pow(pos2.getX() - pos1.getX(), 2) + Math.pow(pos2.getY() - pos1.getY(), 2));
    }


    public void unloadCar() {

       if (!getIfRampRaised() && getLoadedCarsCount() > 0) {


       loadedCars.get(loadedCars.size()-1).setPosition(getNewCarPosition());
       loadedCars.remove(loadedCars.size()-1);
       System.out.println("Bil lastades av från biltransporten.");

       } else {
       System.out.println("Kunde inte lasta på bilen. Kolla status på både ramp och Biltransportens hastighet.");
       }

        }
    private Point getNewCarPosition() {
        Point transporterPosition = getPosition();
        double angle = Math.toRadians(getDirection() + 180);
        double distance = 1;
        double newX = transporterPosition.getX() + distance * Math.cos(angle);
        double newY = transporterPosition.getY() + distance * Math.sin(angle);
        return new Point((int) newX, (int) newY);
    }

    public int getLoadedCarsCount() {
        return loadedCars.size();
    }




    public LinkedList<Vehicles> getLoadedCars() {
        return loadedCars;
    }

    @Override
    public void gas(double amount) {
        if (rampState)
            super.gas(amount);
    }

    public boolean getIfRampRaised() {
        return rampState;
    }



}





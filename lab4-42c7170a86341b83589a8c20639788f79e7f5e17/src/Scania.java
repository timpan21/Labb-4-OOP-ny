import java.awt.*;

public class Scania extends Vehicles{

    private double bedAngle = 0;
    public Scania() {
        super(12,2,"Scania",150, Color.white);
    }

    public void changeBedAngle(double amount) {
        if (amount >= 0 && amount <= 70 && getCurrentSpeed() == 0) {
            bedAngle = amount;
        }
        else {
            System.out.println("invalid input");
        }
    }

    @Override
    public void gas(double amount) {
        if (getCurrentBedAngle() == 0) {
            super.gas(amount);
        }

    }

    public double getCurrentBedAngle() {
        return bedAngle;
    }

    public double speedFactor(){
        return getEnginePower() * 0.1;
    }



}

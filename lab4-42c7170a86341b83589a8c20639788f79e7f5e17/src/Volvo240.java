import java.awt.*;

public class Volvo240 extends Vehicles implements isCar{
    public static final double TRIM_FACTOR = 1.25;

    public Volvo240() {
        super(2, 4,"Volvo240", 100, Color.black);
    }

    public double speedFactor() {
        return getEnginePower() * 0.1 * TRIM_FACTOR;
    }



}

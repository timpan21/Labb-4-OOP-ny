import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTransporterTest {

    private CarTransporter transporter;
    private Saab95 s;
    private Volvo240 v;
    private Volvo240 StulenVolvo;

    @BeforeEach
    void newVehicles(){
        transporter = new CarTransporter(2);
        s = new Saab95();
        v = new Volvo240();
        StulenVolvo = new Volvo240();

    }
    @Test
    void rampShouldBeLoweredWhenStationary() {
        transporter.lowerRamp();
        assertFalse(transporter.getIfRampRaised());
        transporter.gas(1);
        transporter.lowerRamp();
        assertFalse(transporter.getIfRampRaised());
    }
    @Test
    void rampShouldNotLowerWhenMoving(){
        transporter.startEngine();

        transporter.gas(1);
        transporter.move();
        transporter.lowerRamp();
       assertTrue(transporter.getIfRampRaised());

    }

    @Test
    void raiseRamp() {
        transporter.lowerRamp();
        transporter.loadCar(s);
        transporter.raiseRamp();

        assertTrue(transporter.getIfRampRaised());

    }
    @Test
    void countingWorks() {
        transporter.lowerRamp();

        transporter.loadCar(s);
        assert(transporter.getLoadedCars().size() == 1);
        transporter.unloadCar();
        assert (transporter.getLoadedCars().isEmpty());
    }
    @Test
    void loadCarRangeLimitAndOnlyWhenRampIsLowered() {
        transporter.raiseRamp();
        transporter.loadCar(s);

        transporter.lowerRamp();
        Point motel = new Point(20,20);
        s.setPosition(motel);
        transporter.loadCar(s);
        assertEquals(0,transporter.getLoadedCarsCount() );

    }

    @Test
    void kapacitetLimitWorks() {
        transporter.lowerRamp();

        Point Origo = new Point(0,0);
        transporter.setPosition(Origo);
        s.setPosition(Origo);
        v.setPosition(Origo);
        StulenVolvo.setPosition(Origo);
        transporter.loadCar(s);
        transporter.loadCar(v);
        transporter.loadCar(StulenVolvo);
        assertEquals(2, transporter.getLoadedCarsCount());


    }

    @Test
    void unloadCar() {
        transporter.lowerRamp();
        transporter.loadCar(s);
        transporter.getLoadedCarsCount();
        assertEquals(1, transporter.getLoadedCarsCount());
        transporter.unloadCar();
        assertEquals(0, transporter.getLoadedCarsCount());
        assertNotEquals(transporter.getPosition(), s.getPosition());
    }


    @Test
    void getAntalLastadeBilar() {
        transporter.lowerRamp();
        Point Origo = new Point(0,0);
        transporter.setPosition(Origo);
        s.setPosition(Origo);
        v.setPosition(Origo);
        transporter.loadCar(s);
        assertEquals(1,transporter.getLoadedCarsCount());
        transporter.loadCar(v);
        assertEquals(2,transporter.getLoadedCarsCount());
    }
    @Test
    void updateLoadedCarsPosition() {
        Point Origo = new Point(0,0);



        transporter.lowerRamp();
        transporter.loadCar(s);
        transporter.loadCar(v);
        transporter.raiseRamp();
        transporter.startEngine();
        transporter.gas(1);
        transporter.move();  // korrekt rörelse inputs
        transporter.turnLeft();
        transporter.move();
        Point nyposTransporter = new Point(transporter.getPosition());
        Point nyposSaab = new Point(s.getPosition());
        assertEquals(nyposTransporter, nyposSaab);
        System.out.println(Origo);
        assertNotEquals(Origo, nyposSaab);

    }

}
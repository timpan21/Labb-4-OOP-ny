public class Application {

    public static void main(String[] args) {
        CarController cc = new CarController();
        VehicleFactory saabfactory = new Saab95Factory();
        VehicleFactory volvo240factory = new Volvo240Factory();
        VehicleFactory scaniafactory = new ScaniaFactory();
        scaniafactory.createVehicles();
        volvo240factory.createVehicles();
        saabfactory.createVehicles();



        // Start the timer
        cc.timer.start();

    }
}

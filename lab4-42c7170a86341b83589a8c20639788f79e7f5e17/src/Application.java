public class Application {

    public static void main(String[] args) {
        EventSource source = new EventSource();
        CarView carview = new CarView("CarSim 1.0");
        CarController cc = new CarController(source, carview);
        Listener listener = new Listener(source);

        VehicleFactory saabfactory = new Saab95Factory();
        VehicleFactory volvo240factory = new Volvo240Factory();
        VehicleFactory scaniafactory = new ScaniaFactory();
        scaniafactory.createVehicles();
        volvo240factory.createVehicles();
        saabfactory.createVehicles();




        // Start the timer
        listener.timer.start();

    }
}

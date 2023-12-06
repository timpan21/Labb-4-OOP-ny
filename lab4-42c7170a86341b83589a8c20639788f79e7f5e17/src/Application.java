public class Application {

    public static void main(String[] args) {
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Saab95());
        cc.vehicles.add(new Scania());

        // Start the timer
        cc.timer.start();

    }
}

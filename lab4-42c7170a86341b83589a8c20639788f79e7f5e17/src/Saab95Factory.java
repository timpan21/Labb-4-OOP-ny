public class Saab95Factory extends VehicleFactory{
    @Override
    protected CreateVehicles createVehicles() {
        return new Saab95();
    }
}

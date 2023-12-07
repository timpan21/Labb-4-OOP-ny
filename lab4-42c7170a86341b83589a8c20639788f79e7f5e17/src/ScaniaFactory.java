public class ScaniaFactory extends VehicleFactory{
    @Override
    protected CreateVehicles createVehicles() {
        return new Scania();
    }
}

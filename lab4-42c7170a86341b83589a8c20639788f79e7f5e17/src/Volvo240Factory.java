public class Volvo240Factory extends VehicleFactory{
    @Override
    protected CreateVehicles createVehicles() {
        return new Volvo240();
    }
}

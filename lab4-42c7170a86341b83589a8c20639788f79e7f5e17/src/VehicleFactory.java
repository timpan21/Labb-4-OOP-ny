public abstract class VehicleFactory {
    public CreateVehicles create() {
        CreateVehicles vehicle = createVehicles();
        vehicle.build();
        return vehicle;
    }
    protected abstract CreateVehicles createVehicles();



}

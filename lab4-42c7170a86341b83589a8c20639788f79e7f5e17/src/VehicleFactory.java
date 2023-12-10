public abstract class VehicleFactory {


    public  CreateVehicles create(Vehicles type) {

        CreateVehicles vehicle = createVehicles();
        vehicle.build();
        return vehicle;
    }
    protected abstract CreateVehicles createVehicles();



}

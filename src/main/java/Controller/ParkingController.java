package Controller;

import Model.Car;
import Model.ParkingBoy;
import Model.Receipt;

public class ParkingController {
    private ParkingBoy parkingBoy;

    public ParkingController(ParkingBoy parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

    public Receipt park(String plateNumber) {
        Car car = new Car(plateNumber);
        return parkingBoy.park(car);
    }

    public Car unPark(String uuid) {
        return parkingBoy.unPark(uuid);
    }

}

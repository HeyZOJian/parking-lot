package Model;

import Expection.ParkingLotFullException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoy {
	private List<ParkingLot> parkingLots = new LinkedList<>();

	public ParkingBoy(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
	}

	public Receipt park(Car car) {
		ParkingLot parkingLot = getParkingLotHaveRemainSpace();
		return parkingLot.park(car);
	}

	public Car unPark(Receipt receipt) {
		Car car = null;
		for (ParkingLot parkingLot : parkingLots) {
			car = parkingLot.unPark(receipt);
			if (car != null)
				break;
		}
		if (car == null) {
			throw new WrongReceiptException();
		}
		return car;
	}


	public ParkingLot getParkingLotHaveRemainSpace() {
		for (ParkingLot parkingLot : parkingLots) {
			if (!parkingLot.isFull())
				return parkingLot;
		}
		throw new ParkingLotFullException();
	}
}

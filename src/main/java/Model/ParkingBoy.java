package Model;

import Expection.AllParkingLotFullException;
import Expection.ParkingLotFullException;
import Expection.WrongReceiptException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

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

	public Car unPark(String uuid) {
		for (ParkingLot parkingLot : parkingLots) {
			Car car = parkingLot.unPark(uuid);
			if (car != null)
				return car;
		}
		throw new WrongReceiptException();
	}


	public ParkingLot getParkingLotHaveRemainSpace() {
		for (ParkingLot parkingLot : parkingLots) {
			if (!parkingLot.isFull())
				return parkingLot;
		}
		throw new AllParkingLotFullException();
	}

	public boolean isAllParkingLotFull() {
		for (ParkingLot parkingLot : parkingLots){
			if(!parkingLot.isFull())
				return false;
		}
		return true;
	}
}

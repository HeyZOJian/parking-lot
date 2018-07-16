package Model;

import Exception.AllParkingLotFullException;
import Exception.ParkingLotNoEmptyException;
import Exception.WrongReceiptException;
import Exception.ParkingLotNoExistException;

import java.util.List;
import java.util.Map;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoy {
	private int idRecoder = 1;
	private Map<Integer, ParkingLot> parkingLots;

	public ParkingBoy(Map<Integer, ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
	}

	public Receipt park(Car car) {
		ParkingLot parkingLot = getParkingLotHaveRemainSpace();
		return parkingLot.park(car);
	}

	public Car unPark(String uuid) {
		for (ParkingLot parkingLot : parkingLots.values()) {
			Car car = parkingLot.unPark(uuid);
			if (car != null)
				return car;
		}
		throw new WrongReceiptException();
	}


	public ParkingLot getParkingLotHaveRemainSpace() {
		for (ParkingLot parkingLot : parkingLots.values()) {
			if (!parkingLot.isFull())
				return parkingLot;
		}
		throw new AllParkingLotFullException();
	}

	public boolean isAllParkingLotFull() {
		for (ParkingLot parkingLot : parkingLots.values()) {
			if (!parkingLot.isFull())
				return false;
		}
		return true;
	}

	public void addParkingLot(String name, int size) {
		parkingLots.put(idRecoder, new ParkingLot(name, size));
		idRecoder++;
	}

	public ParkingLot deleteParkingLotById(String Id) {
		ParkingLot parkingLot = parkingLots.remove(Integer.parseInt(Id));
		if (parkingLot != null) {
			if (parkingLot.parkCarCount() == 0) {
				return parkingLot;
			} else {
				throw new ParkingLotNoEmptyException();
			}
		} else {
			throw new ParkingLotNoExistException();
		}
	}
}

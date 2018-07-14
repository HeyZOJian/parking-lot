package Model;

import Expection.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Vito Zhuang on 7/11/2018.
 */
public class ParkingLot {
	private int capacity;
	private Map<String, Car> parkSpaces = new HashMap<>();

	public ParkingLot(int capacity) {
		this.capacity = capacity;
	}

	public Receipt park(Car car) {
		if(isFull()) throw new ParkingLotFullException();
		else{
			Receipt receipt = new Receipt(UUID.randomUUID());
			parkSpaces.put(receipt.getUuid().toString(),car);
			return receipt;
		}
	}

	public boolean isFull(){
		return this.parkSpaces.size() == this.capacity;
	}

	public Car unPark(String uuid) {
		return this.parkSpaces.remove(uuid);
	}
}

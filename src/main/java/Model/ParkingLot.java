package Model;

import Exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Vito Zhuang on 7/11/2018.
 */
public class ParkingLot {
    private int id;
	private String name;
	private int capacity;
	private Map<String, Car> parkSpaces = new HashMap<>();

	public ParkingLot(int id, String name, int capacity) {
		this.id = id;
	    this.name = name;
		this.capacity = capacity;
	}

    public ParkingLot(int id) {
	    this.id  = id;
    }

    public Receipt park(Car car) {
		if(isFull()) throw new ParkingLotFullException();
		else{
			Receipt receipt = new Receipt(UUID.randomUUID());
			parkSpaces.put(receipt.getUuid(),car);
			return receipt;
		}
	}

	public boolean isFull(){
		return this.parkSpaces.size() == this.capacity;
	}

	public Car unPark(String uuid) {
		return this.parkSpaces.remove(uuid);
	}

	public int getParkNum(){
		return parkSpaces.entrySet().size();
	}

	public int getRemainNum(){
	    return capacity - getParkNum();
    }

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    public int getId() {
	    return this.id;
    }
}

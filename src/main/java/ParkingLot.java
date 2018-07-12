import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vito Zhuang on 7/11/2018.
 */
public class ParkingLot {
	private String name;
	private int capacity;
	private Map<Receipt,Car> parkSpaces = new HashMap<>();

	public ParkingLot(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	public Receipt park(Car car) {
		if(isFull()) throw new ParkingLotFullException();
		else{
			Receipt receipt = new Receipt(this.name);
			parkSpaces.put(receipt,car);
			return receipt;
		}
	}

	public boolean isFull(){
		return this.parkSpaces.size() == this.capacity;
	}

	public Car unPark(Receipt receipt) {
		return this.parkSpaces.remove(receipt);
	}

	public String getName() {
		return name;
	}
}

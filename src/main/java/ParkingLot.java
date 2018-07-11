import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vito Zhuang on 7/11/2018.
 */
public class ParkingLot {
	private int capacity;
	private Map<Receipt,Car> parkSpaces = new HashMap<>();

	public ParkingLot(int capacity) {
		this.capacity = capacity;
	}

	public Receipt park(Car car) {
		if(isFull()) throw new ParkingLotFullException();
		else{
			Receipt receipt = new Receipt();
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
}
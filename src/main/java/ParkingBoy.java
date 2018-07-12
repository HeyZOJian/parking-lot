import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoy {
	private List<ParkingLot> parkingLots = new ArrayList<>();

	public ParkingBoy(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
	}

	public Receipt park(Car car) {
		Receipt receipt = null;
		for (ParkingLot parkingLot : parkingLots) {
			if (!parkingLot.isFull()) {
				receipt = parkingLot.park(car);
				break;
			}
		}
		if (receipt == null) {
			throw new ParkingLotFullException();
		} else {
			return receipt;
		}
	}

	public Car unpark(Receipt receipt) {
		return null;
	}
}

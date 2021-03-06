import Model.Car;
import Model.ParkingLot;
import Exception.ParkingLotFullException;
import Model.Receipt;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ParkingLotTest {

	@Test
	public void should_park_successfully_given_parking_lot_is_not_full() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 1);

		try {
			parkingLot.park(new Car("粤C99999"));
		} catch (ParkingLotFullException exception) {
			fail("should park successfully");
		}

	}


	@Test
	public void should_park_failed_given_parking_lot_is_full() {
		ParkingLot parkingLot = new ParkingLot(1, "西南停车场", 0);

		try {
			parkingLot.park(new Car("粤C99999"));
			fail("should park successfully");
		} catch (ParkingLotFullException exception) {

		}
	}


	@Test
	public void should_get_specific_car_when_call_unPark_given_receipt_is_right() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 1);

		Car theCar = new Car("粤C99999");
		Receipt receipt = parkingLot.park(theCar);

		assertThat(parkingLot.unPark(receipt.getUuid()), is(theCar));

	}

	@Test
	public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 1);

		Car theCar = new Car("粤C99999");
		Receipt receipt = parkingLot.park(theCar);

		Receipt anotherReceipt = new Receipt(UUID.randomUUID());

		assertThat(parkingLot.unPark(anotherReceipt.getUuid()), not(theCar));
	}


	@Test
	public void should_be_true_when_call_isFull_given_parking_lot_is_full() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 0);

		assertThat(parkingLot.isFull(), is(true));
	}

	@Test
	public void should_be_false_when_call_isFull_given_parking_lot_is_not_full() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 1);

		assertThat(parkingLot.isFull(), is(false));
	}

	@Test
	public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 1);

		Car theCar = new Car("粤C99999");
		Receipt receipt = parkingLot.park(theCar);
		parkingLot.unPark(receipt.getUuid());

		assertThat(parkingLot.isFull(), is(false));
	}

	@Test
	public void should_park_successfully_when_call_park_again_given_a_full_parking_lot_take_out_a_car() {
		ParkingLot parkingLot = new ParkingLot(1,"西南停车场", 1);

		Car theCar = new Car("粤C99999");
		Receipt receipt = parkingLot.park(theCar);
		parkingLot.unPark(receipt.getUuid());

		try {
			parkingLot.park(new Car("粤C99999"));
		} catch (ParkingLotFullException exception) {
			fail("should park successfully");
		}
	}



}

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ParkingLotTest {

	@Test
	public void should_park_successfully_given_parking_lot_is_not_full() {
		ParkingLot parkingLot = new ParkingLot("No.1", 1);

		try {
			parkingLot.park(new Car());
		} catch (ParkingLotFullException exception) {
			fail("should park successfully");
		}

	}


	@Test
	public void should_park_failed_given_parking_lot_is_full() {
		ParkingLot parkingLot = new ParkingLot("No.1", 0);

		try {
			parkingLot.park(new Car());
			fail("should park successfully");
		} catch (ParkingLotFullException exception) {

		}
	}


	@Test
	public void should_get_specific_car_when_call_unPark_given_receipt_is_right() {
		ParkingLot parkingLot = new ParkingLot("No.1", 1);

		Car theCar = new Car();
		Receipt receipt = parkingLot.park(theCar);

		assertThat(parkingLot.unPark(receipt), is(theCar));

	}

	@Test
	public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong() {
		ParkingLot parkingLot = new ParkingLot("No.1", 1);

		Car theCar = new Car();
		Receipt receipt = parkingLot.park(theCar);

		Receipt anotherReceipt = new Receipt("No.1");

		assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
	}


	@Test
	public void should_be_true_when_call_isFull_given_parking_lot_is_full() {
		ParkingLot parkingLot = new ParkingLot("No.1", 0);

		assertThat(parkingLot.isFull(), is(true));
	}

	@Test
	public void should_be_false_when_call_isFull_given_parking_lot_is_not_full() {
		ParkingLot parkingLot = new ParkingLot("No.1", 1);

		assertThat(parkingLot.isFull(), is(false));
	}

	@Test
	public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car() {
		ParkingLot parkingLot = new ParkingLot("No.1", 1);

		Car theCar = new Car();
		Receipt receipt = parkingLot.park(theCar);
		parkingLot.unPark(receipt);

		assertThat(parkingLot.isFull(), is(false));
	}

	@Test
	public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car() {
		ParkingLot parkingLot = new ParkingLot("No.1", 1);

		Car theCar = new Car();
		Receipt receipt = parkingLot.park(theCar);
		parkingLot.unPark(receipt);

		try {
			parkingLot.park(new Car());
		} catch (ParkingLotFullException exception) {
			fail("should park successfully");
		}
	}

	@Test
	public void should_park_in_park1_successfully_when_park1_can_park_given_two_park() {
		ParkingLot parkingLot1 = new ParkingLot("No.1", 1);
		ParkingLot parkingLot2 = new ParkingLot("No.2", 1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
			assertThat(receipt.getParkingLotName(), is("No.1"));
		} catch (ParkingLotFullException e) {

		}
	}

	@Test
	public void should_park_in_park2_successfully_when_park1_is_full_and_park2_is_not_full_park_given_two_park() {
		ParkingLot parkingLot1 = new ParkingLot("No.1", 0);
		ParkingLot parkingLot2 = new ParkingLot("No.2", 1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
			assertThat(receipt.getParkingLotName(), is("No.2"));
		} catch (ParkingLotFullException e) {

		}
	}

	@Test
	public void should_park_failed_when_park1_and_park2_are_full_park_given_two_park(){
		ParkingLot parkingLot1 = new ParkingLot("No.1",0);
		ParkingLot parkingLot2 = new ParkingLot("No.2",0);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
			fail("should_park_failed");
		}catch (ParkingLotFullException e){
		}
	}
}

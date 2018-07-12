import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoyTest {
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
	public void should_park_failed_when_park1_and_park2_are_full_park_given_two_park() {
		ParkingLot parkingLot1 = new ParkingLot("No.1", 0);
		ParkingLot parkingLot2 = new ParkingLot("No.2", 0);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
			fail("should_park_failed");
		} catch (ParkingLotFullException e) {
		}
	}

	@Test
	public void should_unpark_successfully_when_unpark_the_car() {
		ParkingLot parkingLot1 = new ParkingLot("No.1", 1);
		ParkingLot parkingLot2 = new ParkingLot("No.2", 1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
			Car unparkCar = parkingBoy.unpark(receipt);
			assertThat(unparkCar, is(car));
		} catch (WrongReceiptException e) {
		}
	}

	@Test
	public void should_unpark_failed_when_unpark_the_car_given_the_wrong_receipt() {
		ParkingLot parkingLot1 = new ParkingLot("No.1", 1);
		ParkingLot parkingLot2 = new ParkingLot("No.2", 1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		try {
			Receipt receipt = new Receipt("No.3");
			Car unparkCar = parkingBoy.unpark(receipt);
			fail("should_unpark_failed");
		} catch (WrongReceiptException e) {
		}
	}
}

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoyTest {

	@Test
	public void should_park_successfully_when_park_is_not_full_given_one_park() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		List<ParkingLot> parkingLots = new LinkedList<>();
		parkingLots.add(parkingLot1);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
		} catch (ParkingLotFullException e) {

		}
	}

	@Test
	public void should_unpark_successfully_when_parkingBoy_manage_one_park() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		List<ParkingLot> parkingLots = new LinkedList<>();
		parkingLots.add(parkingLot1);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		Receipt receipt = parkingBoy.park(car);
		Car unparkCar = parkingBoy.unpark(receipt);
		assertThat(unparkCar, is(car));
	}

	@Test
	public void should_park_successfully_when_park_are_not_full_at_least_one_given_two_park() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		try {
			Receipt receipt = parkingBoy.park(car);
		} catch (ParkingLotFullException e) {

		}
	}

	@Test
	public void should_unpark_successfully_when_parkingBoy_manage_two_park() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();

		Receipt receipt = parkingBoy.park(car);
		Car unparkCar = parkingBoy.unpark(receipt);
		assertThat(unparkCar, is(car));
	}

	@Test
	public void should_park_in_orderly_when_parkingBoy_manage_two_park() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car1 = new Car();
		Car car2 = new Car();

		try {
			Receipt receipt1 = parkingBoy.park(car1);
			Receipt receipt2 = parkingBoy.park(car2);
			assertThat(parkingLot2.unPark(receipt2), is(car2));
		} catch (ParkingLotFullException e) {

		}
	}

	@Test
	public void should_park_failed_when_park1_and_park2_are_full_park_given_two_park() {
		ParkingLot parkingLot1 = new ParkingLot(0);
		ParkingLot parkingLot2 = new ParkingLot(0);
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
	public void should_unpark_failed_when_unpark_the_car_given_the_wrong_receipt() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot1);
		parkingLots.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		Car car = new Car();
		try {
			Receipt receipt = parkingBoy.park(car);
			Car unparkCar1 = parkingBoy.unpark(receipt);
			Car unpakCar2 = parkingBoy.unpark(receipt);
			fail("should_unpark_failed");
		} catch (WrongReceiptException e) {
		}
	}
}


import Exception.*;
import Model.*;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Vito Zhuang on 7/12/2018.
 */
public class ParkingBoyTest {

    @Test
    public void should_park_successfully_when_park_is_not_full_given_one_park() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.addParkingLot("西南停车场", 1);
        Car car = new Car("粤C99999");

        try {
            Receipt receipt = parkingBoy.park(car);
        } catch (ParkingLotFullException e) {

        }
    }

    @Test
    public void should_unpark_successfully_when_parkingBoy_manage_one_park() {

        Car car = new Car("粤C99999");
        Receipt receipt = new Receipt(UUID.randomUUID());
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.park(car)).thenReturn(receipt);
        when(parkingLot.unPark(receipt.getUuid())).thenReturn(car);

        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        receipt = parkingBoy.park(car);
        parkingBoy.unPark(receipt.getUuid());
        verify(parkingLot).unPark(receipt.getUuid());

    }

    @Test
    public void should_park_successfully_when_park_are_not_full_given_two_park() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot2.isFull()).thenReturn(false);
        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot1);
	    parkingLots.put(2,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("粤C99999");

        try {
            Receipt receipt = parkingBoy.park(car);
        } catch (ParkingLotFullException e) {

        }
    }

    @Test
    public void should_unpark_successfully_when_parkingBoy_manage_two_park() {
        Car car = new Car("粤C99999");
        Receipt receipt = new Receipt(UUID.randomUUID());

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt);
        when(parkingLot1.unPark(receipt.getUuid())).thenReturn(car);

        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot1);
	    parkingLots.put(2,parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        receipt = parkingBoy.park(car);
        Car unParkCar = parkingBoy.unPark(receipt.getUuid());
        verify(parkingLot1).unPark(receipt.getUuid());

    }

    @Test
    public void should_park_in_orderly_when_parkingBoy_manage_two_park() {
        Car car1 = new Car("粤C99999");
        Car car2 = new Car("粤C99998");

        Receipt receipt1 = new Receipt(UUID.randomUUID());
        Receipt receipt2 = new Receipt(UUID.randomUUID());

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false, true);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot1.park(car1)).thenReturn(receipt1);
        when(parkingLot2.park(car2)).thenReturn(receipt2);

        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot1);
	    parkingLots.put(2,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        receipt1 = parkingBoy.park(car1);
        receipt2 = parkingBoy.park(car2);

        verify(parkingLot1).park(car1);
        verify(parkingLot2).park(car2);
    }

    @Test
    public void should_park_failed_when_park1_and_park2_are_full_park_given_two_park() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(true);
        when(parkingLot2.isFull()).thenReturn(true);
        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot1);
	    parkingLots.put(2,parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("粤C99999");

        try {
            Receipt receipt = parkingBoy.park(car);
            fail("should_park_failed");
        } catch (AllParkingLotFullException e) {
        }
    }

    @Test
    public void should_unpark_failed_when_unpark_the_car_given_the_wrong_receipt() {
        Receipt receipt1 = new Receipt(UUID.randomUUID());
        Receipt receipt2 = new Receipt(UUID.randomUUID());
        Car car = new Car("粤C99999");
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt1, receipt2);
        when(parkingLot1.unPark(receipt1.getUuid())).thenReturn(car,null);
        Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Receipt receipt = parkingBoy.park(car);
        Car unParkCar1 = parkingBoy.unPark(receipt.getUuid());
        try {
            Car unpakCar2 = parkingBoy.unPark(receipt.getUuid());
            fail("should_unpark_failed");
        } catch (WrongReceiptException e) {
        }
    }
    
    @Test
    public void should_return_success_when_add_new_parkingLot_given_new_parkingLot_name_and_size (){
        Map<Integer, ParkingLot> parkingLots = mock(TreeMap.class);
    	ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
    	parkingBoy.addParkingLot("西南停车场",1);
    	verify(parkingLots).put(anyInt(),any(ParkingLot.class));
    }
    
    @Test
    public void should_return_success_when_delete_a_parkinglot_given_a_existing_parkinglot_id (){
        ParkingLot parkingLot = new ParkingLot("西南停车场",1);
    	Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,parkingLot);
    	ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
    	ParkingLot delParkingLot = parkingBoy.deleteParkingLotById("001");
    	assertThat(delParkingLot,is(parkingLot));
    }

	@Test
	public void should_return_failed_when_delete_a_parkinglot_given_a_no_existing_parkinglot_id (){
		Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		try{
		ParkingLot delParkingLot = parkingBoy.deleteParkingLotById("001");
		fail("should throw ParkingLotNoExistException");
		}catch (ParkingLotNoExistException e){

		}
    }

    @Test
    public void should_return_failed_when_delete_a_parkinglot_given_a_still_parking_car_parkinglot_id (){
	    Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,new ParkingLot("西南停车场",1));
	    ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car("1234"));
        try {
	        parkingBoy.deleteParkingLotById("001");
	        fail("should throw ParkingLotNoEmptyException");
        }catch (ParkingLotNoEmptyException e){

        }
    }

    @Test
    public void should_return_all_manage_parkinglot_info (){
	    Map<Integer, ParkingLot> parkingLots = new TreeMap<>();
	    parkingLots.put(1,new ParkingLot("西南停车场",1));
	    ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
		List<Map<String,String>> allInfo = parkingBoy.getParkingLotsInfo();
		Map<String,String> parkingLotInfo = allInfo.get(0);
		assertThat(parkingLotInfo.get("Id"),is("001"));
		assertThat(parkingLotInfo.get("Name"),is("西南停车场"));
		assertThat(parkingLotInfo.get("Capacity"),is("1"));
		assertThat(parkingLotInfo.get("ParkNum"),is("0"));
		assertThat(parkingLotInfo.get("RemainNum"),is("1"));
    }
}

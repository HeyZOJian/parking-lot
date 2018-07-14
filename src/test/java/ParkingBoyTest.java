import Expection.ParkingLotFullException;
import Expection.WrongReceiptException;
import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
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

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

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

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car("粤C99999");

        try {
            Receipt receipt = parkingBoy.park(car);
            fail("should_park_failed");
        } catch (ParkingLotFullException e) {
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
        when(parkingLot1.unPark(receipt1.getUuid().toString())).thenReturn(car,null);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Receipt receipt = parkingBoy.park(car);
        Car unParkCar1 = parkingBoy.unPark(receipt.getUuid());
        try {
            Car unpakCar2 = parkingBoy.unPark(receipt.getUuid());
            fail("should_unpark_failed");
        } catch (WrongReceiptException e) {
        }
    }
}

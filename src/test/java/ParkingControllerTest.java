import Controller.ParkingController;
import Expection.WrongReceiptException;
import Model.Car;
import Model.ParkingBoy;
import Model.ParkingLot;
import Model.Receipt;
import View.Request;
import View.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

/**
 * Created by Vito Zhuang on 7/15/2018.
 */
public class ParkingControllerTest {
	private ParkingLot parkingLot;
	private ParkingBoy parkingBoy;
	private ParkingController parkingController;
	private Request request;
	private Response response;
	private Car car;
	private Receipt receipt;
	ByteArrayOutputStream output = new ByteArrayOutputStream();

	@Before
	public void init(){
		parkingLot = new ParkingLot(1);
		List<ParkingLot>parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot);
		parkingBoy = new ParkingBoy(parkingLots);
		parkingController = new ParkingController(parkingBoy);
		receipt = mock(Receipt.class);
		car = mock(Car.class);
		request = mock(Request.class);
		response = new Response();
	}

	@Test
	public void should_park_successfully_when_parklot_is_not_full (){
		when(request.getParameter()).thenReturn("粤C99999");
	    parkingController.park(request);
	}

	@Test
	public void should_unpark_successfully_when_receipt_is_right_given_receipt_number (){
		parkingBoy = mock(ParkingBoy.class);
		parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("1234-1234");
		when(parkingBoy.unPark("1234-1234")).thenReturn(car);
		when(car.getPlateNumber()).thenReturn("粤C99999");
		try {
			parkingController.unpark(request);
		}catch (WrongReceiptException e){
			fail("should unpark successfully");
		}
	}
}

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
	private Request request;
	private Response response;
	private Car car;
	private Receipt receipt;
	ByteArrayOutputStream output = new ByteArrayOutputStream();

	@Before
	public void init(){
		parkingBoy = mock(ParkingBoy.class);
		receipt = mock(Receipt.class);
		car = mock(Car.class);
		request = mock(Request.class);
		response = new Response();
	}

	@Test
	public void should_return_index_view_when_request_index_view (){
		ParkingController parkingController = new ParkingController(parkingBoy);
	    response = parkingController.indexView();
	    assertThat(response.getResult(),is("1. 停车\n" +
			    "2. 取车 \n" +
			    "请输入您要进行的操作："));
	}

	@Test
	public void should_return_hint_intput_plate_number_when_input_command_1 (){
		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("1");
		response = parkingController.inputCommand(request);
		assertThat(response.getResult(),is("请输入车牌号："));
	}

	@Test
	public void should_return_hint_intput_receipt_number_when_input_command_2 (){
		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("2");
		response = parkingController.inputCommand(request);
		assertThat(response.getResult(),is("请输入小票编号："));
	}

	@Test
	public void should_return_hint_error_command_number_when_input_command_no_1_or_2 (){
		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("3");
		response = parkingController.inputCommand(request);
		assertThat(response.getResult(),is("非法指令，请查证后再输"));
	}

	@Test
	public void should_return_receipt_number_when_parkinglot_is_not_full_given_plate_number (){
		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("1", "粤C99999");
		when(request.getObject()).thenReturn(car);
		when(parkingBoy.park(car)).thenReturn(receipt);
		response = parkingController.inputCommand(request);
		response = parkingController.operation(request);
		assertThat(response.getResult().contains("停车成功，您的小票是：\n"),is(true));
	}

	@Test
	public void should_return_parkinglot_is_full_when_parkinglot_is_full_given_plate_number (){
		parkingLot = new ParkingLot(0);
		List<ParkingLot> parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot);
		parkingBoy = new ParkingBoy(parkingLots);

		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("1", "粤C99999");
		response = parkingController.inputCommand(request);
		response = parkingController.operation(request);
		assertThat(response.getResult(),is("车已停满，请晚点再来"));
	}

	@Test
	public void should_return_car_platenumber_when_receipt_number_is_right_given_receipt_number (){
		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("1", "粤C99999","2","1234-1234");
		when(request.getObject()).thenReturn(car);
		when(parkingBoy.park(car)).thenReturn(receipt);
		when(receipt.getUuid()).thenReturn("1234-1234");
		when(parkingBoy.unPark("1234-1234")).thenReturn(car);
		when(car.getPlateNumber()).thenReturn("粤C99999");
		response = parkingController.inputCommand(request);
		response = parkingController.operation(request);
		response = parkingController.inputCommand(request);
		response = parkingController.operation(request);
		assertThat(response.getResult(),is("车已取出，您的车牌号是：粤C99999"));
	}

	@Test
	public void should_return_error_receipt_when_receipt_number_is_wrong_given_receipt_number (){
		parkingLot = new ParkingLot(1);
		List<ParkingLot>parkingLots = new ArrayList<>();
		parkingLots.add(parkingLot);
		parkingBoy = new ParkingBoy(parkingLots);
		ParkingController parkingController = new ParkingController(parkingBoy);
		when(request.getParameter()).thenReturn("1", "粤C99999","2","4321-4321");
		when(request.getObject()).thenReturn(car);
		when(receipt.getUuid()).thenReturn("1234-1234");
		response = parkingController.inputCommand(request);
		response = parkingController.operation(request);
		response = parkingController.inputCommand(request);
		response = parkingController.operation(request);
		assertThat(response.getResult(),is("非法小票，无法取出车，请查证后再输"));
	}

}

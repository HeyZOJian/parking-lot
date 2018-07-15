import Controller.ParkingController;
import Model.ParkingBoy;
import Model.ParkingLot;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vito Zhuang on 7/15/2018.
 */
public class ParkingControllerTest {
	private ParkingBoy parkingBoy;
	private Request request;
	private Response response;
	ByteArrayOutputStream output = new ByteArrayOutputStream();

	@Before
	public void init(){
		parkingBoy = mock(ParkingBoy.class);
		request = mock(Request.class);
		response = new Response();
	}

	@Test
	public void should_return_index_view_when_request_index_view (){
	    ParkingController parkingController = new ParkingController(parkingBoy,request,response);
	    parkingController.indexView();
	    verify(response).setResult("1. 停车\n" +
			    "2. 取车 \n" +
			    "请输入您要进行的操作：");
	}

	@Test
	public void should_return_hint_intput_plate_number_when_input_command_1 (){
		ParkingController parkingController = new ParkingController(parkingBoy,request,response);
		when(request.getParameter()).thenReturn("1");
		response = parkingController.inputCommand(request);
		assertThat(response.getResult(),is("请输入车牌号："));
	}

	@Test
	public void should_return_hint_intput_receipt_number_when_input_command_2 (){
		ParkingController parkingController = new ParkingController(parkingBoy,request,response);
		when(request.getParameter()).thenReturn("2");
		response = parkingController.inputCommand(request);
		assertThat(response.getResult(),is("请输入小票编号："));
	}

	@Test
	public void should_return_hint_error_command_number_when_input_command_no_1_or_2 (){
		ParkingController parkingController = new ParkingController(parkingBoy,request,response);
		when(request.getParameter()).thenReturn("3");
		response = parkingController.inputCommand(request);
		assertThat(response.getResult(),is("非法指令，请查证后再输"));
	}


}

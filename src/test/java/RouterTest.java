import Controller.ParkingController;
import Controller.Router;
import Model.ParkingBoy;
import Model.ParkingLot;
import View.Request;
import View.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Vito Zhuang on 7/16/2018.
 */
public class RouterTest {
	private ParkingLot parkingLot;
	private Map<Integer, ParkingLot> parkingLots;
	private ParkingBoy parkingBoy;
	private ParkingController parkingController;
	private Request request;
	private Response response;
	private Router router;
	@Before
	public void init(){
		parkingLot = new ParkingLot("西南停车场", 1);
		parkingLots = new HashMap<>();
		parkingLots.put(1,parkingLot);
		parkingBoy = new ParkingBoy(parkingLots);
		parkingController = mock(ParkingController.class);
		request = mock(Request.class);
		response = mock(Response.class);
		router = new Router(parkingController);
	}

	@Test
	public void should_return_main_page_when_router_get_main_page() {
		parkingBoy = mock(ParkingBoy.class);
		parkingController = new ParkingController(parkingBoy);
		router = new Router(parkingController);
		router.getIndexView(response);
		verify(response).send("1. 停车\n" +
				"2. 取车 \n" +
				"请输入您要进行的操作：");
	}
	@Test
	public void should_return_main_page_when_input_wrong_command() {
		parkingBoy = mock(ParkingBoy.class);
		parkingController = new ParkingController(parkingBoy);
		router = new Router(parkingController);
		when(request.getParameter()).thenReturn("3");
		router.handleRequest(request,response);

		assertThat(router.getCurrentPage(),is(Router.MAINPAGE));

	}

	@Test
	public void should_return_park_page_when_current_page_in_main_page_given_command_1 (){
	    parkingBoy = mock(ParkingBoy.class);
	    when(parkingBoy.isAllParkingLotFull()).thenReturn(false);
		when(request.getParameter()).thenReturn("1");
		parkingController = new ParkingController(parkingBoy);
	    router = new Router(parkingController);

	    router.handleRequest(request,response);

		assertThat(router.getCurrentPage(),is(Router.PARKPAGE));

	}

	@Test
	public void should_return_unpark_page_when_current_page_in_main_page_given_command_2 (){
		when(request.getParameter()).thenReturn("2");
		parkingController = new ParkingController(parkingBoy);
		router = new Router(parkingController);

		router.handleRequest(request,response);

		assertThat(router.getCurrentPage(),is(Router.UNPARKPAGE));
	}

	@Test
	public void should_return_main_page_when_park_successfully (){
		when(request.getParameter()).thenReturn("1", "粤C00000");
		parkingController = new ParkingController(parkingBoy);
		router = new Router(parkingController);

		router.handleRequest(request,response);
		router.handleRequest(request,response);

		assertThat(router.getCurrentPage(),is(Router.MAINPAGE));
	}
	@Test
	public void should_return_main_page_when_park_failed (){
		when(request.getParameter()).thenReturn("1", "粤C00000","1");
		parkingController = new ParkingController(parkingBoy);
		router = new Router(parkingController);

		router.handleRequest(request,response);
		router.handleRequest(request,response);
		router.handleRequest(request,response);
		verify(response).send("车已停满，请晚点再来");
	}

	@Test
	public void should_return_main_page_and_output_platenumber_when_unpark_successfully (){
		//TODO
	}

	@Test
	public void should_return_main_page_and_output_error_message_when_unpark_failed (){
		//TODO
	}
}
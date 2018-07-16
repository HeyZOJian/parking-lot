import Controller.ParkingController;
import Controller.Router;
import Model.ParkingBoy;
import Model.ParkingLot;
import View.Request;
import View.Response;

import java.util.List;

/**
 * Created by Vito Zhuang on 7/16/2018.
 */
public class RouterTest {
	private ParkingLot parkingLot;
	private List<ParkingLot> parkingLots;
	private ParkingBoy parkingBoy;
	private ParkingController parkingController;
	private Request request;
	private Response response;
	private Router router;
//	@Before
//	public void init(){
//		parkingLot = new ParkingLot(1,"西南停车场", 1);
//		parkingLots = new LinkedList<>();
//		parkingLots.add(parkingLot);
//		parkingBoy = new ParkingBoy(parkingLots);
//		parkingController = mock(ParkingController.class);
//		request = mock(Request.class);
//		response = mock(Response.class);
//		router = new Router(parkingController);
//	}
//
//	@Test
//	public void should_return_main_page_when_router_get_main_page() {
//		parkingBoy = mock(ParkingBoy.class);
//		parkingController = new ParkingController(parkingBoy,request,response);
//		router = new Router(parkingController);
//		router.launch();
//		verify(response).send("1. 停车\n" +
//				"2. 取车 \n" +
//				"请输入您要进行的操作：");
//	}
//	@Test
//	public void should_return_main_page_when_input_wrong_command() {
//		parkingBoy = mock(ParkingBoy.class);
//		parkingController = new ParkingController(parkingBoy,request,response);
//		router = new Router(parkingController);
//		when(request.getParameter()).thenReturn("3");
//		router.handleRequest(request,response);
//
//		assertThat(router.getCurrentPage(),is(Router.MAINPAGE));
//
//	}
//
//	@Test
//	public void should_return_park_page_when_current_page_in_main_page_given_command_1 (){
//	    parkingBoy = mock(ParkingBoy.class);
//	    when(parkingBoy.isAllParkingLotFull()).thenReturn(false);
//		when(request.getParameter()).thenReturn("1");
//		parkingController = new ParkingController(parkingBoy,request,response);
//	    router = new Router(parkingController);
//
//	    router.handleRequest(request,response);
//
//		assertThat(router.getCurrentPage(),is(Router.PARKPAGE));
//
//	}
//
//	@Test
//	public void should_return_unpark_page_when_current_page_in_main_page_given_command_2 (){
//		when(request.getParameter()).thenReturn("2");
//		parkingController = new ParkingController(parkingBoy,request,response);
//		router = new Router(parkingController);
//
//		router.handleRequest(request,response);
//
//		assertThat(router.getCurrentPage(),is(Router.UNPARKPAGE));
//	}
//
//	@Test
//	public void should_return_main_page_when_park_successfully (){
//		when(request.getParameter()).thenReturn("1", "粤C00000");
//		parkingController = new ParkingController(parkingBoy,request,response);
//		router = new Router(parkingController);
//
//		router.handleRequest(request,response);
//		router.handleRequest(request,response);
//
//		assertThat(router.getCurrentPage(),is(Router.MAINPAGE));
//	}
//	@Test
//	public void should_return_main_page_when_park_failed (){
//		when(request.getParameter()).thenReturn("1", "粤C00000","1");
//		parkingController = new ParkingController(parkingBoy,request,response);
//		router = new Router(parkingController);
//
//		router.handleRequest(request,response);
//		router.handleRequest(request,response);
//		router.handleRequest(request,response);
//		verify(response).send("车已停满，请晚点再来");
//	}
//
//	@Test
//	public void should_return_main_page_and_output_platenumber_when_unpark_successfully (){
//		//TODO
//	}
//
//	@Test
//	public void should_return_main_page_and_output_error_message_when_unpark_failed (){
//		//TODO
//	}
}
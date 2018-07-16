import Controller.ParkingController;
import Controller.Router;
import Model.ParkingBoy;
import Model.ParkingLot;
import View.CLI;
import View.Request;
import View.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    public static void main(String[] args) {
	    Map<Integer, ParkingLot> parkingLots = new HashMap<>();

	    ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
	    parkingBoy.addParkingLot("西南停车场", 1);

	    ParkingController parkingController = new ParkingController(parkingBoy);

		Request request = new Request();
	    Response response = new Response();

	    CLI cli = new CLI();
		Router router = new Router(parkingController);
		router.getIndexView(response);

		while (true){
			String command = cli.input();
			request.setParameter(command);
			router.handleRequest(request,response);
		}
    }
}

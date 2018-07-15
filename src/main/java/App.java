import Controller.ParkingController;
import Model.ParkingBoy;
import Model.ParkingLot;
import View.Request;
import View.Response;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    public static void main(String[] args) {
	    ParkingLot parkingLot = new ParkingLot(1);
	    List<ParkingLot> parkingLots = new LinkedList<>();
	    parkingLots.add(parkingLot);

	    ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

	    ParkingController parkingController = new ParkingController(parkingBoy, new Request(),new Response());

		Response response;
		Request request = new Request();

		Scanner scanner = new Scanner(System.in);

		while (true){
			response = parkingController.indexView();
			System.out.println(response.getResult());

			String command = scanner.next();
			request.setParameter(command);
			response = parkingController.inputCommand(request);
			System.out.println(response.getResult());

			String parameter = scanner.next();
			request.setParameter(parameter);
			response = parkingController.operation(request);
			System.out.println(response.getResult());
		}
    }
}

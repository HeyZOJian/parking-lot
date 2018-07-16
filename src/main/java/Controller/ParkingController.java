package Controller;

import Expection.ParkingLotFullException;
import Expection.WrongReceiptException;
import Model.Car;
import Model.ParkingBoy;
import Model.Receipt;
import View.Request;
import View.Response;

public class ParkingController {
	private ParkingBoy parkingBoy;
	private Response response = new Response();

	public ParkingController(ParkingBoy parkingBoy) {
		this.parkingBoy = parkingBoy;
	}


	public Response park(Request request) {
		Receipt receipt = parkingBoy.park(new Car(request.getParameter()));
		response.send("停车成功，您的小票是：\n" + receipt.getUuid());
		return response;
	}

	public Response unpark(Request request) {
		String receiptNumber = request.getParameter();
		try {
			Car car = parkingBoy.unPark(receiptNumber);
			response.send("车已取出，您的车牌号是：" + car.getPlateNumber());
		}catch (WrongReceiptException e){
			response.send("非法小票，无法取出车，请查证后再输");
		}
		return response;
	}

	public boolean isAllParkingLotFull() {
		return parkingBoy.isAllParkingLotFull();
	}
}

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
	private Request request;
	private Response response;
	private String command;

	public ParkingController(ParkingBoy parkingBoy,Request request,Response response) {
		this.parkingBoy = parkingBoy;
		this.request = request;
		this.response = response;
	}

	public Response indexView() {
		response.setResult("1. 停车\n" +
				"2. 取车 \n" +
				"请输入您要进行的操作：");
		return response;
	}

	public Response inputCommand(Request request) {
		command = request.getParameter();
		if (command.equals("1")) {
			response.setResult("请输入车牌号：");
		} else if (command.equals("2")) {
			response.setResult("请输入小票编号：");
		} else {
			response.setResult("非法指令，请查证后再输");
		}
		return response;
	}

	public Response operation(Request request){
		String parameter = request.getParameter();
		if(command.equals("1")){
			response = park(parameter);
		}
		else if(command.equals("2")){
			response = unpark(parameter);
		}
		return response;
	}

	public Response park(String plateNumber) {
		Car car = new Car(plateNumber);
		try {
			Receipt receipt = parkingBoy.park(car);
			response.setResult("停车成功，您的小票是：\n" + receipt.getUuid());
		} catch (ParkingLotFullException e) {
			response.setResult("车已停满，请晚点再来");
		}
		return response;
	}

	public Response unpark(String receiptNumber) {
		try {
			Car car = parkingBoy.unPark(receiptNumber);
			response.setResult("车已取出，您的车牌号是：" + car.getPlateNumber());
		}catch (WrongReceiptException e){
			response.setResult("非法小票，无法取出车，请查证后再输");
		}
		return response;
	}


}

package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;

public class ParkingController {
	private ParkingBoy parkingBoy;
	private Request request;
	private Response response;
//
//	public ParkingController(ParkingBoy parkingBoy, Request request, Response response) {
//		this.parkingBoy = parkingBoy;
//		this.request = request;
//		this.response = response;
//	}
//
//	public String mainHandle(Request request,Response response) {
//		String currentPage;
//		switch (request.getParameter()) {
//			case "1":
//				currentPage = parkPage(response);
//				break;
//			case "2":
//				currentPage = unparkPage(response);
//				break;
//			default:
//				response.send("非法指令，请查证后再输");
//				currentPage = Router.MAINPAGE;
//				mainView(response);
//		}
//		return currentPage;
//	}
//
//	public void mainView(Response response) {
//		response.send("1. 停车\n" +
//				"2. 取车 \n" +
//				"请输入您要进行的操作：");
//	}
//
//	private String parkPage(Response response) {
//		if (isAllParkingLotFull()) {
//			response.send("车已停满，请晚点再来");
//			mainView(response);
//			return Router.MAINPAGE;
//		} else {
//			response.send("请输入车牌号：");
//			return Router.PARKPAGE;
//		}
//	}
//
//	private String unparkPage(Response response) {
//		response.send("请输入小票编号：");
//		return Router.UNPARKPAGE;
//	}
//
//	public String park(Request request,Response response) {
//		Receipt receipt = parkingBoy.park(new Car(request.getParameter()));
//		response.send("停车成功，您的小票是：\n" + receipt.getUuid());
//		mainView(response);
//		return Router.MAINPAGE;
//	}
//
//	public String unpark(Request request, Response response) {
//		String receiptNumber = request.getParameter();
//		try {
//			Car car = parkingBoy.unPark(receiptNumber);
//			response.send("车已取出，您的车牌号是：" + car.getPlateNumber());
//		} catch (WrongReceiptException e) {
//			response.send("非法小票，无法取出车，请查证后再输");
//		}
//		mainView(response);
//		return Router.MAINPAGE;
//	}
//
//	public boolean isAllParkingLotFull() {
//		return parkingBoy.isAllParkingLotFull();
//	}
//
//	@Override
//	public String process() {
//		return null;
//	}
}

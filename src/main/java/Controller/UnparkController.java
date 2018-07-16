package Controller;

import Model.Car;
import Model.ParkingBoy;
import View.Request;
import View.Response;
import Exception.WrongReceiptException;

public class UnparkController implements BaseController {

    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public UnparkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        try {
            Car car = parkingBoy.unPark(request.getParameter());
            response.send("车已取出，您的车牌号是：" + car.getPlateNumber());
        }catch (WrongReceiptException e){
            response.send("非法小票，无法取出车，请查证后再输");
        }
        return "forward:main";
    }
}

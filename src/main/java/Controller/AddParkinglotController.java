package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;

public class AddParkinglotController implements BaseController {

    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public AddParkinglotController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        String name = request.getParameter().split(" ")[0];
        String size = request.getParameter().split(" ")[1];
        parkingBoy.addParkingLot(name,Integer.parseInt(size));
        response.send("停车场添加成功！");
        return "forward:main";
    }
}

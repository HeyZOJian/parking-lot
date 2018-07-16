package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;

public class GoToDeleteParkinglotController implements BaseController {

    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public GoToDeleteParkinglotController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        response.send("请输入需要删除的被管理停车场ID:");
        return "main/2/3";
    }
}

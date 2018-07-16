package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;

public class GoToUnparkController implements BaseController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public GoToUnparkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        response.send("请输入小票编号：");
        return "main/1/2";
    }
}

package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;

public class GoToParkPageController implements BaseController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    public GoToParkPageController(Request request, Response response,ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        if(parkingBoy.isAllParkingLotFull()){
            response.send("车已停满，请晚点再来");
            return "forward:main";
        }
        else{
            response.send("请输入车牌号：");
            return "main/1/1";
        }
    }
}

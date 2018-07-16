package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;
import Exception.ParkingLotNoEmptyException;
import Exception.ParkingLotNoExistException;

public class DeleteParkingController implements BaseController {

    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public DeleteParkingController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        try {
            parkingBoy.deleteParkingLotById(request.getParameter());
            response.send("停车场删除成功！");
        }catch (ParkingLotNoEmptyException e1){
            response.send("停车场添加失败，原因：此停车场中，依然停有汽车，无法删除！");
        }catch (ParkingLotNoExistException e2){
            response.send("停车场添加失败，原因：此停车场不存在！");
        }
        return "forward:main";
    }
}

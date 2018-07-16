package Controller;

import Model.ParkingBoy;
import View.Request;
import View.Response;

import java.util.List;
import java.util.Map;

public class ParkingLotsInfoController implements BaseController {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;

    public ParkingLotsInfoController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }


    @Override
    public String process() {
        response.send(formateInfos(parkingBoy.getParkingLotsInfo()));
        return "forward:main";
    }

    private String formateInfos(List<Map<String,String>> parkingLotsInfo) {
        String result = "|停车场ID|名称|车位|已停车辆|剩余车位|\n";
        result += "======================================\n";
        for (Map<String,String> map:parkingLotsInfo){
            result += String.format("|%s|%s|%s(个)|%s(辆)|%s(个)|\n",
                    map.get("id"),
                    map.get("name"),
                    map.get("capacity"),
                    map.get("parkNum"),
                    map.get("remainNum"));
        }
        return result;
    }
}

/*
|停车场ID|名称|车位|已停车辆|剩余车位|
======================================
|001|西南停车场|28(个)|8(辆)|20(个)|
|002|西南停车场|12(个)|8(辆)|4(个)|
 */

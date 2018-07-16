package Controller;

import View.Request;
import View.Response;

public class ServerMainController implements BaseController {
    private Request request;
    private Response response;

    public ServerMainController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
        return "main/1";
    }
}

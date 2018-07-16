package Controller;

import View.Request;
import View.Response;

public class GoToAddParkinglotPageController implements BaseController {
    private Request request;
    private Response response;

    public GoToAddParkinglotPageController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
        return "main/2/2";
    }
}

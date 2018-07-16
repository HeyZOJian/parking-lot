package Controller;

import View.Request;
import View.Response;

public class ErrorController implements BaseController {
    private Request request;
    private Response response;

    public ErrorController(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String process() {
        response.send("非法指令，请查证后再");
        return "forward:main";
    }
}

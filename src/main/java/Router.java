import Controller.ParkingController;
import View.Request;
import View.Response;

/**
 * Created by Vito Zhuang on 7/16/2018.
 */
public class Router {
	public static final String MAINPAGE = "main";
	public static final String PARKPAGE = "park";
	public static final String UNPARKPAGE = "unpark";
	
	private String currentPage;
	private ParkingController controller;
	private Response response;

	public Router(ParkingController controller) {
		this.controller = controller;
		this.response = new Response();
		this.currentPage = MAINPAGE;
	}

	public void getIndexView(){
		response.send("1. 停车\n" +
				"2. 取车 \n" +
				"请输入您要进行的操作：");
	}

	public void handleRequest(Request request) {

		switch (currentPage){
			case MAINPAGE:
				main(request);
				break;
			case PARKPAGE:
				park(request);
				break;
			case UNPARKPAGE:
				unpark(request);
				break;
		}
	}

	private void main(Request request) {

			switch (request.getParameter()){
				case "1":
					currentPage = PARKPAGE;
					parkView();

					break;
				case "2":
					currentPage = UNPARKPAGE;
					unparkView();
					break;
				default:
					response.send("非法指令，请查证后再输");
					getIndexView();
			}

	}

	private void parkView() {
		if(controller.isAllParkingLotFull()){
			response.send("车已停满，请晚点再来");
			currentPage = MAINPAGE;
			getIndexView();
		}
		else{
			response.send("请输入车牌号：");
		}
	}

	private void unparkView() {
		response.send("请输入小票编号：");
	}

	private void park(Request request){
		controller.park(request);
		currentPage = MAINPAGE;
		getIndexView();
	}

	private void unpark(Request request){
		controller.unpark(request);
		currentPage = MAINPAGE;
		getIndexView();
	}
}

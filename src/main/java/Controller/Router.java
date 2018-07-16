package Controller;

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

	public Router(ParkingController controller) {
		this.controller = controller;
		this.currentPage = MAINPAGE;
	}

	public void getIndexView(Response response) {
		controller.mainView(response);
	}

	public void handleRequest(Request request,Response response) {

		switch (currentPage) {
			case MAINPAGE:
				currentPage = controller.mainHandle(request,response);
				break;
			case PARKPAGE:
				currentPage = controller.park(request,response);
				break;
			case UNPARKPAGE:
				currentPage = controller.unpark(request,response);
				break;
		}
	}

	public String getCurrentPage() {
		return currentPage;
	}
}

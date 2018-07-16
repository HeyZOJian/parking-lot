package Controller;

import View.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vito Zhuang on 7/16/2018.
 */
public class Router {
	private Map<String,BaseController> routerMap = new HashMap<>();

	private String currentPath;

	public Router(){
	    this.currentPath = "main";
    }

	public void registerRouter(String router,BaseController controller){
	    routerMap.put(router,controller);
    }

    public void launch(){
	    routerMap.get(currentPath).process();
    }

    public void handleRequest(Request request){
	    String routePath = getRoutePath(request);
	    String forwarRoutePath = routerMap.get(routePath).process();
	    currentPath = forwarRoutePath;
	    if(forwarRoutePath!=null && forwarRoutePath.contains("forward:")){
	        currentPath = getForwardRoutePath(forwarRoutePath);
        }
    }

    private String getForwardRoutePath(String forwarRoutePath) {
        String routePath = forwarRoutePath.substring(forwarRoutePath.indexOf(":")+1,forwarRoutePath.length());
        routerMap.get(routePath).process();
        return routePath;
	}

    private String getRoutePath(Request request) {
        String subPath = request.getParameter().isEmpty()?"":translateRequestInput(request);
        return currentPath+"/"+subPath;
	}

    private String translateRequestInput(Request request) {
        if(Arrays.asList("1","2").contains(request.getParameter())
                || (request.getParameter().equals("3") && currentPath.contains("main/2")))
            return request.getParameter();
        return "*";
	}
}

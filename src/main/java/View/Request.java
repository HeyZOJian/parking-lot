package View;

import java.util.Scanner;

/**
 * Created by Vito Zhuang on 7/15/2018.
 */
public class Request {
	private String parameter;

	private Object object;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}

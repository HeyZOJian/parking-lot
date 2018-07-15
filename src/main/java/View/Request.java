package View;

import java.util.Scanner;

/**
 * Created by Vito Zhuang on 7/15/2018.
 */
public class Request {
	public String InputCommand() {
		Scanner scanner = new Scanner(System.in);
		String command = scanner.next();
		if (validate(command)) {
			return command;
		} else {
			return "非法指令，请查证后再输";
		}
	}

	private boolean validate(String command) {
		return command.equals("1") || command.equals("2");
	}
}

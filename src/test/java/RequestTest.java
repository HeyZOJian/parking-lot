/**
 * Created by Vito Zhuang on 7/15/2018.
 */
import View.Request;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RequestTest {
	@Test
	public void should_return_1_when_input_1() {
		Request request = new Request();
		String input = "1";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		assertThat("1",is(request.InputCommand()));
	}

	@Test
	public void should_return_2_when_input_2 (){
		Request request = new Request();
		String input = "2";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		assertThat("2",is(request.InputCommand()));
	}

	@Test
	public void should_return_error_message_when_input_other_number (){
		Request request = new Request();
		String input = "3";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		assertThat("非法指令，请查证后再输",is(request.InputCommand()));
	}

	@Test
	public void should_return_error_message_when_input_other_letter (){
		Request request = new Request();
		String input = "a";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		assertThat("非法指令，请查证后再输",is(request.InputCommand()));
	}
}

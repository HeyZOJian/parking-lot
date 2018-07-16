import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vito Zhuang on 7/16/2018.
 */
public class demo {
	public static void main(String[] args) {
		List<Integer>list = new LinkedList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(2);
		System.out.println(list);
		list.remove(2);
		System.out.println(list);
	}
}

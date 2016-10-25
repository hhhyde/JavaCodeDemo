package demos.CollectionDEmo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Administrator on 2016/10/20.
 */
public class QueueDemo {
	public static void main(String[] args) {
		Queue<String> ss = new LinkedList<String>();
		ss.add("0");
		ss.add("1");
		ss.add("2");
		System.out.println(ss);
		ss.offer("5");
		System.out.println(ss);

	}
}

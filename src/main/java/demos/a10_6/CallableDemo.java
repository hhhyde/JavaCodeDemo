package demos.a10_6;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	public String call() {
		return "result of TaskWithResult " + id;
	}
}

public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		ArrayList<Future<String>> results=new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) 
			results.add(exec.submit(new TaskWithResult(i)));
		for (Future<String> future : results) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(future.isDone());
		}
	}
}

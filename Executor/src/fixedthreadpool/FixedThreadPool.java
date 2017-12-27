package fixedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 20; i++) {
			Runnable syncRunnable = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			};
			executorService.execute(syncRunnable);
		}

	}
	
}

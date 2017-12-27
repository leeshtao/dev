package singlethreadexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
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

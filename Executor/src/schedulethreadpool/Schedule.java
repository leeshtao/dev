package schedulethreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule {

	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 20; i++) {
			Runnable syncRunnable = new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			};
			executorService.schedule(syncRunnable, 5000, TimeUnit.MILLISECONDS);
		}
	}

}

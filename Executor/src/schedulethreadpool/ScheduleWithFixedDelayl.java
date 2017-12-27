package schedulethreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayl {

	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		Runnable syncRunnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		};
		executorService.scheduleWithFixedDelay(syncRunnable, 5000, 3000, TimeUnit.MILLISECONDS);

	}
}

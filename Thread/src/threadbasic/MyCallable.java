package threadbasic;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {
	String name;

	public MyCallable(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + "开始工作==============");
		Random random = new Random();
		Thread.sleep(random.nextInt(5) * 100);
		// 模拟执行业务
		return name + ":执行完成";
	}

	public static void main(String[] args) throws Exception {
		MyCallable callable = new MyCallable("测试");
		FutureTask<String> futureTask = new FutureTask<String>(callable);
		Thread thread = new Thread(futureTask);
		thread.start();
		String result = futureTask.get();
		// 获取任务线程执行结果
		System.out.println("线程的执行结果：" + result);
	}
}
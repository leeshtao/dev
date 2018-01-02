package threadbasic;

import java.util.Random;

public class MyThread extends Thread {

	String flag;

	public MyThread(String flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("线程" + name + "开始工作了...");
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(random.nextInt(10) * 100);
				System.out.println(name + "=============" + flag);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread t0 = new MyThread("t0");
		Thread t1 = new MyThread("t1");
		t0.start();
		t1.start();
		// t0.run();
		// t1.run();
	}
}

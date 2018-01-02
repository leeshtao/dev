package threadbasic;

import java.util.Random;

public class MyRunnable implements Runnable {

	int x;

	public MyRunnable(int x) {
		this.x = x;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("线程" + name + "开始执行");
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(random.nextInt(10) * 100);
				System.out.println(name + "=============" + x);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunnable(1), "线程1");
		Thread t2 = new Thread(new MyRunnable(2), "线程2");
		t1.start();
		t2.start();
	}
}

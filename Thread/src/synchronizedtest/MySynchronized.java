package synchronizedtest;

public class MySynchronized {

	public static void main(String[] args) {
		final MySynchronized synchronized1 = new MySynchronized();
		final MySynchronized synchronized2 = new MySynchronized();
		new Thread("thread1") {
			@Override
			public void run() {
				synchronized (synchronized1) {
					try {
						System.out.println(this.getName() + ":start");
						Thread.sleep(1000);
						System.out.println(this.getName() + ":wake up");
						System.out.println(this.getName() + ":end");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread("thread2") {
			@Override
			public void run() {
				synchronized (synchronized1) { // 争抢同一把锁时，线程1没释放之前，线程2只能等待
					// synchronized (synchronized2) {
					// 如果不是一把锁，可以看到两句话交叉打印，发生争抢
					System.out.println(this.getName() + ":start");
					System.out.println(this.getName() + ":end");
				}
			}
		}.start();
	}

}
package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李世涛
 * @date 2017年12月28日
 * 
 *       测试Lock接口:trylock()
 * 
 *       观察现象：一个线程获得锁后，另一个线程取不到锁，不会一直等待
 */
public class MyTryLock {
	private static List<Integer> arrayList = new ArrayList<Integer>();
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		new Thread("线程1") {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = lock.tryLock();
				System.out.println(thread.getName() + "=======" + tryLock);
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "得到了锁");
						for (int i = 0; i < 20; i++) {
							arrayList.add(i);
						}
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
						System.out.println(thread.getName() + "释放了锁");
					}
				}
			}
		}.start();

		new Thread("线程2") {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				boolean tryLock = false;
				try {
					// tryLock = lock.tryLock();
					tryLock = lock.tryLock(1000, TimeUnit.SECONDS);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(thread.getName() + "=======" + tryLock);
				if (tryLock) {
					try {
						System.out.println(thread.getName() + "得到了锁");
						for (int i = 0; i < 20; i++) {
							arrayList.add(i);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
						System.out.println(thread.getName() + "释放了锁");
					}
				}
			}
		}.start();
	}

}
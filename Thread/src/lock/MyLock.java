package lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 李世涛
 * @date 2017年12月28日
 * 
 *       测试Lock接口:lock(),unlock()
 */
public class MyLock {

	private static ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private static Lock lock = new ReentrantLock();

	public static <E> void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				// 获取锁
				lock.lock();
				try {
					System.out.println(thread.getName() + "得到了锁");
					for (int i = 0; i < 5; i++) {
						arrayList.add(i);
					}
					System.out.println(arrayList.toString());
				} catch (Exception e) {
				} finally {
					System.out.println(thread.getName() + "释放了锁");
					// 释放锁
					lock.unlock();
				}

			};
		}.start();

		new Thread() {
			@Override
			public void run() {
				Thread thread = Thread.currentThread();
				lock.lock();
				try {
					System.out.println(thread.getName() + "得到了锁");
					for (int i = 5; i <= 10; i++) {
						arrayList.add(i);
					}
					System.out.println(arrayList.toString());
				} catch (Exception e) {
				} finally {
					System.out.println(thread.getName() + "释放了锁");
					lock.unlock();
				}
			};
		}.start();
	}
}
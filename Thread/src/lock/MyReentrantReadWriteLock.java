package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 李世涛
 * @date 2017年12月28日
 * 
 *       如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。
 *       如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。
 */
public class MyReentrantReadWriteLock {
	ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		final MyReentrantReadWriteLock myTest = new MyReentrantReadWriteLock();
		new Thread("线程1") {
			@Override
			public void run() {
				myTest.read(Thread.currentThread());
				myTest.writer(Thread.currentThread());
			}
		}.start();
		new Thread("线程2") {
			@Override
			public void run() {
				myTest.read(Thread.currentThread());
				myTest.writer(Thread.currentThread());
			}
		}.start();
	}

	private void read(Thread thread) {
		readWriteLock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println(thread.getName() + "===正在执行读操作");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
			System.out.println(thread.getName() + "==释放读锁");
		}
	}

	private void writer(Thread thread) {
		readWriteLock.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println(thread.getName() + "===正在执行写操作");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
			System.out.println(thread.getName() + "==释放写锁");
		}
	}
}

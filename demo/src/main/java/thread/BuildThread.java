package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/12 9:45
 */
public class BuildThread {
	public static int num = 1000;

	private static Object object = new Object();
	private static Object object2 = new Object();
	private static FutureTask<Integer> tasks = new FutureTask<>((Callable<Integer>) () -> {
		synchronized(object2){
			num += 100;
			System.out.println("num新" + num);
			Thread.sleep(2000);
		}
		return num;
	});
	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			if (i % 3 == 0) {
				Thread mythread = new MyThread();
				mythread.start();
			} else if (i % 3 == 1) {
				Thread mythread2 = new Thread(new MyThread2());
				mythread2.start();
			} else {
				Thread thread3 = new Thread(tasks);
				thread3.start();
			}

		}
	}

	private static class MyThread extends Thread {
		@Override
		public void run() {
			try {
				synchronized (object) {
					num += 100;
					System.out.println("num加" + num);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class MyThread2 implements Runnable {

		@Override
		public synchronized void run() {
			try {
				num -= 200;
				System.out.println("num减" + num);
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}


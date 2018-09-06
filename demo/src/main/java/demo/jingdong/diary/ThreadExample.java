package demo.jingdong.diary;


public class ThreadExample extends Thread {

	volatile private boolean stop = false;

	public static void main(String[] args) throws InterruptedException{

		ThreadExample thread = new ThreadExample();

		System.out.println("Thread is starting....");
		thread.start();
		Thread.sleep(3000);

		System.out.println("Asking thread to stop..");
		thread.stop = true;
		Thread.sleep(3000);
		System.out.println("Stoping application....");

	}

	public void run(){

		while(!stop){
			System.out.println("Thread is running...");

			long time = System.currentTimeMillis();

			while((System.currentTimeMillis()-time)<1000){}

		}

		System.out.println("Thread exiting under request...");

	}
}

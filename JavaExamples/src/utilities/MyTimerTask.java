package utilities;

public abstract class MyTimerTask implements Runnable {
	private final long interval;
	private final MyTimer timer;
	
	public MyTimerTask(long interval) {
		this.interval = interval;
		timer = new MyTimer();
	}
	
	public void start() {
		timer.start();
		while(true) {
			if(timer.time() > interval) {
				timer.lap();
				run();
			}
		}
	}
}

package utilities;

public class MyTimer {
	private long start = 0l;
	
	public long start() {
		return start = System.currentTimeMillis();
	}
	
	public long lap() {
		return start();
	}
	
	public long time() {
		return System.currentTimeMillis() - start;
	}
	
	public void reset() {
		start = 0l;
	}
}

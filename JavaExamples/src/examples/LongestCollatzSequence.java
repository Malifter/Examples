package examples;

public class LongestCollatzSequence {

	public static void main(String [] args) {
		int threshold = 1000000;
		System.out.println("The starting number lower than " + threshold +
				" that produces the longest Collatz sequence is: " + findLongestCollatzSequence(threshold));
	}
	
	public static int findLongestCollatzSequence(int threshold) {
		if(threshold < 2) return 1;
		CollatzSequence collatzSequence = new CollatzSequence();
		for(int start = 1; start < threshold; start++) {
			long n = start;
			int size = 1;
			while(n != 1) { // This is unknown, possibly infinite
				n = (n % 2 == 0) ? n = n / 2 : (3 * n) + 1;
				size++;
			}
			if(size > collatzSequence.size) {
				collatzSequence.start = start;
				collatzSequence.size = size;
			}
		}
		return collatzSequence.start;
	}
	
	private static class CollatzSequence {
		private int size = 0;
		private int start = 1;
		
		public String toString() {
			return Integer.toString(start);
		}
	}
}

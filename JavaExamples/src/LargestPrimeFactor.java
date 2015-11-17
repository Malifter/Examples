
public class LargestPrimeFactor {

	public static void main(String [] args) {
		long target = 600851475143l;
		System.out.println("The largest prime factor of the number "
				+ target + " is: " + findLargestPrimeFactor(target));
	}
	
	public static long findLargestPrimeFactor(long target) {
		long max = 0;
		for(long factor = 2; target > 1; factor++) {
			for(; target % factor == 0; target /= factor) max = factor;
			if(factor*factor > target) return (target > 1) ? target : max;
		}
		return max;
	}
}

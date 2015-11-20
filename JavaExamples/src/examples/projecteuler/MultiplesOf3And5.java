package examples.projecteuler;
import java.util.Arrays;

public class MultiplesOf3And5 {
	
	public static void main(String args[]) {
		final int [] multiples = {3, 5}; // so that we can change the multiples to whatever we want
		final int threshold = 1000;
		System.out.println("The sum of all the multiples of " + Arrays.toString(multiples)
			+ " below " + threshold +  " is: " + findSumOfMultiples(multiples, threshold));
	}
	
	public static int findSumOfMultiples(final int [] multiples, final int threshold) {
		int sum = 0;
		for(int i = 1; i < threshold; i++) {
			for(int multiple: multiples) {
				if(i % multiple == 0) {
					sum += i;
					break;
				}	
			}
		}
		return sum;
	}
}

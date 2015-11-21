package examples.projecteuler;

import java.util.ArrayList;

public class NonAbundantSums {
	final static int MIN_ABUNDANT_NUM = 12;
	final static int KNOWN_MAX = 28124; // All number == to this and greater is the sum of two abundant numbers

	public static void main(String [] args) {
		
		System.out.println("The sum of all positive integers which cannot be "
				+ "written as the sum of two abundant numbers is: " + findNonAbundantSums());
	}
	
	public static int findNonAbundantSums() {
		// find all abundant numbers O(n^2)
		ArrayList<Integer> abundantNumbers = new ArrayList<Integer>();
		abundantNumbers.add(MIN_ABUNDANT_NUM);
		for(int i = MIN_ABUNDANT_NUM+1; i < KNOWN_MAX; i++) {
			int divisorSum = 1;
			int maxDivisor = i;
			for(int j = 2; j < maxDivisor; j++) {
				maxDivisor = i / j;
				if(i % j == 0) {
					divisorSum += maxDivisor == j ? j : maxDivisor + j;
				}
			}
			if(divisorSum > i) {
				abundantNumbers.add(i);
			}
		}
		
		// find the sums of every pair of abundant numbers O(n^2)
		ArrayList<Integer> abundantSumPairs = new ArrayList<Integer>();
		for(int j = 0; j < abundantNumbers.size(); j++) {
			for(int k = j; k < abundantNumbers.size(); k++) {
				abundantSumPairs.add(abundantNumbers.get(j) + abundantNumbers.get(k));
			}
		}
		
		// find sum of numbers that cannot be written as the sum of two abundant numbers. O(n^2)
		int sum = 0;
		for(int i = 1;  i < KNOWN_MAX; i++) {
			boolean sumFound = false;
			for(int j = 0; abundantSumPairs.get(j) <= i; j++) {
				if(abundantSumPairs.get(j) == i) {
					sumFound = true;
					break;
				}
			}
			if(!sumFound) {
				sum += i;
				System.out.println(i);
			}
		}
		return sum;
	}
}

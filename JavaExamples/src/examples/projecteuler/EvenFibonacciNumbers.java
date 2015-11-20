package examples.projecteuler;

public class EvenFibonacciNumbers {

	public static void main(String [] args) {
		int start1 = 1;
		int start2 = 2;
		int threshold = 4000000;
		System.out.println("The sum of the even-valued terms of the Fibonacci sequence below "
				+ threshold + " is: " + findEvenFibonacciSum(threshold, start1, start2));
	}
	
	public static int findEvenFibonacciSum(int threshold, int oldest, int previous) {
		int current = oldest + previous;
		if(current > threshold) return (oldest % 2 == 0 ? oldest : 0) + (previous % 2 == 0 ? previous : 0);
		return (oldest % 2 == 0 ? oldest : 0) + findEvenFibonacciSum(threshold, previous, current);
	}
}

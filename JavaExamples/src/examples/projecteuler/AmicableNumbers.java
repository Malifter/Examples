package examples.projecteuler;

public class AmicableNumbers {

	public static void main(String [] args) {
		int n = 10000; // all amicable numbers under n
		System.out.println("The sum of all amicable numbers under " + n + " is: " + findAmicableNumbersSum(n));
	}
	
	public static long findAmicableNumbersSum(int n) {
		// calculate the sum of proper divisors
		int [] divisorSums = new int [n]; // wastes O(1) space for index == 0, but makes the loops more straightforward.
		for(int i = 1; i < n; i++) {
			divisorSums[i] = 1; // all numbers are divisible by themselves
			int maxDivisor = i;
			for(int divisor = 2; divisor < maxDivisor; divisor++) {
				maxDivisor = i / divisor;
				if(i % divisor == 0) {
					divisorSums[i] += divisor + maxDivisor;
				}
			}
		}
		
		// check which divisors are amicable
		int sum = 0;
		for(int i = 1; i < n; i++) {
			if(divisorSums[i] < n &&
					i != divisorSums[i] &&
					i == divisorSums[divisorSums[i]]) {
				sum += i;
			}
		}
		return sum;
	}
}

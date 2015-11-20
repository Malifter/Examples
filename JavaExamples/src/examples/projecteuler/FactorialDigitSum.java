package examples.projecteuler;

import java.math.BigInteger;

public class FactorialDigitSum {

	public static void main(String [] args) {
		int n = 100; // number that we want to find the factorial for and sum it's digits
		System.out.println("The sum of the digits of the number " + n + " is: " + findFactorialDigitSum(n));
	}
	
	public static long findFactorialDigitSum(int n) {
		BigInteger factorial = BigInteger.valueOf(n);
		for(int i = n; i > 0; i--) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}
		long sum = 0;
		BigInteger [] quotientAndRemainder;
		while(!factorial.equals(BigInteger.ZERO)) {
			quotientAndRemainder = factorial.divideAndRemainder(BigInteger.TEN);
			sum += quotientAndRemainder[1].intValue();
			factorial = quotientAndRemainder[0];
		}
		return sum;
	}
}

package examples.projecteuler;

public class HighlyDivisibleTriangularNumber {

	public static void main(String [] args) {
		int n = 500; // min divisors
		System.out.println("The value for the first triangle number to have over " + n + " divisors is: " + findDivisibleTriangleNumber(n));
	}
	
	public static long findDivisibleTriangleNumber(int n) {
		if(n <= 1) return 1;
		TriangleNumber triangle = new TriangleNumber();
		for(int i = 1; triangle.divisors < n; i++) {
			triangle.number = (i*(i+1))/2;
			triangle.divisors = 2; // all numbers are divisible by 1 and themselves
			long maxDivisor = triangle.number;
			for(int j = 2; j < maxDivisor; j++) {
				if(triangle.number % j == 0) {
					triangle.divisors += 2;
					maxDivisor = triangle.number / j;
				}
			}
		}
		return triangle.number;
	}
	
	private static class TriangleNumber {
		private long number = 0;
		private int divisors = 0;
	}
}

// 1 and itself are given
// divide from 2...num - keep i and d if r=0
// stop when i < smallest d found

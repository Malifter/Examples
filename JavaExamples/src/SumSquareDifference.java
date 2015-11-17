
public class SumSquareDifference {

	public static void main(String [] args) {
		int n = 100; // first n natural numbers
		System.out.println("The difference between the sum of squares and the square of the sum for the first "
				+ n + " natural numbers is: " + findSumSquareDifference(n));
	}
	
	public static int findSumSquareDifference(int n) {
		int sumOfSquares = 0;
		int squareOfSum = 0;
		for(int i = 1; i < n + 1; i++) {
			sumOfSquares += i*i;
			squareOfSum += i;
		}
		squareOfSum *= squareOfSum;
		return squareOfSum - sumOfSquares;
	}
}

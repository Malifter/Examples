package examples.projecteuler;

public class SpecialPythagoreanTriplet {

	public static void main(String [] args) {
		int sum = 1000; // this sum is 3^2+4^2=5^2 => 9+16=25 => 3+4+5=12 => 3*4*5=60
		System.out.println("The product of the Pythagorean triplet that sums to "
				+ sum + " is: " + findPythagoreanTripletProduct(sum));
	}
	
	public static int findPythagoreanTripletProduct(int sum) {
		// note that because a < b < c then a > 1, b > 2, c > 3
		int half = sum/2;
		for(int b = 3; b < half; b++) {
			for(int a = 2; a < b; a++) {
				int c; double sqrt = Math.sqrt(a*a + b*b);
				if(sqrt == (c = (int) sqrt) && a+b+c == sum) return a*b*c;
			}
		}
		return -1;
	}
}

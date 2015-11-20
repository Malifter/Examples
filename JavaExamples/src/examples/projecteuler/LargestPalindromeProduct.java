package examples.projecteuler;

public class LargestPalindromeProduct {
	static final int POWER_10 = 10;
	static final int MAX_DIGIT = 9;

	public static void main(String [] args) {
		int digits = 3; // clearly not scalable for large N -- integer overflow
		System.out.println("The largest palindrome made from the product of two "
				+ digits +"-digit numbers is: " + findLargestPalindromeProduct(digits));
	}
	
	public static Palindrome findLargestPalindromeProduct(int digits) {
		int max = MAX_DIGIT;
		int min = digits > 1 ? 1 : 0;
		for(int i = 1; i < digits; i++) {
			max = max * POWER_10 + MAX_DIGIT;
			min = min * POWER_10;
		}

		Palindrome palindrome = new Palindrome();
		for(int i = max; i > min; i--) {
			for(int j = i; j > min; j--) {
				int product = i * j;
				if(product <= palindrome.product) break;
				String s = Integer.toString(product);
				int half = s.length()/2;
				boolean isPalindrome = true;
				for(int k = 0;  k < half; k++) {
					if(s.charAt(k) != s.charAt(s.length() - k - 1)) {
						isPalindrome = false;
						break;
					}
				}
				if(isPalindrome) {
					palindrome.product = product;
					palindrome.first = i;
					palindrome.second = j;
					break;
				}
			}
		}
		return palindrome;
	}
	
	private static class Palindrome {
		private int first = 0;
		private int second = 0;
		private int product = 0;
		
		public String toString() {
			return product + " = " + first + " * " + second;
		}
	}
}

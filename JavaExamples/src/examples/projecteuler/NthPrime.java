package examples.projecteuler;

public class NthPrime {

	public static void main(String [] args) {
		int n = 10001; // The nth prime to find
		System.out.println("The " + n + intTense(n) + " prime number is: " + findNthPrime(n));
	}
	
	public static String intTense(int n) {
	    if (n >= 11 && n <= 13) {
	        return "th";
	    }
	    switch (n % 10) {
	        case 1:  return "st";
	        case 2:  return "nd";
	        case 3:  return "rd";
	        default: return "th";
	    }
	}
	
	public static int findNthPrime(int n) {
		if(n <= 1) return n < 1 ? -1 : 2;
		int primes = 1; // 2 is the first prime so count it
		for(int i = 3; true; i+=2) { // 2 is the only even prime so skip even numbers
			if(isPrime(i)) if(++primes == n) return i;
		}
	}
	
	public static boolean isPrime(int target) {
		int root = (int) Math.sqrt(target);
		for(long i = 2; i <= root; i++) {
	        if (target % i == 0) {
	            return false;
	        }
	    }
		return true;
	}
}

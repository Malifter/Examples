package examples;
import java.util.ArrayList;

public class SummationOfPrimes {

	public static void main(String [] args) {
		int threshold = 2000000; // all primes below threshold
		System.out.println("The sum of all the primes below " + threshold + " is: " + findSummationOfPrimes(threshold));
	}
	
	public static long findSummationOfPrimes(int threshold) {		
		// sieve of eratosthenes
		long sum = 0, start, end;
		start = System.currentTimeMillis();
		for(int prime: sieveOfEratosthenes(threshold)) sum += prime;
		end = System.currentTimeMillis();
		System.out.println("Sieve of Eratosthenes took " + (end - start) + " milliseconds to find the sum of primes below " + threshold + ": sum = " + sum);
		
		// sieve of sundaram
		sum = 0;
		start = System.currentTimeMillis();
		for(int prime: sieveOfSundaram(threshold)) sum += prime;
		end = System.currentTimeMillis();
		System.out.println("Sieve of Sundaram took " + (end - start) + " milliseconds to find the sum of primes below " + threshold + ": sum = " + sum);
		
		// sieve of atkin
		sum = 0;
		start = System.currentTimeMillis();
		for(int prime: sieveOfAtkin(threshold)) sum += prime;
		end = System.currentTimeMillis();
		System.out.println("Sieve of Atkin took " + (end - start) + " milliseconds to find the sum of primes below " + threshold + ": sum = " + sum);
		
		return sum;
	}
	
	public static ArrayList<Integer> sieveOfEratosthenes(int threshold) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		boolean [] sieve = new boolean [threshold - 2]; // false = prime, true = non-prime
		for(int i = 2; i*i < threshold; i++) {
			if(sieve[i-2]) continue;
			for(int j = i*i; j < threshold; j += i) sieve[j-2] = true;
		}
		for(int i = 0; i < threshold - 2; i++) {
			if(!sieve[i]) primes.add(i+2);
		}
		return primes;
	}
	
	public static ArrayList<Integer> sieveOfSundaram(int threshold) {
		ArrayList<Integer> primes = new ArrayList<Integer>(); primes.add(2);
		int n = (threshold - 1) / 2;
		boolean [] sieve = new boolean [n]; // false = prime, true = non-prime
		int half = ((n + 1) / 4) + 1;
		for(long i = 1; i < half; i++) {
			for(long j = i; j < n + 1; j++) {
				long t = i + j + 2*i*j;
				if(t-1 >= n) break;
				if(sieve[(int) (t-1)]) continue;
				if(t <= n + 1) sieve[(int) (t-1)] = true;
			}
		}
		for(int i = 1; i < n + 1; i++) {
			if(!sieve[i-1]) primes.add(i*2 + 1);
		}
		return primes;
	}
	
	public static ArrayList<Integer> sieveOfAtkin(int threshold) {
		ArrayList<Integer> primes = new ArrayList<Integer>(); primes.add(2); primes.add(3); primes.add(5);
		boolean [] sieve = new boolean [threshold]; // false = non-prime, true = prime
		int root = (int) Math.ceil(Math.sqrt(threshold - 1));
		for(int x = 1; x < root; x++) {
			for(int y = 1; y < root; y++) {
				// precompute x*x and y*y at the cost of O(2) memory
				int x2 = x*x;
				int y2 = y*y;
				int n = 4*x2 + y2; // 4x^2 + y^2
				if(n <= threshold) {
					switch(n % 60) {
						case 1: case 13: case 17: case 29: case 37: case 41: case 49: case 53:
							sieve[n] = !sieve[n];
							break;
						default:break;
					}
				}
				n = n - x2; // 3x^2 + y^2
				if(n <= threshold) {
					switch(n % 60) {
						case 7: case 19: case 31: case 43:
							sieve[n] = !sieve[n];
							break;
						default:break;
					}
				}
				if(x > y) {
					n = (n - y2) - y2; // 3x^2 - y^2
					if(n <= threshold) {
						switch(n % 60) {
							case 11: case 23: case 47: case 59:
								sieve[n] = !sieve[n];
								break;
							default:break;
						}
					}
				}
			}
		}
		for(int i = 7; i <= root; i++) {
			if(sieve[i]) {
				for(long j = i*i; j > 0 && j < threshold; j += i*i) sieve[(int) (j)] = false;
			}
		}
		for(int i = 0; i < threshold; i++) {
			if(sieve[i]) primes.add(i);
		}
		return primes;
	}
}

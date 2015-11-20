package examples.projecteuler;
import java.math.BigInteger;

public class LatticePaths {

	public static void main(String [] args) {
		int size = 20; // grid size
		System.out.println("The number of Lattice paths for a " + size + "x" + size + " grid is: " + findLatticePathsSeries(size));
	}
	
	// My Solutions
	public static BigInteger findLatticePathsSeries(int n) {
		BigInteger v = BigInteger.valueOf(1);
		for(long i = 1; i <= n; i++) {
			//v = (long) (v * (3 + ((i-2) / i)));
			//v = (long) ((v * ((4*i) - 2)) / i); // reordering of above
			v = v.multiply(BigInteger.valueOf((4*i) - 2));
			v = v.divide(BigInteger.valueOf(i));
		}
		return v;
	}
	
	// BFS used to find initial series for grids sized 1 - 10 to determine above series equation
	// Note: Brute-forcing is a very slow implementation -- O(2N choose N)
	public static long findLatticePathsRecursive(int n, int r, int c) {
		if(r == n && c == n) return 1; // reached the end point
		long sum = 0;
		if(r < n) sum += findLatticePathsRecursive(n, r+1, c);
		if(c < n) sum += findLatticePathsRecursive(n, r, c+1);
		return sum;
	}
	
	// Other Solutions
	public static long findLatticePathsDynamic(int n) { // in dynamic programming we solve a problem backwards at the cost of O((n+1)^2) space
		long [][] grid = new long[n+1][n+1]; // need grid N+1 because there are N+1xN+1 vertices in a NxN grid
		
		// initialize grid's bottom and rightmost vertices with weight 1
		for(int i = 0; i < n; i++) {
			grid[i][n] = 1; // rightmost
			grid[n][i] = 1; // bottom
		}
		
		for(int r = n - 1; r >= 0; r--) { // count backwards from points just before end-point
			for(int c = n - 1; c >= 0; c--) {
				grid[r][c] = grid[r+1][c] + grid[r][c+1]; // add up total from my right and down
			}
		}
		
		return grid[0][0];
	}
	
	public static long findLatticePathsMultiplicitive(int n) {
		long v = 1;
		for(double i = 1; i <= n; i++) {
			v = (long) ((v * ((2*n) - (i-1))) / i); // solves in reverse order of cobinatronic
		}
		return v;
	}
	
	// Note: This is the best implementation. It requires the least constant-time mathematical steps
	// and is tied for smallest memory imprint with O(n) runtime.
	public static long findLatticePathsCombinatronic(int n) { // 2n choose n
		long v = 1;
		for(double i = 1; i <= n; i++) {
			v = (long) ((v * (n + i)) / i);
		}
		return v;
	}
}

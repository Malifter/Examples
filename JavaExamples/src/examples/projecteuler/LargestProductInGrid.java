package examples.projecteuler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.List;

public class LargestProductInGrid {

	public static void main(String [] args) throws IOException {
		int [][] grid = parseGridFromFile("grid.txt");
		int n = 4;
		DecimalFormat df = new DecimalFormat("00");
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				System.out.print(df.format(grid[r][c]) + " ");
			}
			System.out.println();
		}
		System.out.println("The greatest product for the above " + grid.length + "x" + grid[0].length
				+ " for " + n + " adjacent numbers in the same direction (e.g., up/down, left/right, diagonally) is: "
				+ findLargestProductInGrid(n, grid));
	}
	
	public static Product findLargestProductInGrid(int n, int [][] grid) {
		Product maxProduct = new Product();
		// left/right
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length - n + 1; c++) {
				long product = 1;
				int [] values = new int [n];
				for(int i = 0; i < n; i++) {
					values[i] = grid[r][c+i];
					product *= values[i];
				}
				if(product > maxProduct.product) {
					maxProduct.product = product;
					maxProduct.values = values;
				}
			}
		}
		
		// up/down
		for(int c = 0; c < grid[0].length; c++) {
			for(int r = 0; r < grid.length - n + 1; r++) {
				long product = 1;
				int [] values = new int [n];
				for(int i = 0; i < n; i++) {
					values[i] = grid[r+i][c];
					product *= values[i];
				}
				if(product > maxProduct.product) {
					maxProduct.product = product;
					maxProduct.values = values;
				}
			}
		}
		
		// diagonally - back-slash
		for(int r = 0; r < grid.length - n + 1; r++) {
			for(int c = 0; c < grid[r].length - n + 1; c++) {
				long product = 1;
				int [] values = new int [n];
				for(int i = 0; i < n; i++) {
					values[i] = grid[r+i][c+i];
					product *= values[i];
				}
				if(product > maxProduct.product) {
					maxProduct.product = product;
					maxProduct.values = values;
				}
			}
		}
		
		// diagonally - forward-slash
		for(int r = 0; r < grid.length - n + 1; r++) {
			for(int c = n - 1; c < grid[r].length; c++) {
				long product = 1;
				int [] values = new int [n];
				for(int i = 0; i < n; i++) {
					values[i] = grid[r+i][c-i];
					product *= values[i];
				}
				if(product > maxProduct.product) {
					maxProduct.product = product;
					maxProduct.values = values;
				}
			}
		}
		return maxProduct;
	}
	
	public static int [][] parseGridFromFile(String filename) throws IOException {
		List<String> lines = Files.readAllLines(new File(filename).toPath()); // can just use normal buffered reader too but needed to know line count
		int [][] grid = new int [(int) lines.size()][];
		int r = 0; for(String line: lines) {
			String [] parts = line.split("\\s+");
			grid[r] = new int [parts.length];
			for(int c = 0; c < parts.length; c++) {
				grid[r][c] = Integer.parseInt(parts[c]);
			} r++;
		}
		return grid;
	}
	
	private static class Product {
		private int [] values;
		private long product = 0;
		
		public String toString() {
			String out = "";
			for(int i: values) {
				out += i + " * ";
			}
			out = out.substring(0, out.lastIndexOf('*'));
			out += "= " + product;
			return out;
		}
	}
}

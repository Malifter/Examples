package examples.projecteuler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class MaximumPathSumI {

	public static void main(String [] args) throws IOException {
		int [] triangle = parseTriangleFromFile("small_triangle.txt");
		System.out.println("Input Triangle:");
		printTriangle(triangle);
		System.out.println("\nThe maximum total moving along adjacent numbers from top to bottom of the triangle is: "
				+ findMaximumPathSumFromTriangle(triangle));
	}
	
	public static int findMaximumPathSumFromTriangle(int [] triangle) {
		// traverse through the triangle in reverse order
		for(int level = (int) (Math.floor(Math.sqrt(2 * triangle.length)) - 2); level >= 0; level--) {
			for(int index = level; index >= 0; index--) {
				int i = index + ((level*(level+1))/2);
				triangle[i] += Math.max(triangle[i+level+1], triangle[i+level+2]);
			}
		}
		System.out.println("\nDynamic Programming Triangle Results:");
		printTriangle(triangle);
		return triangle[0];
	}
	
	public static void printTriangle(int [] triangle) {
		for(int level = 0; level < Math.floor(Math.sqrt(2 * triangle.length)); level++) {
			for(int index = 0; index < level+1; index++) {
				System.out.print(triangle[index + ((level*(level+1))/2)] + "\t");
			}
			System.out.println();
		}
	}
	
	public static int [] parseTriangleFromFile(String filename) throws IOException {
		List<String> lines = Files.readAllLines(new File(filename).toPath());
		String [] parts = lines.get(lines.size()-1).split("\\s+");
		int [] triangle = new int [(parts.length * (parts.length + 1)) / 2];
		for(int level = 0; level < lines.size(); level++) {
			parts = lines.get(level).split("\\s+");
			for(int index = 0; index < level+1; index++) {
				triangle[index + ((level*(level+1))/2) ] = Integer.parseInt(parts[index]);
			}
		}
		return triangle;
	}
}

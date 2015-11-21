package examples.projecteuler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NameScores {

	public static void main(String [] args) throws FileNotFoundException, IOException {
		List<String> names = parseAndSortListFromFile("p022_names.txt");
		System.out.println("The sum of all of the name scores is: " + findNameScores(names));
	}
	
	public static long findNameScores(List<String> names) {
		long sum = 0;
		for(int i = 1; i < names.size()+1; i++) {
			String name = names.get(i-1);
			int namescore = 0;
			for(int j = 0; j < name.length(); j++) {
				namescore += name.charAt(j) - ('A' - 1);
			}
			sum += namescore * i;
		}
		return sum;
	}
	
	@SuppressWarnings("resource")
	public static List<String> parseAndSortListFromFile(String filename) throws FileNotFoundException, IOException {
		List<String> data = Arrays.asList(new BufferedReader(new FileReader(new File(filename))).readLine().replaceAll("\"", "").split(","));
		Collections.sort(data);
		return data;
	}
}

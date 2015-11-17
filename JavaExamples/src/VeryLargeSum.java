
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;

public class VeryLargeSum {

	public static void main(String [] args) throws IOException {
		List<String> longNumbers = parseLongNumbersFromFile("100_50_digit_numbers.txt");
		int n = 10; // first n digits of large sum
		System.out.println("The first " + n + " digits of the large sum are: " + findFirstNDigitsOfLargeSumComplex(n, longNumbers));
	}
	
	public static String findFirstNDigitsOfLargeSumSimple(int n, List<String> longNumbers) {
		BigInteger sum = null;
		for(String number: longNumbers) {
			if(sum == null) sum = new BigInteger(number);
			else sum = sum.add(new BigInteger(number));
		}
		String firstndigits = "";
		BigInteger[] resultAndRemainder;
		while(sum.compareTo(BigInteger.ZERO) > 0) {
			resultAndRemainder = sum.divideAndRemainder(BigInteger.TEN);
			firstndigits += resultAndRemainder[1].toString();
			sum = resultAndRemainder[0];
		}
		return new StringBuilder(firstndigits).reverse().toString().substring(0, n);
	}
	
	public static String findFirstNDigitsOfLargeSumComplex(int n, List<String> longNumbers) {
		String firstndigits = "";
		int carryover = 0;
		for(int digit = longNumbers.get(0).length() - 1; digit >= 0; digit--) {
			int sum = carryover;
			for(String number: longNumbers) {
				sum += number.charAt(digit) - '0'; // shift to Literal from ASCII
			}
			firstndigits += sum % 10;
			carryover = sum / 10;
		}
		while(carryover != 0) {
			firstndigits += carryover % 10;
			carryover = carryover / 10;
		}
		return new StringBuilder(firstndigits).reverse().toString().substring(0, n);
	}
	
	public static List<String> parseLongNumbersFromFile(String filename) throws IOException {
		return Files.readAllLines(new File(filename).toPath());
	}
}

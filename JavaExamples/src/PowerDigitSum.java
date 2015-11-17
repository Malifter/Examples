import java.math.BigInteger;

public class PowerDigitSum {

	public static void main(String [] args) {
		int power = 1000;
		int base = 2;
		System.out.println("The sum of the digits of " + base
				+ superscript(Integer.toString(power)) + " is: " + findPowerDigitSum(BigInteger.valueOf(base), power));
	}
	
	public static int findPowerDigitSum(BigInteger base, int power) {
		BigInteger result = base;
		for(int i = 0; i < power - 1; i++) {
			result = result.multiply(base);
		}
		System.out.println(result.toString());
		int sum = 0;
		BigInteger[] resultAndRemainder;
		while(result.compareTo(BigInteger.ZERO) > 0) {
			resultAndRemainder = result.divideAndRemainder(BigInteger.TEN);
			sum += resultAndRemainder[1].intValue();
			result = resultAndRemainder[0];
		}
		return sum;
	}
	
	public static String superscript(String str) {
	    str = str.replaceAll("0", "⁰");
	    str = str.replaceAll("1", "¹");
	    str = str.replaceAll("2", "²");
	    str = str.replaceAll("3", "³");
	    str = str.replaceAll("4", "⁴");
	    str = str.replaceAll("5", "⁵");
	    str = str.replaceAll("6", "⁶");
	    str = str.replaceAll("7", "⁷");
	    str = str.replaceAll("8", "⁸");
	    str = str.replaceAll("9", "⁹");         
	    return str;
	}
}

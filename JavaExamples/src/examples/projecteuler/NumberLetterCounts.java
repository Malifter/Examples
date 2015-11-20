package examples.projecteuler;

public class NumberLetterCounts {

	public static void main(String [] args) {
		long start = 1;
		long end = 1000;
		System.out.println("The written notation for numbers " + start + " to " + end
				+ " contain " + findNumberLetterCounts(start, end) + " letters.");
	}
	
	public static long findNumberLetterCounts(long start, long end) {
		long sum = 0;
		if(start == 0);
		for(long i = start; i <= end; i++) {
			String number = new StringBuilder(Long.toString(i)).reverse().toString();
			String name = "";
			boolean nonZero = false;
			for(int digit = 0; digit < number.length(); digit++) {
				switch(digit % 3) {
				case 0:
					if(!nonZero && number.charAt(digit) - '0' != 0) nonZero = true;
					if(digit > 0) {
						if(name.length() > 0) name = String.join("", "and", name);
						name = String.join("", NumberNames.commaNames[digit / 3], name);
					}
					if(digit + 1 < number.length() && number.charAt(digit + 1) - '0' < 2) {
						int twoDigits = Integer.parseInt("" + number.charAt(digit + 1) + number.charAt(digit));
						if(twoDigits > 0) name = String.join("", NumberNames.numberNames[twoDigits], name);
						if(number.length() > 2 && number.charAt(digit) - '0' == 0 && name.length() > 0) name = String.join("", "and", name);
					} else {
						if((number.length() > 0 && number.charAt(digit) - '0' != 0) || number.length() == 1) {
							name = String.join("", NumberNames.numberNames[number.charAt(digit) - '0'], name);
						}
						if(digit + 1 < number.length()) {
							name = String.join("", NumberNames.tensNames[number.charAt(digit + 1) - '0'], name);
							if(number.length() > 2 && number.charAt(digit) - '0' == 0 && name.length() > 0) name = String.join("", "and", name);
						}
					}
					digit++;
					break;
				case 2:
					if(number.charAt(digit) - '0' > 0) {
						if(number.charAt(digit-1) - '0' != 0 && number.charAt(digit-2) - '0' != 0) {
							name = String.join("", "and", name);
						} else if(nonZero && name.length() > 0) name = String.join("", "and", name);
						
						name = String.join("", NumberNames.numberNames[number.charAt(digit) - '0'] + NumberNames.commaNames[0], name);
					}
					nonZero = false;
					break;
				}
			}
			sum += name.length();
		}
		return sum;
	}
	
	private static class NumberNames {
		// can just index into the number names with the literal number value
		private final static String [] numberNames = {"zero",
										 			  "one",
										 			  "two",
										 			  "three",
										 			  "four",
										 			  "five",
										 			  "six",
										 			  "seven",
										 			  "eight",
										 			  "nine",
										 			  "ten",
										 			  "eleven",
										 			  "twelve",
										 			  "thirteen",
										 			  "fourteen",
										 			  "fifteen",
										 			  "sixteen",
										 			  "seventeen",
										 			  "eighteen",
										 			  "nineteen"};
		private final static String [] tensNames = {"", // taken care of by number names
									   			    "", // taken care of by number names
									   			    "twenty",
									   			    "thirty",
									   			    "forty",
									   			    "fifty",
									   			    "sixty",
									   			    "seventy",
									   			    "eighty",
									   			    "ninety"};
		private final static String [] commaNames = {"hundred",
													 "thousand",
													 "million",
													 "billion",
													 "trillion",
													 "quadrillion",
													 "quintillion",
													 "sextillion",
													 "septillion",
													 "octillion",
													 "nontillion",
													 "decillion"};
	}
}

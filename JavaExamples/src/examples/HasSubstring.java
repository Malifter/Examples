package examples;

public class HasSubstring {
	
	public static void main(String args[]) {
		/*if(args.length != 2) {
			System.err.println("Input arguments must contain two strings: (1) substringToFind, (2) stringToCheck.");
		} else {
			String x = args[0];
			String y = args[1];
			System.out.println("String " + "\"" + x + "\"" + (hasSubstringComplete(x, y) ? " IS " : " IS NOT ")  + "a substring of \"" + y + "\".");
		}*/
		
		String x = "test";
		String y = "This does not contain any words that match t e s t tiest taste etc t";
		System.out.println("String " + "\"" + x + "\""+ (hasSubstringComplete(x, y) ? " IS " : " IS NOT ") + "a substring of \"" + y + "\".");
	}
	
	public static boolean hasSubstringSimple(String x, String y) {
		return y.contains(x);
	}
	
	public static boolean hasSubstringComplete(String x, String y) {
		for(int i = 0; i < y.length(); i++) {
			boolean matching = true;
			for(int j = 0; j < x.length(); j++) {
				if(i+j < y.length() && y.charAt(i+j) != x.charAt(j)) {
					matching = false;
					break;
				}
			}
			if(matching) return true;
		}
		return false;
	}
}

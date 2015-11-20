package examples.google;

public class GoogleSample1 {

	public static void main(String [] args) {
		int x = 12511;
		System.out.println(solution(x));
	}
	
	public static int solution(int X) {
		// x is at most 100,000,000 therefore it can be contained an integer (always less than 2 billion)
		int max = 0;
		String number = Integer.toString(X);
		for(int i = 0; i < number.length(); i++) {
			String numToTestString = number.substring(0,i+1) + number.charAt(i) + number.substring(i+1, number.length());
			int numToTestInt = Integer.parseInt(numToTestString);
			if(numToTestInt > max) max = numToTestInt;
		}
		return max;
	}
}

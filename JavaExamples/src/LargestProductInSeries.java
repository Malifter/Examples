
public class LargestProductInSeries {

	public static void main(String [] args) {
		String number = "73167176531330624919225119674426574742355349194934"
				+ "96983520312774506326239578318016984801869478851843858615"
				+ "60789112949495459501737958331952853208805511125406987471"
				+ "58523863050715693290963295227443043557668966489504452445"
				+ "23161731856403098711121722383113622298934233803081353362"
				+ "76614282806444486645238749303589072962904915604407723907"
				+ "13810515859307960866701724271218839987979087922749219016"
				+ "99720888093776657273330010533678812202354218097512545405"
				+ "94752243525849077116705560136048395864467063244157221553"
				+ "97536978179778461740649551492908625693219784686224828397"
				+ "22413756570560574902614079729686524145351004748216637048"
				+ "44031998900088952434506585412275886668811642717147992444"
				+ "29282308634656748139191231628245861786645835912456652947"
				+ "65456828489128831426076900422421902267105562632111110937"
				+ "05442175069416589604080719840385096245544436298123098787"
				+ "99272442849091888458015616609791913387549920052406368991"
				+ "25607176060588611646710940507754100225698315520005593572"
				+ "972571636269561882670428252483600823257530420752963450";
		int n = 13;
		System.out.println("series = " + number);
		System.out.println("The " + n + " adjacent digits in the " + number.length()
			+ "-digit number that have the greatest poduct are: " + findLargestProductInSeries(n, number));
	}
	
	public static Product findLargestProductInSeries(int n, String number) {
		Product maxProduct = new Product();
		for(int i = 0; i <= number.length() - n + 1; i++) {
			long product = 1; // the end value overflowed the original integer used
			int [] values = new int [n];
			for(int j = 0; j < n; j++) {
				values[j] = number.charAt(i+j) - '0'; // shift ASCII to Literal
				product *= values[j];
				if(values[j] == 0) break;
			}
			if(product > maxProduct.product) {
				maxProduct.product = product;
				maxProduct.values = values;
			}
		}
		return maxProduct;
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

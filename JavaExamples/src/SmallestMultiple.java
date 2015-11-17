import java.util.ArrayList;
import java.util.Iterator;

public class SmallestMultiple {

	public static void main(String [] args) {
		int n = 20;
		System.out.println("The smallest positive number that is evenly divisible by all of the numbers from 1 to "
				+ n + " is: " + findSmallestMultiple(n));
	}
	
	public static int findSmallestMultiple(int n) {
		ArrayList<Integer> targets = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			targets.add(i+1);
		}
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for(int factor = 2; !targets.isEmpty(); factor++) {
			while(true) {
				boolean repeat = false;
				for(int i = 0; i < targets.size(); i++) {
					int target = targets.get(i);
					if(target % factor == 0) {
						target /= factor;
						targets.set(i, target);
						repeat = true;
					}
				}
				if(repeat) {
					factors.add(factor);
				} else {
					break;
				}
			}
			Iterator<Integer> itr = targets.iterator();
			while(itr.hasNext()) {
				if(itr.next() == 1) itr.remove();
			}
		}
		int multiple = 1;
		for(int factor: factors) multiple *= factor;
		return multiple;
	}
}

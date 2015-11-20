package examples.google;
import java.util.Random;

public class GoogleDemo1 {

	public static void main(String [] args) {
		int [] A = {-1,3,-4,5,1,-6,2,1};
		int [] B = new int [100000];
		{
			Random random = new Random();
			for(int i = 0; i < B.length; i++) {
				int r = (random.nextInt(Integer.MAX_VALUE) - Integer.MAX_VALUE/2) +
						(random.nextInt(Integer.MAX_VALUE) - Integer.MAX_VALUE/2);
				B[i] = r;
			}
		}
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		long totalSum = 0;
		long [] leftSums = new long [A.length];
		// find sums as we move right and total sum
		for(int i = 0; i < A.length; i++) {
			totalSum += A[i];
			leftSums[i] = totalSum;
		}
		for(int p = 0; p < A.length; p++) {
			long value = A[p];
			long leftSum = p == 0 ? 0 : leftSums[p-1];
			long rightSum = p+1 >= A.length ? 0 : ((totalSum - value) - leftSum);
			if(leftSum == rightSum) return p;
		}
		return -1;
	}
}

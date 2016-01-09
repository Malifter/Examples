package examples.google;

import java.util.Arrays;

// find element in circular sorted list
public class GoogleCoaching1 {
	
	public static void main(String [] args) {
		int[] searchArray = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 24, 30, 31, 33, 1000};
		int[] list1 = {13, 24, 30, 31, 1, 3, 4, 6, 8, 10, 11};
		int[] list2 = {30, 31, 1, 3, 4, 6, 8, 10, 11, 13, 24};
		int[] list3 = {1, 3, 4, 6, 8, 10, 11, 24, 30, 31};
		int[] list4 = {0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0};
		
		for(int search: searchArray) {
			System.out.println("Find: " + search);
			System.out.println("list1: " + Arrays.toString(list1) + " " + solution(list1, search));
			System.out.println("list2: " + Arrays.toString(list2) + " " + solution(list2, search));
			System.out.println("list3: " + Arrays.toString(list3) + " " + solution(list3, search));
			System.out.println("list4: " + Arrays.toString(list4) + " " + solution(list4, search) + "\n");
		}
	}
	
	public static boolean solution(int[] list, int search) {
		if(list.length == 0) return false;
		if(list.length == 1) return list[0] == search;
		return recursiveCircularSearch(list, search, 0, list.length-1);
	}
	
	public static boolean recursiveCircularSearch(int[] list, int search, int low, int high) {
		// Check if we found it
		if(low == high) return (list[low] == search);
		
		// Find midpoint
		int mid = (high+low)/2;
		boolean firstHalfUnsorted = false;
		boolean secondHalfUnsorted = false;
		
		// Check if first half is sorted
		if(list[low] < list[mid]) { // if sorted, do a binary search (if applicable)
			if(list[low] <= search && search <= list[mid]) return recursiveBinarySearch(list, search, low, mid);
			// else not in first half
		} else firstHalfUnsorted = true;
		
		// Check if second half is sorted
		if(list[mid+1] < list[high]) { // if sorted, do a binary search (if applicable)
			if(list[mid+1] <= search && search <= list[high]) return recursiveBinarySearch(list, search, mid+1, high);
			// else not in second half
		} else secondHalfUnsorted = true;
		
		// If any are not sorted, we need redo this check on that half.
		if(firstHalfUnsorted && secondHalfUnsorted) {
			return (recursiveCircularSearch(list, search, low, mid) ||
					recursiveCircularSearch(list, search, mid+1, high));
		}
		else if(firstHalfUnsorted) return recursiveCircularSearch(list, search, low, mid);
		else if(secondHalfUnsorted) return recursiveCircularSearch(list, search, mid+1, high);
		else return false;
	}
	
	public static boolean recursiveBinarySearch(int[] list, int search, int low, int high) {
		// Check if can't search further
		if(low == high) return (list[low] == search);
		
		// Find midpoint
		int mid = (high+low)/2;
		// Check if we found it
		if(list[mid] == search) return true;
		else if(search < list[mid]) {
			return recursiveBinarySearch(list, search, low, mid);
		} else {
			return recursiveBinarySearch(list, search, mid+1, high);
		}
	}
}

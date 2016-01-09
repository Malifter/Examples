package examples;

import java.util.Arrays;

import hashstructures.HashTableSeparateChaining;

public class HashExamples {
	
	public static void main(String [] args) {
		HashTableSeparateChaining<String, Integer> htsc = new HashTableSeparateChaining<String, Integer>();
		// before inserting
		System.out.println("Before Inserting");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		htsc.put("a", 0);
		htsc.put("b", 1);
		htsc.put("c", 2);
		htsc.put("d", 3);
		htsc.put("e", 2);
		htsc.put("f", 1);
		htsc.put("g", 0);
		htsc.put("h", 4);
		htsc.put("h", 5);
		htsc.put("i", 6);
		htsc.put("j", 7);
		htsc.put("k", 8);
		htsc.put("l", 9);
		// before grow resize
		System.out.println("Before Grow");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		htsc.put("m", 20);
		// after grow resize
		System.out.println("After Grow");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		htsc.put("n", 15);
		htsc.put("a", 11);
		htsc.put("b", 12);
		htsc.put("c", 13);
		htsc.put("d", 14);
		htsc.put("p", 16);
		htsc.put("q", 17);
		htsc.put("r", 18);
		htsc.put("s", 19);
		htsc.put("o", 21);
		// finished inserting
		System.out.println("Finished Inserting & Before Removing");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		htsc.remove("b");
		htsc.remove("d");
		htsc.remove("f");
		htsc.remove("h");
		htsc.remove("j");
		htsc.remove("l");
		htsc.remove("n");
		htsc.remove("p");
		htsc.remove("r");
		htsc.remove("k");
		htsc.remove("o");
		// before shrink resize
		System.out.println("Before Shrink");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		htsc.remove("e");
		// after shrink resize
		System.out.println("After Shrink");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		htsc.clear();
		// finished removing
		System.out.println("Finished Removing");
		System.out.println(Arrays.toString(htsc.keySet().toArray()));
		System.out.println(htsc.toFullString());
		
		// TODO: Use the p022_names file to do a massive insert and remove in random order.
	}
}

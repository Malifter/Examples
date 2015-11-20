package examples;

import java.lang.reflect.Array;
import java.util.Arrays;

import linkedlist.SinglyUnsortedLinkedList;
import linkedlist.SinglyUnsortedLinkedList.Node;

public class LinkedListExamples {
	
	public static void main(String [] args) {
		testSinglyUnsortedLinkedList();
	}
	
	@SuppressWarnings("unchecked")
	public static void testSinglyUnsortedLinkedList() {
		System.out.println("Singly Unsorted Linked List:");
		SinglyUnsortedLinkedList<Integer> sull = new SinglyUnsortedLinkedList<Integer>();
		System.out.println("Intialize list:");
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		// Insert
		System.out.println("Fill list:");
		Node<Integer> [] nodes = (Node<Integer>[])Array.newInstance(Node.class, 18);
		for(int count = 0; count < 2; count++) {
			for(int i = 0; i < nodes.length/2; i++) {
				if(i % 2 == 0) sull.insert(nodes[i + count*nodes.length/2] = new Node<Integer>(i/2 - i));
				else nodes[i + count*nodes.length/2] = sull.insert(i*3 + i);
				System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
			}
		}
		
		// Search
		System.out.println("Find first instance of: 4");
		System.out.println(sull.findFirst(4));
		
		System.out.println("Find last instance of: 4");
		System.out.println(sull.findLast(4));
		
		System.out.println("Find all instances of: 4");
		System.out.println(Arrays.toString(sull.findAll(4)));
		
		// Next and Previous
		System.out.println("Iterate through next in value order:");
		Node<Integer> node = sull.findFirst(0);
		System.out.print(node);
		while(node != null) System.out.print(" -> " + (node = sull.next(node)));
		System.out.println();
		
		System.out.println("Iterate through previous in value order:");
		node = sull.findFirst(0);
		System.out.print(node);
		while(node != null) System.out.print(" -> " + (node = sull.previous(node)));
		System.out.println();
		
		// Remove
		System.out.println("Remove: " + nodes[9]);
	    sull.remove(nodes[9]);
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Remove: " + nodes[8]);
	    sull.remove(nodes[8]);
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Remove: " + nodes[7]);
	    sull.remove(nodes[7]);
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Remove from head:");
		sull.remove(sull.first());
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Remove from end:");
		sull.remove(sull.last());
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Remove min:");
		sull.remove(sull.min());
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Remove max:");
		sull.remove(sull.max());
		System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		
		System.out.println("Empty list:");
		while(!sull.isEmpty()) {
			sull.remove(sull.first());
			System.out.println("size: " + sull.size() + "\tmin: " + sull.min() + "\tmax: " + sull.max() + "\tlist: " + sull);
		}
	}
}

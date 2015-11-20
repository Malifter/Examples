package linkedlist;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SinglyUnsortedLinkedList<T extends Comparable<? super T>> {
	private Node<T> head;
	private Node<T> min;
	private Node<T> max;
	private int size;
	
	public SinglyUnsortedLinkedList() {
		head = null;
		size = 0;
	}
	
	public SinglyUnsortedLinkedList(Node<T> head) {
		this.head = head;
		min = this.head;
		max = this.head;
		size = 1;
	}
	
	public SinglyUnsortedLinkedList(T value) {
		head = new Node<T>(value);
		min = head;
		max = head;
		size = 1;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	// O(1)
	public Node<T> first() {
		return head;
	}
	
	// O(n) without tail pointer
	public Node<T> last() {
		if(size < 2) return head;
		Node<T> node = head.next;
		while(node.next != null) node = node.next;
		return node;
	}
	
	// O(1) since we update it in insert/remove
	public Node<T> min() {
		return min;
	}
	
	// O(1) since we update it in insert/remove
	public Node<T> max() {
		return max;
	}
	
	// O(1) also can update min/max here
	public void insert(Node<T> node) {
		if(head == null) {
			head = node;
			min = head;
			max = head;
		}
		else { // since it's unsorted, insert at front of list for constant time insertion
			node.next = head;
			head = node;
			if(head.value.compareTo(max.value) > 0) max = head;
			if(head.value.compareTo(min.value) < 0) min = head;
		}
		size++;
	}
	
	public Node<T> insert(T value) {
		Node<T> node = new Node<T>(value);
		insert(node);
		return node;
	}
	
	// O(n) anyways so might as well update min/max here when removing
	public void remove(Node<T> node) {
		if(head == null) return;
		if(head == node) {
			head = head.next;
			size--;
		}
		else {
			Node<T> prev = head;
			Node<T> curr = head.next;
			while(curr != null) {
				if(curr == node) {
					prev.next = curr.next;
					size--;
					break;
				}
				prev = curr;
				curr = curr.next;
			}
		}
		if(node == min) findMin();
		if(node == max) findMax();
	}
	
	// O(n) to find new min
	private void findMin() {
		min = head;
		Node<T> node = head;
		while(node != null) {
			if(node.value.compareTo(min.value) < 0) min = node;
			node = node.next;
		}
	}
	
	// O(n) to find new max
	private void findMax() {
		max = head;
		Node<T> node = head;
		while(node != null) {
			if(node.value.compareTo(max.value) > 0) max = node;
			node = node.next;
		}
	}
	
	// O(n) to find first instance
	public Node<T> findFirst(T value) {
		Node<T> node = head;
		while(node != null) {
			if(node.value.compareTo(value) == 0) {
				return node;
			}
			node = node.next;
		}
		return null;
	}
	
	// O(n) to find last instance
	public Node<T> findLast(T value) {
		Node<T> node = head;
		Node<T> last = null;
		while(node != null) {
			if(node.value.compareTo(value) == 0) {
				last = node;
			}
			node = node.next;
		}
		return last;
	}
	
	// O(n) to find all instances
	@SuppressWarnings("unchecked")
	public Node<T> [] findAll(T value) {
		Node<T> node = head;
		ArrayList<Node<T>> found = new ArrayList<Node<T>>(); // TODO: implement my own dynamically resizing array
		while(node != null) {
			if(node.value.compareTo(value) == 0) {
				found.add(node);
			}
			node = node.next;
		}
		return found.toArray((Node<T>[])Array.newInstance(Node.class, found.size()));
	}
	
	// O(n) to find the next largest value (not order)
	public Node<T> next(Node<T> node) {
		if(head == null || node == max) return null;
		Node<T> search = head;
		T nextValue = max.value;
		Node<T> nextNode = max;
		while(search != null) {
			if(search.value.compareTo(node.value) > 0 && search.value.compareTo(nextValue) < 0) {
				nextValue = search.value;
				nextNode = search;
			}
			search = search.next;
		}
		return nextNode;
	}
	
	// O(n) to find the previous smaller value (not order)
	public Node<T> previous(Node<T> node) {
		if(head == null || node == min) return null;
		Node<T> search = head;
		T prevValue = min.value;
		Node<T> prevNode = min;
		while(search != null) {
			if(search.value.compareTo(node.value) < 0 && search.value.compareTo(prevValue) > 0) {
				prevValue = search.value;
				prevNode = search;
			}
			search = search.next;
		}
		return prevNode;
	}
	
	public String toString() {
		if(size == 0) return "[]";
		String out = "[" + head.value;
		Node<T> node = head.next;
		while(node != null) {
			out += ", " + node.value;
			node = node.next;
		}
		return out += "]";
	}
	
	public static class Node<T extends Comparable<? super T>> {
		private Node<T> next = null;
		private T value;
		
		public Node(T value) {
			this.value = value;
		}
		
		public T getValue() {
			return value;
		}
		
		public String toString() {
			return this.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "=" + value;
			//return value.toString();
		}
	}
}

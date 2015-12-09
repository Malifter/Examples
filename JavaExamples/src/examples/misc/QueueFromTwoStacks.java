package examples.misc;

import java.util.Random;
import java.util.Stack;

public class QueueFromTwoStacks<T> {
	public Stack<T> inStack = new Stack<T>();
	public Stack<T> outStack = new Stack<T>();

	public static void main(String [] args) {
		QueueFromTwoStacks<Integer> queue = new QueueFromTwoStacks<Integer>();
		int [] input = {37, 5, 16, 25, 32, 8, 9, 0, 100, 4};
		
		System.out.println("Dequeueing: " + queue.dequeue());
		
		Random r = new Random();
		for(int i = 0; i < input.length; i++) {
			int index = r.nextInt(input.length);
			System.out.println("Enqueueing: " + input[index]);
			queue.enqueue(input[index]);
			if(i % 2 == 0) {
				System.out.println("Dequeueing: " + queue.dequeue());
			}
		}
		System.out.println("Status: " + queue.toString());
		
		for(int val: input) {
			System.out.println("Enqueueing: " + val);
			queue.enqueue(val);
		}
		System.out.println("Status: " + queue.toString());
		
		while(queue.size() > 0) {
			System.out.println("Dequeueing: " + queue.dequeue());
		}
		System.out.println("Status: " + queue.toString());
		
		System.out.println("Dequeueing: " + queue.dequeue());
	}
	
	public int size() {
		return inStack.size() + outStack.size();
	}
	
	public void enqueue(T item) {
		while(outStack.size() > 0) {
			inStack.push(outStack.pop());
		}
		inStack.push(item);
	}
	
	public T dequeue() {
		if(size() == 0) return null;
		T item = null;
		while(inStack.size() > 0) {
			outStack.push(inStack.pop());
		}
		if(outStack.size() > 0) {
			item = outStack.pop();
		}
		return item;
	}

	public String toString() {
		String out = "[";
		if(size() > 0) {
			while(outStack.size() > 0) {
				inStack.push(outStack.pop());
			}
			out += inStack.elementAt(0);
			for(int i = 1; i < inStack.size(); i++) {
				out += ", " + inStack.elementAt(i);
			}
		}
		out += "]";
		return out;
	}
}

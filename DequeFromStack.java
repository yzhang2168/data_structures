package data_structures;

import java.util.Deque;
import java.util.LinkedList;

/***
 * implement a deque using 2 stacks
 * addLeft(), addRight(): O(1)
 * removeLeft(), removeRight(): if left/right stack is not empty, O(1), else O(n)
 * amortized time for remove()
 * ] s1 s2 [1 2 3 4 5 6 7 8
 * l.remove	2n + 1
 * r.remove 2(n - 1) + 1
 * l.remove 2(n - 2) + 1
 * ...
 * amortized time = ( 2 * (n + n - 1 + n - 2 +... + 1) + n ) / n = ( n(n - 1) + n) / n = n
 * */
public class DequeFromStack {
	private Deque<Integer> s1; //left part of deque
	private Deque<Integer> s2; //right part of deque
	// ...] s1 s2 [...
	
	public DequeFromStack() {
		s1 = new LinkedList<>();
		s2 = new LinkedList<>();
	}
	
	public void addLeft(Integer value) {
		s1.push(value);
	}

	public void addRight(Integer value) {
		s2.push(value);		
	}

	public Integer removeLeft() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return null;
		} else if (s1.isEmpty()) {
			while (!s2.isEmpty()) {
				s1.push(s2.pop());				
			}
		} 
		return s1.pop();
	}

	public Integer removeRight() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return null;
		} else if (s2.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.push(s1.pop());				
			}
		} 
		return s2.pop();
	}
	
	public static void main(String[] args) {
		DequeFromStack deque = new DequeFromStack();
		deque.addRight(1);
		deque.addRight(2);
		deque.addRight(3);
		deque.addRight(4);
		//System.out.println(deque);
		deque.addLeft(5);
		deque.addLeft(6);
		//System.out.println(deque);
		deque.removeLeft();
		deque.removeLeft();
		deque.removeLeft();
		deque.removeLeft();
	}
}

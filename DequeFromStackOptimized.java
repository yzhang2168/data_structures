package data_structures;


import java.util.Deque;
import java.util.LinkedList;

/***
 * implement a deque using 2 stacks
 * addLeft(), addRight(): O(1)
 * removeLeft(), removeRight(): if left/right stack is not empty, O(1), else O(n)
 * amortized time for remove() optimized to O(1)
 * ] s1 s2 [1 2 3 4 5 6 7 8
 * balance left and right
 * 1. move n/2 elements from s2 --> s3
 * 2. move remaining n/2 elements from s2 to s1
 * 3. move n/2 elements s3 --> s2 to reverse order
 * O(3n) for balancing and this allows n/2 pop() at O(1) in worst case
 * amortized time = ( 3n + n/2 * 1 ) / n/2 = O(7) = O(1)
 * */
public class DequeFromStackOptimized {
	private Deque<Integer> s1; //left part of deque
	private Deque<Integer> s2; //right part of deque
	private Deque<Integer> s3; //buffer
	// ...] s1 s2 [...
	
	public DequeFromStackOptimized() {
		s1 = new LinkedList<>();
		s2 = new LinkedList<>();
		s3 = new LinkedList<>();
	}
	
	public void addLeft(Integer value) {
		s1.addLast(value);
	}

	public void addRight(Integer value) {
		s2.addLast(value);		
	}

	public Integer removeLeft() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return null;
		} else if (s1.isEmpty()) {
			for (int i = 0; i <= s2.size() / 2; i++) {
				s3.addLast(s2.removeLast());				
			}
			while (!s2.isEmpty()) {
				s1.addLast(s2.removeLast());
			}
			while (!s3.isEmpty()) {
				s2.addLast(s3.removeLast());				
			}
		} 
		return s1.removeLast();
	}

	public Integer removeRight() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return null;
		} else if (s2.isEmpty()) {
			for (int i = 0; i <= s1.size() / 2; i++) {
				s3.addLast(s1.removeLast());				
			}
			while (!s1.isEmpty()) {
				s2.addLast(s1.removeLast());
			}
			while (!s3.isEmpty()) {
				s1.addLast(s3.removeLast());				
			}
		} 
		return s2.removeLast();
	}
	
	public static void main(String[] args) {
		DequeFromStackOptimized deque = new DequeFromStackOptimized();
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
		deque.removeLeft();
	}
}	
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
		s1.push(value);
	}

	public void addRight(Integer value) {
		s2.push(value);		
	}

	public Integer removeLeft() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return null;
		} else if (s1.isEmpty()) {
			for (int i = 0; i <= s2.size() / 2; i++) {
				s3.push(s2.pop());				
			}
			while (!s2.isEmpty()) {
				s1.push(s2.pop());
			}
			while (!s3.isEmpty()) {
				s2.push(s3.pop());				
			}
		} 
		return s1.pop();
	}

	public Integer removeRight() {
		if (s1.isEmpty() && s2.isEmpty()) {
			return null;
		} else if (s2.isEmpty()) {
			for (int i = 0; i <= s1.size() / 2; i++) {
				s3.push(s1.pop());				
			}
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			while (!s3.isEmpty()) {
				s1.push(s3.pop());				
			}
		} 
		return s2.pop();
	}
	
	public static void main(String[] args) {
		DequeFromStackOptimized deque = new DequeFromStackOptimized();
		deque.addRight(1);
		deque.addRight(2);
		deque.addRight(3);
		deque.addRight(4);
		//s2 [1 2 3 4 --> head
		deque.addLeft(5);
		deque.addLeft(6);
		// head <-- 6 5] s1 
		deque.removeLeft();
		deque.removeLeft();
		// ] s1 s2 [1 2 3 4 --> head 
		deque.removeLeft();
		// head <-- 1 2] s1 s2 [3 4 --> head
		deque.removeLeft();
		deque.removeLeft();
	}
}	
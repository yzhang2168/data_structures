package data_structures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * implement a queue using built-in stack
 * time: offer(): O(1)
 * 		 peek(), poll(): O(1) amortized, O(n) worst case  
 * space: a data structure that holds data
 *		  concerned with time for methods, not for data storage space
 *		  not counting data storage in heap: two stacks expand or shrink as # elements changes
 * 		  no extra space in heap 		  
 * */
public class QueueFromStacks {
	// fields
	private Deque<Integer> _in; // for writing values into queue
	private Deque<Integer> _out; // for reading/removing values from queue
	
	// constructor
	public QueueFromStacks() {
		_in = new LinkedList<>();
		_out = new LinkedList<>();
	}
	
	// methods
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return _in.size() + _out.size();
	}
	
	public void offer(final int value) {
		_in.offerFirst(value);
	}
	
	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		
		if (!_out.isEmpty()) {
			return _out.pollFirst();
		} else {
			shuffle();
			return _out.pollFirst();
		}
	}
	
	private void shuffle() {
		if (!_out.isEmpty() || isEmpty() || _in.isEmpty()) {
			System.out.println("This should not happen!");
		}
		if (_out.isEmpty()) {
			while (!_in.isEmpty()) {
				_out.offerFirst(_in.pollFirst());
			}
		}
	}
	
	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		
		if (!_out.isEmpty()) {
			return _out.peekFirst();
		} else {
			shuffle();
			return _out.peekFirst();
		}
	}
	
	public static void main(String[] args) {
		QueueFromStacks q = new QueueFromStacks();
		System.out.println(q.isEmpty());
		q.offer(1);
		q.offer(2);
		q.offer(3);
		System.out.println(q.isEmpty());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.poll());
	}
}

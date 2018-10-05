package data_structures;

import java.util.Queue;
import java.util.LinkedList;

/**
 * implement a stack using one queue with the q.size() option
 * time: push(): O(1)
 * 		 peek(), pop(): O(n)  
 * */
public class StackFromQueue2 {
	private Queue<Integer> _queue;
	
	public StackFromQueue2() {
		_queue = new LinkedList<Integer>();
	}
	
	public boolean isEmpty() {
		return _queue.isEmpty();
	}
	
	public void push(final int value) {
		_queue.offer(value);
	}
	
	/* q [1 2 3 4
	   pop()
	   poll size() -1 elements and offer them to the tail 
	   q [4 1 2 3
	   poll the nth element
	   q [1 2 3 
	*/
	public Integer pop() {
		if (isEmpty()) {
			return null;
		}
		for (int i = 0; i < _queue.size() - 1; i++) {
			_queue.offer(_queue.poll());
		}
		return _queue.poll();
	}
	
	/* q [1 2 3 4
	   peek()
	   q [4 1 2 3
	   poll 4 and offer it to the tail
	   q [1 2 3 4	   
	*/
	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		for (int i = 0; i < _queue.size() - 1; i++) {
			_queue.offer(_queue.poll());
		}
		int result = _queue.poll();
		_queue.offer(result);
		return result;
	}
	
	public static void main(String[] args) {
		StackFromQueue2 s = new StackFromQueue2();
		System.out.println(s.isEmpty());
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.pop());
		s.push(5);
		s.push(6);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.peek());

	}
}	
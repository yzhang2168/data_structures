package data_structures;

import java.util.Queue;
import java.util.LinkedList;

/**
 * implement a stack using Java Queue without using q.size()
 * time: push(): O(1)
 * 		 peek(), pop(): O(1) amortized, O(n) worst case  
 * */
public class StackFromQueue {
	private Queue<Integer> _in; // for push()
	private Queue<Integer> _out; // for pop(), peek()
	
	public StackFromQueue() {
		_in = new LinkedList<Integer>();
		_out = new LinkedList<Integer>();
	}
	
	public boolean isEmpty() {
		return _in.isEmpty() && _out.isEmpty();
	}
	
	public void push(final int value) {
		_in.offer(value);
	}
	
	public Integer pop() {
		if (isEmpty()) {
			return null;
		} else {
			if (_in.isEmpty()) { 
				// swap(_in, _out); // WRONG! pass by value, original _in, _out not changed
				Queue<Integer> temp = _in;
				_in = _out;
				_out = temp;
			} 
			Integer temp = null;
			while (!_in.isEmpty()) {
				temp = _in.poll();
				if (_in.peek() != null) {
					_out.offer(temp);
				}
			}
			return temp;
		}
	}
	
	public Integer peek() {
		if (_in.isEmpty() && _out.isEmpty()) {
			return null;
		} else {
			if (_in.isEmpty()) { 
				// swap(_in, _out);
				Queue<Integer> temp = _in;
				_in = _out;
				_out = temp;
			} 
			Integer temp = null;
			while (!_in.isEmpty()) {
				temp = _in.poll();
				_out.offer(temp);
			}
			return temp;
		}
	}
	
	public static void main(String[] args) {
		StackFromQueue s = new StackFromQueue();
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

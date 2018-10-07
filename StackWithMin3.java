package data_structures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * implement min(), pop(), push(), peek() using stack with time O(1)
 * assumption: lots of duplicates in the data
 * goal is to optimize space use
 * */
public class StackWithMin3 {
	private Deque<Integer> _input;
	private Deque<Integer> _min; // curr min
	private Deque<Integer> _firstOccurrenceStackSize; // when curr min is saved, size of input stack
	
	public StackWithMin3() {
		_input = new LinkedList<>();
		_min = new LinkedList<>();
		_firstOccurrenceStackSize = new LinkedList<>();
	}
	
	public boolean isEmpty() {
		return _input.isEmpty();
	}

	public void push(final int value) {
		_input.offerFirst(value);
		if (_min.isEmpty() || _min.peekFirst() > value) {
			_min.offerFirst(value);
			_firstOccurrenceStackSize.offerFirst(_input.size());
		}
	}
	
	public Integer pop() {
		if (_input.isEmpty()) {
			return null;
		}
		if (_firstOccurrenceStackSize.peekFirst() == _input.size()) {
			_min.pollFirst();
			_firstOccurrenceStackSize.pollFirst();
		}
		return _input.pollFirst();
	}
	
	public Integer peek() {
		if (_input.isEmpty()) {
			return null;
		}
		return _input.peekFirst();	
	}
	
	public Integer min() {
		if (_min.isEmpty()) {
			return null;
		}
		return _min.peekFirst();
	}
	
	public static void main(String[] args) {
		StackWithMin3 s = new StackWithMin3();
		s.push(3);
		s.push(1);
		s.push(2);
		s.push(1);
		s.push(0);
		System.out.println(s._input);
		System.out.println(s._min);
		System.out.println(s._firstOccurrenceStackSize);
		System.out.println(s.min());

		System.out.println(s.pop());
		System.out.println(s._input);
		System.out.println(s._min);
		System.out.println(s._firstOccurrenceStackSize);
		System.out.println();

		System.out.println(s.pop());
		System.out.println(s._input);
		System.out.println(s._min);
		System.out.println(s._firstOccurrenceStackSize);
		System.out.println();

		System.out.println(s.pop());		
		System.out.println(s._input);
		System.out.println(s._min);
		System.out.println(s._firstOccurrenceStackSize);
		System.out.println();

		System.out.println(s.pop());
		System.out.println(s._input);
		System.out.println(s._min);
		System.out.println(s._firstOccurrenceStackSize);
		System.out.println();

		System.out.println(s.pop());
		System.out.println(s._input);
		System.out.println(s._min);
		System.out.println(s._firstOccurrenceStackSize);
	}

}

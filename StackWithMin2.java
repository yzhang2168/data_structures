package data_structures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * implement min(), pop(), push(), peek() using stack with time O(1)
 * time:  O(1)
 * space: O(1) per element
 * */
public class StackWithMin2 {
	// _stack for input
	// _minStack for current min in _stack
	// _stack    [3 1 -7 -6 -8
	// _minStack [3 1 -7 -8
	private Deque<Integer> _stack;
	private Deque<Integer> _minStack;
	
	// constructor
	public StackWithMin2() {
		_stack = new LinkedList<Integer>();
		_minStack = new LinkedList<Integer>();
	}
	
	// methods
	public boolean isEmpty() {
		return _stack.isEmpty();
	}
	
	public void push(final int e) {
		_stack.offerFirst(e);
		// when e < or == current min in stack, push e to minstack
		// == to deal with multiple elements == min
		if (_minStack.isEmpty() || e <= _minStack.peekFirst()) {
			_minStack.offerFirst(e);
		}	
	}
	
	public Integer pop() {
		if (_stack.isEmpty()) {
			return null;
		} else if (_stack.peekFirst() == _minStack.peekFirst()) {			
			_minStack.pollFirst();			
		}
		return _stack.pollFirst();
	}
	
	public Integer top() {
		if (_stack.isEmpty()) {
			return null;
		} else {
			return _stack.peekFirst();			
		}
	}

	public Integer min() {
		if (_minStack.isEmpty()) {
			return null;
		} else {
			return _minStack.peekFirst();
		}
	}
	
	
	public static void main(String[] args) {
		StackWithMin2 s = new StackWithMin2();
		s.push(3);
		s.push(1);
		s.push(2);
		s.push(5);
		s.push(0);
		System.out.println(s._stack);
		System.out.println(s._minStack);
		s.pop();
		s.pop();
		System.out.println(s._stack);
		System.out.println(s._minStack);
	}
}

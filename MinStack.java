package data_structures;

import java.util.Deque;
import java.util.LinkedList;

/**
 * implement min(), pop(), push(), peek() using stack with time O(1)
 * */
public class MinStack {
    private Deque<Integer> _stack = null; 
    private Deque<Integer> _minStack = null;
    //private Deque<Integer> _minStack; // by default, sets to null
    
    public MinStack() {
    	_stack = new LinkedList<Integer>(); // new - empty LinkedList
    	_minStack = new LinkedList<Integer>();
    }
    
    public Integer min() {
    	// Java peek() returns null if this deque is empty
    	if (_minStack.isEmpty()) {
    		return null;
    	}
    	return _minStack.peek();
    }
    
    public Integer pop() {
    	// Java throws NoSuchElementException if deque is empty
    	if (_stack.isEmpty()) {
    		return null;
    	}
    	if (_stack.peek() == _minStack.peek()) {
    		_minStack.pop();
    	}
    	return _stack.pop();
    }
    
    public Integer peek() {
    	// Java peek() returns null if this deque is empty
    	if (_stack.isEmpty()) {
    		return null;
    	}
    	return _stack.peek();
    }
    
    public void push(int x) {
    	_stack.push(x);
    	if (_minStack.isEmpty()) {
    		_minStack.push(x);
    	}
    	if (x <= _minStack.peek()) {
    		_minStack.push(x);
    	}
    }
}

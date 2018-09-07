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
    private Deque<Integer> _in = null;
    private Deque<Integer> _out = null;
    
    public QueueFromStacks() {
    	_in = new LinkedList<Integer>();
    	_out = new LinkedList<Integer>();
    }

    // cannot make it static b/c it uses static
    // static is for class, not for instances
    // static can only access static variables, not instance variables
    // _in and _out are not static/class variables
    private void shuffleIfNecessary(){ 
    	if (_out.isEmpty()) {
    		while (!_in.isEmpty()) {
    			_out.push(_in.pop());
    		}
    	}
    }
    
    public void offer(int x) {
    	_in.push(x);
    }
    
    public Integer poll() { //<-------- Integer can return null while int cannot
    	shuffleIfNecessary();
    	if (_out.isEmpty()) {
    		return null;
    	}
    	return _out.pop();
    }
    
    public Integer peek() {
    	shuffleIfNecessary();
    	if (_out.isEmpty()) {
    		return null;
    	}
    	return _out.peek();
    }
    
    public int size() {
    	return _in.size() + _out.size();
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }
}
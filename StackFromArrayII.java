package data_structures;

public class StackFromArrayII {
	// fields
	private int[] array;
	private int first; // first: position to read/pop; first + 1: position to write
	
	// constructor
	public StackFromArrayII(int capacity) {
		array = new int[capacity];
		first = -1;
	}
	
	// methods
	public boolean isEmpty() {
		return first == -1;
	}
	
	public boolean isFull() {
		return first == array.length - 1;
	}
	
	public boolean push(int value) {
		if (first == array.length - 1) {
			return false;
		}
		first++; 
		array[first] = value;
		return true;
	}
	
	public Integer pop() {
		if (first == -1) {
			return null;
		}
		int result = array[first];
		first--;
		return result;
	}
	
	public Integer peek() {
		if (first == -1) {
			return null;
		}
		return array[first];
	}
	
	public static void main(String[] args) {
		StackFromArrayII s = new StackFromArrayII(4);
		util.Util.printArray(s.array);
		System.out.println(s.peek());
		s.push(11);
		s.push(12);
		s.push(13);
		s.push(14);
		util.Util.printArray(s.array);
		System.out.println(s.isFull());
		System.out.println(s.push(15));
		util.Util.printArray(s.array);
		System.out.println(s.peek());
		System.out.println(s.pop());
		s.push(15);
		System.out.println(s.isFull());
		util.Util.printArray(s.array);
		System.out.println(s.pop());
		System.out.println(s.isFull());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());	
		System.out.println(s.isEmpty());
		System.out.println(s.pop());	
		util.Util.printArray(s.array);
	}
}

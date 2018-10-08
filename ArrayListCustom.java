package data_structures;

/** build a custom class similar to Java ArrayList
 * */
public class ArrayListCustom {
	private Integer[] array; // maintained array, capacity is array.length, with possible unused cells 
	private int size; // actually used calls in the maintained array
	
	// Constructs an empty list with an initial capacity of ten - allocates memory for 10 Integers
	public ArrayListCustom() {
		array = new Integer[10];
		size = 0;
	}
	
	// Constructs an empty list with the specified initial capacity.
	public ArrayListCustom(int capacity) {
		array = new Integer[capacity];
		size = 0;
	}
	
	public Integer get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("invalid input");
		}
		return array[index];
	}
	
	public void set(int index, Integer val) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("invalid input");
		}
		array[index] = val;
	}
	
	public void add(int value) {
		//length is lazy calculation, when .length is not called, the value is not updated
		if (array.length == size) {
			Integer[] temp = new Integer[(size + size / 2)]; // {null, null, null}
			// for an empty array, length == 0, array[0] causes NEP
			for (int i = 0; i < size; i++) {
				temp[i] = array[i];
			}
			array = temp;
			System.out.println("array has been expanded 1.5x");
		}
		array[size] = value;
		size++;
		util.Util.printArray(array);
	}

	public void add(int index, int value) {
		// index allowed range: [0...size]
		// index == size, array[size] = value
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("invalid input");
		}
		//length is lazy calculation, when .length is not called, the value is not updated
		//System.out.println("length = " + array.length);
		if (array.length == size) {
			Integer[] temp = new Integer[(size + size / 2)]; // {null, null, null}
			for (int i = 0; i < size; i++) {
				temp[i] = array[i];
			}
			array = temp;
			System.out.println("array has been expanded 1.5x");
			util.Util.printArray(array);
		}
		
		// right shift [index...end] by 1 position
		int pos = size;
		while (pos > index) {
			array[pos] = array[pos - 1];
			pos--;
		}
		size++;
		System.out.println("after shifting to right:");
		util.Util.printArray(array);
		array[index] = value;
		System.out.println("after add(index, value):");
		util.Util.printArray(array);
	}

	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("invalid input");
		}
		for (int i = index; i <= size - 2; i++) {
			array[i] = array[i + 1];
		}
		array[size - 1] = null;
		size--;
		util.Util.printArray(array);
	}
	
	public static void main(String[] args) {
		ArrayListCustom array = new ArrayListCustom();
		//ArrayListExample array = new ArrayListExample(2);
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(1, 0);
		array.add(1, 4);
		array.add(1, 5);
		array.add(1, 6);
		array.add(1, 7);
		array.remove(0);
		array.remove(1);
		array.remove(7);
		array.remove(10);
		//System.out.println(array.get(-1));
		//System.out.println(array.get(4));
		//System.out.println(array.get(10));
	}
}

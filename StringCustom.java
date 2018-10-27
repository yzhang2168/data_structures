package data_structures;

import java.util.HashMap;

public class StringCustom {
	private char[] array;
	private int offset;
	private int length;
	
	public StringCustom() {
		array = new char[10];
		offset = 0;
		length = array.length;
	}
	
	public StringCustom( final String str ) {
		array = str.toCharArray();
		length = array.length;
		offset = 0;
	}
	
	// deep copy
	public StringCustom( final StringCustom str ) {
		length = array.length;
		offset = 0;
		array = new char[array.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = str.array[i];
		}
	}
	
	@Override
	public boolean equals(Object obj) { // Object type in order to overwrite
		if (this == obj) {
			return true;
		}
		
		// check if object in heap is String type
		// ref type and object type are not the same
		if (!(obj instanceof StringCustom)) {
			//if obj == null, returns false
			return false;
		}
		
		// cast Object type ref to String type ref
		// obj.length;// compile error
		StringCustom another = (StringCustom) obj;
		if (this.length != another.length) {
			return false;
		}
		
		for (int i = 0; i < this.offset; i++) {
			if (this.array[this.offset + i] != another.array[another.offset + i]) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		String str = new String(array, offset, length);
		return str.hashCode();
	}
	
	@Override
	public String toString() {
		return new String(array, offset, length);
	}
	
	
	public static void main(String[] args) {
		StringCustom s0 = new StringCustom();
		System.out.println(s0);
		
		StringCustom s1 = new StringCustom("abc");
		StringCustom s2 = new StringCustom("abc");
		
		System.out.println(s1.equals("abc")); // false
		
		System.out.println(s1.equals(null)); // false
		
		System.out.println(s1.equals(s1)); // true
		
		System.out.println(s1.equals(s2)); // true
		
		System.out.println(s1.equals(new StringCustom("abcd"))); // false

		System.out.println(s1.hashCode());
		
		System.out.println(s2.hashCode()); // s1 and s2 should return same hashcode
		
		HashMap<StringCustom, Integer> map = new HashMap<StringCustom, Integer>();
		map.put(s1, 1);
		map.put(s2, 2);
		
		System.out.println(map); // only one entry
	}
}
package data_structures;

import java.util.HashMap;

public class HexToInt {
	
	// 0-9 -> 0-9, a-f/A-F -> 10-15
	public static int hexToInt0(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		} else if (c >= 'a' && c <= 'f') {
			return 10 + c - 'a'; 
		} else if (c >= 'A' && c <= 'F') {
			return 10 + c - 'A'; 
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
	public static Integer hexToInt(char input) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
				'A', 'B', 'C', 'D', 'E', 'F'}; 
		
		for (char c : hex) {
			if (c >= '0' && c <= '9') {
				map.put(c, c - '0');
			} else if (c >= 'a' && c <= 'f') {
				map.put(c, 10 + c - 'a'); 
			} else if (c >= 'A' && c <= 'F') {
				map.put(c, 10 + c - 'A');
			} else {
				map.put(c, Integer.MAX_VALUE);
			}
		}		
		return map.get(input);
	}
	
	public static void main(String[] args) {
		System.out.println(hexToInt0('f'));
		System.out.println(hexToInt('1'));
		System.out.println(hexToInt('a'));
		System.out.println(hexToInt('A'));
		System.out.println(hexToInt('f'));
		System.out.println(hexToInt('F'));
		System.out.println(hexToInt('z'));
		System.out.println(hexToInt('-'));
	}

}

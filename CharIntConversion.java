package data_structures;

public class CharIntConversion {
	
	/**
	 * parse a string representing a non-negative integer
	 * example: "1912" -> 1912
	 * */
	public static int stringToInt(String input) {
		int result = 0;
		String s = input.trim();
		for (int i = 0; i < s.length(); i++) {
			result = result * 10 + s.charAt(i) - '0';
		}
		return result;
	}
	
	/**
	 * a single digit [0-9] to its String representtion
	 * */
	public static String intToString(int digit) {
		if (digit < 0 || digit > 9) {
			return null;
		}
		return Character.toString((char) (digit + '0'));
	}
	
	public static char lowerToUpper(char lower) {
		return (char) (lower - 'a' + 'A');
	}
	
	public static char upperToLower(char upper) {
		return (char) (upper - 'A' + 'a');
	}
	
	public static void main(String[] args) {
		/**
		 * A char is already a number (char's ASCII code), no conversion required.
		 * If you want to convert a digit to the corresponding character, you can simply add '0':
		 * c = i +'0';
		 * The '0' is a character in the ASCll table.
		 * */
		System.out.println(stringToInt("0"));
		
		System.out.println(stringToInt("1912"));
		
		System.out.println(stringToInt("  1912  "));
		
		System.out.println((int) '1'); // char -> ASCII code
		
		System.out.println(1 + '0'); // char -> ASCII code
		
		System.out.println('1' - 0); // char -> ASCII code

		System.out.println('1' + 0); // char -> ASCII code

		System.out.println('1' - '0'); // abs value

		System.out.println((int) 'A'); // ASCII code
		
		System.out.println('A' - 0); // ASCII code
		
		System.out.println((char) 65); //'A'

		System.out.println('A' - '0'); // diff between two ASCII numbers
		
		System.out.println((char) ('A' + 5)); // 'F'

		System.out.println('A' + 5); // char -> ASCII code

		String s = "ABC";
		System.out.println(s.charAt(0) - 0); // 65, char -> ASCII code
		System.out.println(s.charAt(0) - 'A'); // 0
		
		System.out.println(intToString(0));
		System.out.println(intToString(1));
		
		if (intToString(0) instanceof String) {
			System.out.println("It's a string");
		}
		
		System.out.println((char) 127); // not printable
		System.out.println((char) 65); // 'A'
		System.out.println((char) (256 * 256 + 65));// 'A': 2^8 * 2^8 overflow, 17 bits (lower 16 bits are 0) -> take lower 16 bits
		System.out.println(lowerToUpper('a'));
		System.out.println(upperToLower('A'));
	}
}

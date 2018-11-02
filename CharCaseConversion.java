package data_structures;

public class CharCaseConversion {
	public static char lowerToUpper(char lower) {
		return (char) (lower - 'a' + 'A');
	}

	public static char upperToLower(char upper) {
		return (char) (upper - 'A' + 'a');
	}

	public static void main(String[] args) {
		System.out.println((char) 127); // not printable
		System.out.println((char) 65); // 'A'
		System.out.println((char) (256 * 256 + 65));// 'A': 2^8 * 2^8 overflow, 17 bits (lower 16 bits are 0) -> take lower 16 bits
		System.out.println(lowerToUpper('a'));
		System.out.println(upperToLower('A'));
	}

}

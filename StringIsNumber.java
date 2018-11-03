package data_structures;

public class StringIsNumber {

	/**
	 * assumption: string is a valid integer number
	 * corner cases:
	 * 1. string is null or empty
	 * 2. leading/trailing spaces
	 * 3. +/- 
	 * 4. invalid chars: . `! letters; 123abc acceptable
	 * 5. overflow an int
	 * 6. overflow a long
	 * */
	public static int myAtoi(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		
		int n = input.length();
		int i = 0;
		
		// leading spaces
		while (i < n && input.charAt(i) == ' ') {
			i++;
		}
		
		// +/- sign
		boolean positive = true;
		if (input.charAt(i) == '+' || input.charAt(i) == '-') {
			positive = (input.charAt(i) == '+');
			i++;
		}
		
		// int overflow, save in long first
		long sum = 0;
		while (i < n && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
			sum = sum * 10 + (input.charAt(i) - '0');
			if (sum > (long) Integer.MAX_VALUE + 1) { // abs(min) = max + 1
				break;
			}
			i++;
		}
		// while() exit: exhausted input or i is at non-[0-9] char, ignore 
		// 123abc acceptable
		
		// +/-
		sum = positive ? sum : -sum;
		
		if (sum > (long) Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (sum < (long) Integer.MIN_VALUE) {
			return Integer.MIN_VALUE; 
		} else {		
			return (int) sum;
		}	
	}
	
	public static boolean isSimpleNumber(String input) {
		String s = input.trim();
		boolean seenPoint = false;
		int n = s.length();
		if (n == 0) {
			return false;
		}
		
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (c == '.') {
				if (seenPoint) {
					return false;
				} else {
					seenPoint = true;
				}
			} else if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isSciNumber(String input) {
		String s = input.trim();
		int n = s.length();
		if (n == 0) {
			return false;
		}
		
		boolean seenNumber = false;
		boolean seenPoint = false;
		boolean seenE = false;
		boolean seenSignBeforeE = false;
		boolean seenSignAfterE = false;
		boolean seenNumberAfterE = false;
		
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (c == '+' || c == '-') {
				// before E
				if ((seenNumber || seenPoint) && !seenE) {
					return false;
				// after E
				} else if (seenNumberAfterE) {
					return false;
				}
				
				// 2nd +/-
				if (seenSignBeforeE && !seenE) {
					return false;
				} else if (seenSignAfterE) {
					return false;
				}

				if (seenE) {
					seenSignAfterE = true;
				} else {
					seenSignBeforeE = true;
				}
				
			} else if (c >= '0' && c <= '9') {
				seenNumber = true;
				if (seenE) {
					seenNumberAfterE = true;
				}
				
			} else if (c == 'e' || c == 'E') {
				if (seenE) {
					return false;
				} else if (!seenNumber) {
					return false;
				} else {
					seenE = true;
				}
				
			} else if (c == '.') {
				if (seenE || seenPoint) {
					return false;
				} else {
					seenPoint = true;
				}
			
			// unacceptable chars	
			} else { 
				return false;
			}
		}
		
		if (seenE && !seenNumberAfterE) {
			return false;
		}
			
		return seenNumber;
	}
	
	public static void main(String[] args) {
		int i = 123;
		boolean positive = true;
		//System.out.println(0 == positive);
		//System.out.println(i == positive);
		System.out.println(myAtoi("   -12344556"));
		System.out.println(isSciNumber("  "));		// f
		System.out.println(isSciNumber(" a.1. "));	// f
		System.out.println(isSciNumber(" 1.1. "));	// f
		System.out.println(isSciNumber(" 1 "));		// t
		System.out.println(isSciNumber(" 1. "));	// t
		System.out.println(isSciNumber(" 1.1. "));	// f
		System.out.println(isSciNumber(" 1.E1 "));	// t
		System.out.println(isSciNumber(" 1.E "));	// f
		System.out.println(isSciNumber(" 1.E1 "));	// t
		System.out.println(isSciNumber(" E1 "));	// f
		System.out.println(isSciNumber(" 1E1 "));	// t
	}
}

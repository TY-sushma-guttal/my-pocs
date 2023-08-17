package java_basic_programs;

public class BinaryOrNot {

	public static void main(String[] args) {
		Integer input = 123456;
		char[] charArray = input.toString().toCharArray();
		Integer count = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '0' || charArray[i] == '1') {
				count++;
			}

		}
		if (count == charArray.length) {
			System.err.println("true");
		} else {
			System.err.println("false");
		}
	}

}

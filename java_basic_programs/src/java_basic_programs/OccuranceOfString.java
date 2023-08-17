package java_basic_programs;

public class OccuranceOfString {

	public static void main(String[] args) {

		String inputString = "Sushma Shivananda Guttal";

		char a = 'a';

		System.err.println(inputString.length() - inputString.replace(a, ' ').length());
	}
}

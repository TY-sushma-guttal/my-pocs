package java_basic_programs;

public class ReverseEachWord {

	public static void main(String[] args) {
		String input = "Hello World!";
		String[] split = input.split("//s");
		String output = "";

		for (int i = 0; i < split.length; i++) {
			StringBuffer buffer = new StringBuffer(split[i]);
			buffer.reverse();
			output = output + buffer;

		}
		System.err.println(output+" output");
	}

}

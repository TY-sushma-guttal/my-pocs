package sorting;
/* IMPORTANT: Multiple classes and nested static classes are supported */

//uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import for Scanner and other utility classes
import java.util.*;

//Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

public class Test {
	public static void main(String args[]) throws Exception {
		// Sample code to perform I/O:
		// Use either of these methods for input

		// BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter wr = new PrintWriter(System.out);
		String[] menu = br.readLine().split(" ");
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		for (int i = 0; i <= menu.length; i++) {
			String[] items = br.readLine().split(" ");
			arrayList.add(items);
		}

		ArrayList<Integer> counts = new ArrayList<Integer>();
		for (int i = 0; i <= arrayList.size() - 1; i++) {
			int j = 0;
			while (j < Integer.parseInt(menu[1])) {

				String[] strings = arrayList.get(i);
				System.err.println(strings[j]);
				j++;

			}

		}

		// Write your code here

	}
}

package java_basic_programs;

import java.util.ArrayList;
import java.util.List;

public class ArmStrongNumber {
	public static void main(String[] args) {
		Integer input = 153;
		Integer reminder = 0, sum = 0, a = input;
		List<Integer> list = new ArrayList<>();
		while (input > 0) {
			reminder = input % 10;
			input = input / 10;
			list.add(reminder);

		}
		for (Integer integer : list) {
			sum = sum + integer * integer * integer;
		}
		if (sum.equals(a))
			System.err.println("True");
		else {
			System.err.println("false");
		}
	}

}

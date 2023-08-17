package java_basic_programs;

public class DuplicateElementsInArray {

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 3, 4 };
		int j = 1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == array[j]) {
				System.err.println(array[i]);
			} else {

				j++;
			}
		}

	}

}

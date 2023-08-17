package java_basic_programs;

import java.util.Scanner;

public class ArrayInitialization {
	
	public static Integer[] initializeArray() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter array size");
		Integer size = scanner.nextInt();
		Integer[] inputArray = new Integer[size];
		System.out.println("Enter array elements");
		for (int i = 0; i < inputArray.length; i++) {
			inputArray[i] = scanner.nextInt();
		}
		scanner.close();
		return inputArray;
	}

}

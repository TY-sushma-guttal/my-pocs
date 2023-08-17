package java_basic_programs;

import java.util.Arrays;

public class ReverseAnArray {
	
	public static void main(String[] args) {
		
		Integer[] inputArray = ArrayInitialization.initializeArray();
		
		Integer temp = 0;
		for (int i = 0; i < inputArray.length/2; i++) {
			temp = inputArray[i];
			inputArray[i] = inputArray[inputArray.length-i-1];
			inputArray[inputArray.length-i-1] = temp;
		}
		
		System.err.println("Output array is -> "+Arrays.asList(inputArray));
	}

}

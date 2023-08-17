package java_basic_programs;

import java.util.ArrayList;
import java.util.Scanner;

public class SumOfPairOfElementsEqualsToGivenNumber {
	
	public static void main(String[] args) {
		
		//will not work for all scenario
		Integer[] inputArray = ArrayInitialization.initializeArray();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number");
		ArrayList<Integer[]> output = new ArrayList<>();
		int i = 0; int j = inputArray.length-1;
		while(i<j) {
			if(inputArray[i]+inputArray[j] == 4) {
				i++; j--;
				output.add(new Integer[] {inputArray[i], inputArray[j]});
			}else if(inputArray[i]+inputArray[j]<4) {
				i++;
				
			}else if(inputArray[i]+inputArray[j]>4) {
				j--;
			}
		}
		output.stream().forEach(a -> {for (int k = 0; k < a.length; k++) {
			System.err.print(a[k]);
		}});
		scanner.close();
	}

}

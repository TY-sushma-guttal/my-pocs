package java_basic_programs;

import java.util.Arrays;

public class EqualityOfTwoArrays {
	public static void main(String[] args) {
		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {1,2,3,4,5};
		boolean flag = false;
		if(arr1.length==arr2.length) {
			for (int i = 0; i < arr1.length; i++) {
				if(arr1[i]!=arr2[i]) {
					flag = true;
				}
			}
			
		}
		else {
			flag = false;
		}
		if(flag) {
			System.err.println("Not equal");
		}else {
			System.err.println("Equal");
		}
		
		//Using equals method, use deepEquals for multidimensional array
		boolean equals = Arrays.equals(arr1, arr2);
		System.err.println(equals);
	}

}

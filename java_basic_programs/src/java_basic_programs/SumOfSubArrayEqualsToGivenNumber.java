package java_basic_programs;

public class SumOfSubArrayEqualsToGivenNumber {
	
	public static void main(String[] args) {
		Integer[] array = {1,2,3,4,5,6,7,8,9};
		Integer target = 6;
		int start =0;
		int sum = array[0];
		for (int i = 1; i < array.length; i++) {
			sum = sum + array[i];
			while(sum>target && start<i-1) {
				sum = sum-array[start];
				start++;
			}
			
			
			if(sum==target) {
				for(int j=start; j<=i; j++) {
					System.err.println(array[j]);
				}
			}
			
		}
	}

}

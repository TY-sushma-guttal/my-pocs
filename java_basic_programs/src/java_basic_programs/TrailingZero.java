package java_basic_programs;

public class TrailingZero {
	public static void main(String[] args) {
		int[] array = {1,2,3,0,4,5,0};
		int start = 0, end = array.length-1;
		for (int i = 0; i < array.length; i++) {
			if(array[i]==0) {
				array[start]=0;
				start++;
			}
//			else {
//				array[end] = array[i];
//				end--;
//			}
		}
		for (int i : array) {
			System.err.println(i);
		}
	}

}

package java_basic_programs;

import java.util.ArrayList;

public class DisariumNumber {
	
	public static void main(String[] args) {
		Integer input = 175;
		Integer a = input;
		ArrayList<Integer> list = new ArrayList<>();
		Integer sum = 0;
  		while(input>0) {
			list.add(input%10);
			input = input/10;
		}
  		
  		Integer len = list.size();
  		for (Integer integer : list) {
			 sum = sum + (int)Math.pow(integer, len);
			 len--;
		}
  		
		 if(a.equals(sum))
			 System.err.println("Yes");
		 else
			 System.err.println("No");
		
	}

}

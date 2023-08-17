package java_basic_programs;

public class LargestNumber {
	public static void main(String[] args) {
		Integer input = 145;
		Integer target = 4;
		
		while(input>0) {
			if(input.toString().contains(target.toString())){
				input--;
			}
			else {
				System.err.println(input);
				break;
			}
		}
	}

}

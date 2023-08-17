package java_basic_programs;

public class FibonacciNumbers {

	public static void main(String[] args) {
		Integer input = 10;
		int first = 0, second = 1, next, i=0;
		while(i<input) {
			next = first+second;
			first = second;
			second = next;
			i++;
			System.err.println(next);
		}

	}

}

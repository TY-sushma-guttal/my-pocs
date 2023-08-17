package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TramRide {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter wr = new PrintWriter(System.out);
		int N = Integer.parseInt(br.readLine().trim());
		int start = Integer.parseInt(br.readLine().trim());
		int finish = Integer.parseInt(br.readLine().trim());
		String[] arr_Ticket_cost = br.readLine().split(" ");
		int[] Ticket_cost = new int[N];
		for (int i_Ticket_cost = 0; i_Ticket_cost < arr_Ticket_cost.length; i_Ticket_cost++) {
			Ticket_cost[i_Ticket_cost] = Integer.parseInt(arr_Ticket_cost[i_Ticket_cost]);
		}

		long out_ = solve(N, start, finish, Ticket_cost);
		System.out.println(out_);
ArrayList<E>
		wr.close();
		br.close();
	}

	static long solve(int N, int start, int finish, int[] Ticket_cost) {
		// Write your code here
		long result = 0;
		int rotateSum = 0;
		int antiRotateSum = Ticket_cost[start];
		int end = Ticket_cost.length - 1;
		while (start < finish) {
			rotateSum = rotateSum + Ticket_cost[start - 1];
			start++;
		}

		while (finish <= end) {
			antiRotateSum = antiRotateSum + Ticket_cost[finish];
			finish++;
		}

		if (rotateSum < antiRotateSum)
			return rotateSum;
		else
			return antiRotateSum;

	}
}

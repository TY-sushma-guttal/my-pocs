package java_basic_programs;

import java.util.HashMap;

public class OccurrenceOfEachLetter {
	public static void main(String[] args) {
		String input = "abcgdaa";
		HashMap<Character, Integer> map = new HashMap<>();
		char[] array = input.toCharArray();
		for (char a : array) {
			if (map.containsKey(a)) {
				map.put(a, map.get(a) + 1);
			} else {
				map.put(a, 1);
			}
		}
		System.err.println(map + " map ");

	}

}

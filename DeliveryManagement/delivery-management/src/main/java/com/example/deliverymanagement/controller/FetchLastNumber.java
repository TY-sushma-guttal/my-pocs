package com.example.deliverymanagement.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchLastNumber {

	public static void main(String[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
		hashMap.put("key", null);
		String string = hashMap.get("keys");
		System.out.println(string);
//		List<String> employeeIds = Arrays.asList("T1YC11", "TYC012", "TYC03", "TYP01", "TTYC01");
//		System.out.println("T1YC11".substring("T1YC1".length() + 1));
//		System.err.println(fetchLastNumber("T1YC1", employeeIds));
//	System.err.println(largestNumber(fetchLastNumber("TYC", employeeIds)));
	}

	public static List<Integer> fetchLastNumber(String value, List<String> employeeIds) {

		
		return employeeIds.stream()
				.filter(id -> (id.length() >= value.length() + 1)
						&& id.substring(0, value.length()).equalsIgnoreCase(value)
						&& !(Character.isLetter(id.substring(value.length()).charAt(0))))
				.map(id -> Integer.parseInt(id.substring(value.length()))).collect(Collectors.toList());
	}

	public static Integer largestNumber(List<Integer> numbers) {
		return numbers.stream().reduce((x, y) -> x > y ? x : y).orElse(0);
	}

}

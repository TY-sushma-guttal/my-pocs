package com.example.deliverymanagement.controller;

import java.util.regex.Pattern;

public class RegexMatch {

	public static void main(String[] args) {
		String regex = "^(Mr\\.?|Ms\\.?|Miss\\.?|MR\\.?|MS\\.?|MISS\\.?)\\s\\w{2,}$";
		String value = "MR.";

		System.out.println(Pattern.matches(regex, value) ? "Matches" : "Does not match");
	}
}

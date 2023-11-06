package com.trung.qotd.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenService {
	private static final int MAX_LENGTH = 86;

	private static boolean usernameHasSpecialCharacter(String username) {
		Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(username);
		
		return matcher.find();
	}
	private static boolean usernameTooLong(String username) {
		return username.length() > MAX_LENGTH;
	}

	public static boolean validUsername(String username) {
		if (usernameTooLong(username)) {
			return false;
		}
		
		if (usernameHasSpecialCharacter(username)) {
			return false;
		}
		
		return true;
	}
}

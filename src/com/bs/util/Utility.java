package com.bs.util;

public class Utility {
	/**
	 * This method processes the given String, removes any character which aren't from a to z.
	 * @param phrase
	 * @return
	 */
	public static String processPhrase(String phrase) {
		String processedPhrase = "";
		phrase = phrase.toLowerCase();
		for (int count=0; count<phrase.length(); count++) {
			if (phrase.charAt(count) >= 97 && phrase.charAt(count) <= 122) {
				processedPhrase += String.valueOf(phrase.charAt(count));
			}
		}
		return processedPhrase;
	}
}

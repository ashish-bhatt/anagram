package com.bs.anagram;

import java.util.List;

import com.bs.datastruct.CustomDS;

public interface IAnagramFinder {
	/**
	 * This method returns complete list of probable anagrams.
	 * @param phrase
	 * @return
	 */
	public List<CustomDS> findProbableAnagrams(String phrase);
	
	/**
	 * This method returns list of list of anagrams for the given phrase.
	 * @param phrase
	 * @return
	 */
	public List<List<CustomDS>> findSetOfAnagrams(String phrase);
	
	/**
	 * This method process the phrase and remove all special chars and convert it into lowercase.
	 * @param phrase
	 * @return
	 */
	public String processPhrase(String phrase);
}

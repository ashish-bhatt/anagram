package com.bs.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bs.constants.Constants;
import com.bs.datastruct.CustomDS;
import com.bs.dictionary.IDictionaryProcessor;
import com.bs.util.Utility;

public class AnagramFinderImpl implements IAnagramFinder {
	private Map<String, CustomDS> dictionary = new HashMap<String, CustomDS>();
	private IDictionaryProcessor dictionaryProcessor = null;
	private List<CustomDS> probableAnagrams = null;
	private CustomDS phrase = null;
	
	/**
	 * Constructor
	 * @param dictionaryProcessor
	 */
	public AnagramFinderImpl(IDictionaryProcessor dictionaryProcessor) {
		this.dictionaryProcessor = dictionaryProcessor;
		this.dictionary = dictionaryProcessor.processDictionary();
	}

	@Override
	public List<CustomDS> findProbableAnagrams(String phrase) {
		probableAnagrams = new ArrayList<CustomDS>();
		//Processing the phrase
		phrase = processPhrase(phrase);
		//Creating the custom structure for the phrase
		this.phrase = new CustomDS(phrase);
		//Looping through the dictionary to figure out the set of probable anagrams
		for (String word : dictionary.keySet()) {
			CustomDS wordDS = dictionary.get(word);
			if(this.phrase.isSubtractable(wordDS) != null && this.phrase.getPhrase().length()-word.length()>=Constants.WORDS_MIN_LEN) {
				probableAnagrams.add(wordDS);
			}
		}
		return probableAnagrams;
	}

	@Override
	public List<List<CustomDS>> findSetOfAnagrams(String phrase) {
		findProbableAnagrams(phrase);
		List<CustomDS> anagramSet = new ArrayList<CustomDS>();
		List<List<CustomDS>> customDSSet = findAnagramsRec(this.phrase, anagramSet, probableAnagrams);
		return customDSSet;
	}
	
	/**
	 * This method recursively calls itself to find the pair of anagrams that matches with the given phrase.
	 * @param phrase
	 * @param anagramSet
	 * @return
	 */
	private List<List<CustomDS>> findAnagramsRec(CustomDS phrase, List<CustomDS> anagramSet, List<CustomDS> probabAnagrams) {
		List<List<CustomDS>> customDSSet = new ArrayList<List<CustomDS>>();
		CustomDS custPhrase = new CustomDS();
		//Looping through all the probable anagrams
		for(int counter=0; counter<probabAnagrams.size(); counter++) {
			CustomDS word = probabAnagrams.get(counter);
			custPhrase = phrase.isSubtractable(word);
			//If part of the phrase can be formed from word
			if(custPhrase != null) {
				anagramSet.add(word);
				//If phrase contains exactly same alphabet as in word, we have found a solution!
				if(custPhrase.isEmptyCustomDS()) {
					//System.out.println(anagramSet.toString());
					/*
					 * Creating a new list because we don't want to lose the information during different 
					 * stack calls and adding it to the main customDSSet. 
					 */
					List<CustomDS> newList = new ArrayList<CustomDS>();
					for(CustomDS wordDS : anagramSet) {
						newList.add(wordDS);
					}
					customDSSet.add(newList);
				} else {
					List<CustomDS> newProbabAnagrams = new ArrayList<CustomDS>();
					for(int i=++counter; i<probabAnagrams.size(); i++) {
						newProbabAnagrams.add(probabAnagrams.get(i));
					}
					/*
					 * If the phrase has not yet been completely formed, calling the method recursively with the
					 * new phrase 
					 */
					List<List<CustomDS>> customTempSet = findAnagramsRec(custPhrase, anagramSet, newProbabAnagrams);
					/*
					 * After getting the result from the upper stack call, checking if it generated any result and
					 * adding it to the already existing list of results.
					 */
					if(!customTempSet.isEmpty()) {
						customDSSet.add(customTempSet.get(customTempSet.size()-1));
					}
				}
				/*
				 * If the result has been found, remove the last element and continue as we want to find all
				 * the possible solutions.
				 */
				anagramSet.remove(anagramSet.size()-1);
			}
		}
		return customDSSet;
	}

	@Override
	public String processPhrase(String phrase) {
		return Utility.processPhrase(phrase);
	}
}

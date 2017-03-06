package com.bs.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.bs.constants.Constants;
import com.bs.datastruct.CustomDS;
import com.bs.util.Utility;

public class DictionaryProcessorImpl implements IDictionaryProcessor {
	private String filePath = null;
	
	public DictionaryProcessorImpl(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public Map<String, CustomDS> processDictionary() {
		Map<String, CustomDS> dictionary = new HashMap<String, CustomDS>();
		try {
			Scanner scan = new Scanner(new File(filePath));
			String word = null;
			//load each dictionary word from the text file to HashMap
			while(scan.hasNext()) {
				word = scan.nextLine();
				//Processing the words from dictionary
				word = Utility.processPhrase(word);
				if(word.length() >= Constants.WORDS_MIN_LEN) {
					dictionary.put(word, new CustomDS(word));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dictionary;
	}
}

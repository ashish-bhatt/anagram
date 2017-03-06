package com.bs.anagram;

import java.util.List;
import java.util.Scanner;

import com.bs.datastruct.CustomDS;
import com.bs.dictionary.DictionaryProcessorImpl;

public class AnagramMain {
	public static String filePath = "/mnt/330ECC60714FDBB2/Interviews/BestSecret/misc/wordlist.txt";
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//Getting the dictionary's file location from user
		System.out.println("Please enter location for the dictionary text file: ");
		filePath = scan.nextLine();
		
		//Starting the timer for initialization of anagram finder.
		long start = System.currentTimeMillis();
		//Initializing AnagramFinderImpl by providing DictionaryProcessorImpl
		IAnagramFinder anagramFinder = new AnagramFinderImpl(new DictionaryProcessorImpl(filePath));
		//Stopping the timer for initialization of anagram finder.
		long elapsedTime = System.currentTimeMillis() - start;
		
		System.out.println("Time taken to initialize anagramFinder and to process the dictionary(one time operation) in ms: "+ elapsedTime);
		
		//Providing a CLI for user to interact with the system
		while(true) {
			System.out.println("\n\nEnter phrase(0 to exit the program): ");
			String phrase = scan.nextLine();
			//Condition to exit the program
			if(phrase.equals("0")) {
				System.out.println("Program exited.");
				break;
			}
			//Processing the phrase here
			phrase = anagramFinder.processPhrase(phrase);
			//If phrase is not empty, making the method call to get all anagrams for the given phrase
			if(!phrase.isEmpty()) {
				System.out.println("New phrase after processing is: "+phrase);
				System.out.println("\nPlease wait, processing your phrase...\n");
				//Starting the timer
				start = System.currentTimeMillis();
				//Calling the method which returns set of anagrams
				List<List<CustomDS>> list = anagramFinder.findSetOfAnagrams(phrase);
				//Stopping the timer
				elapsedTime = System.currentTimeMillis() - start;
				
				System.out.println("Time taken to get the list of anagrams in ms: "+elapsedTime);
				System.out.println("Total count of anagrams list:"+list.size());
				
				//Printing the anagrams to the screen
/*				for(List<CustomDS> anagrams : list) {
					System.out.println(anagrams);
				}*/
			} else {
				System.out.println("Phrase is empty... please try again with some alphabets.");
			}
		}
	}

}

package com.bs.datastruct;

/**
 * Class to store String's letter count for various operations e.g. isSubtractable, isEmpty etc.
 * @author abhatt
 *
 */
public class CustomDS {
	//Considering only small alphabets from a-z
	private final int MAX_LETTER_COUNT = 26;
	private int[] letterCount = new int[MAX_LETTER_COUNT];
	private String phrase = null;
	
	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	/**
	 * Default Constructor.
	 */
	public CustomDS() {
		init();
	}

	/**
	 * Constructor which takes String and prepare CustomDS.
	 * @param word
	 */
	public CustomDS(String word) {
		init();
		this.phrase = word;
		/*counting the number of each letter and placing it in the appropriate 
		position starting from a(index=0) to z(index=25)*/
		for(int count=0; count<word.length(); count++) {
			letterCount[word.charAt(count) - 97] = letterCount[word.charAt(count) - 97] + 1; 
		}
	}
	
	/**
	 * Initializes all the character value count to zero.
	 */
	private void init() {
		//initializing each element of letterCount array to 0
		for(int count=0; count<MAX_LETTER_COUNT; count++) {
			letterCount[count] = 0;
		}
	}
	
	/**
	 * Returns the CustomDS object if the word is Subtractable from the current object, otherwise null.
	 * @param word
	 * @return
	 */
	public CustomDS isSubtractable(CustomDS word) {
		CustomDS finalDS = new CustomDS();
		for(int count=0; count<MAX_LETTER_COUNT; count++) {
			if(this.letterCount[count] >= word.letterCount[count]) {
				finalDS.letterCount[count] = this.letterCount[count] - word.letterCount[count];
			} else {
				finalDS = null;
				break;
			}
		}
		return finalDS;
	}
	
	/**
	 * Subtract second CustomDS from the first CustomDS 
	 * @param first
	 * @param second
	 * @return
	 */
	@Deprecated
	public CustomDS subtract(CustomDS first, CustomDS second) {
		CustomDS finalDS = new CustomDS();
		for(int count=0; count<MAX_LETTER_COUNT; count++) {
			if(first.letterCount[count] >= second.letterCount[count]) {
				finalDS.letterCount[count] = first.letterCount[count] - second.letterCount[count];
			} else {
				finalDS = null;
				break;
			}
		}
		return finalDS;
	}
	
	/**
	 * Returns true if all CustomDS doesn't contain any characters, otherwise false.
	 * @return
	 */
	public boolean isEmptyCustomDS() {
		boolean isEmpty = true;
		for(int count=0; count<this.MAX_LETTER_COUNT; count++) {
			if(this.letterCount[count] > 0) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("phrase=");
		builder.append(phrase);
		builder.append("]");
		return builder.toString();
	}
}

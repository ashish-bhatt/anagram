package com.bs.dictionary;

import java.util.Map;

import com.bs.datastruct.CustomDS;

/**
 * Contains methods related to processing dictionary.
 * @author abhatt
 *
 */
public interface IDictionaryProcessor {

	/**
	 * Processes and creates a dictionary.
	 * @return
	 */
	public Map<String, CustomDS> processDictionary();
}

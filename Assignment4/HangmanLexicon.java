/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
ArrayList<String> words=new ArrayList<>();

public HangmanLexicon() {
	try(BufferedReader br=new BufferedReader(new FileReader("HangmanLexicon.txt"));){
		while(true) {
		  String word=br.readLine();
		  if(word==null)break;
		  words.add(word);
		}
		
	}catch(Exception ex) {
		throw new ErrorException(ex);
	}
	
}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
	return	words.get(index);
	};
}

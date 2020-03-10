/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public static void main(String[] args) {
		new Hangman().start(args);
	}
private HangmanCanvas canvas;
public void init() {
	canvas=new HangmanCanvas();
	add(canvas);
}
    public void run() {
		/* You fill this in */
    	RandomGenerator rg=RandomGenerator.getInstance();
    	HangmanLexicon hl=new HangmanLexicon();
    	int guess=8;
    	println("Welcome to Hangman!");
    	String cont="";
    	while(!cont.equals("Q")) {
    		
    	String random=hl.getWord(rg.nextInt(0, hl.getWordCount()-1));
    	String dash="";
    	for(int i=0;i<random.length();i++) {
    		dash+="-";
    	}
    	canvas.removeAll();
    	canvas.reset();
    	while(guess!=0) {
    	
    	println("The word now looks like this: "+dash);
    	canvas.displayWord(dash);
    	if(guess==1) { println(" You have only one guess left ");}
    	else{println("You have "+guess+" guesses left");}
    	String ge=this.readLine("Your Guess: ");
    	char gc=ge.toUpperCase().charAt(0);
    	if(ge.length()>1 || (gc<'A' || gc>'Z')) {
    		
    		while(ge.length()>1 || (gc<'A' || gc>'Z')) {
    	 ge=this.readLine("Please enter only 1 alphabetical character, Your Guess: ");
    	 gc=ge.toUpperCase().charAt(0);
    		
    		}
    	}
    	
    	  String gs=Character.toString(gc);
    	  
    	  if(random.contains(gs)) {
    		  println("That guess is correct");
    		 int index= random.indexOf(gs);
    		 dash=dash.substring(0, index)+gs+dash.substring(index+1);
    		 if(dash.equals(random)){
    			 println("You guessed the word: "+dash);
    			 println("You win.");
    			 cont=this.readLine("Press any key to continue Or Press Q/q to exit").toUpperCase();
    			 if(!cont.equals("Q"))guess=8;
    			 break;
    		 }
    	  }
    	  else {
    		  
    		  println("There are no "+gs+"'s in the word");
    		  canvas.noteIncorrectGuess(gc);
    		  --guess;
    	  }
    	   
    	  if(guess==0) {
    		  println("You are completely hung");
    		  println("The word was: "+random);
    		  println("You lose.");
    		  cont=this.readLine("Press any key to continue Or Press Q/q to exit: ").toUpperCase();
    		  if(!cont.equals("Q"))guess=8;
    	  }
    	}
    	}
    	println("Exiting...");
    	
    	
	}

}

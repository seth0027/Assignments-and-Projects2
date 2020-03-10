/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	private String incorrect="";
	
	private GLabel wo;
	private GLabel in;
	private int counter=0;

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		
		counter=0;
		GLine sl=new GLine(100,400,100,40);
		add(sl);
		GLine bl=new GLine(100,40,244,40);
		add(bl);
		GLine rl=new GLine(244,40,244,58);
		add(rl);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		
		if(wo!=null) {
		this.remove(wo);}
		wo=new GLabel(word);
		wo.setLocation(80, 500);
		wo.setFont("TimesNewRoman-36");
		add(wo);
		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		incorrect+=Character.toString(letter);
		if(in!=null) {
		this.remove(in);}
		in=new GLabel(incorrect);
		in.setLocation(80, 600);
		in.setFont("SansSeriff-26");
		add(in);
		++counter;
		if(counter==1 ) {
			GOval face=new GOval(208,58,72,72);
			add(face);
		}
		if(counter==2) {
			GLine bl=new GLine(244,130,244,274);
			add(bl);
		}
		if(counter==3) {
			GLine larm=new GLine(244,158,172,158);
			add(larm);
			GLine lh=new GLine(172,158,172,202);
			add(lh);
		}
		
		if(counter==4) {
			GLine rarm=new GLine(244,158,316,158);
			add(rarm);
			GLine rh=new GLine(316,158,316,202);
			add(rh);
		}
		 if(counter==5) {
			 GLine hip=new GLine(226,274,262,274);
			 add(hip);
			 GLine lleg=new GLine(226,274,226,382);
			 add(lleg);
		 }
		if(counter==6) {
			GLine rleg=new GLine(262,274,262,382);
			add(rleg);
		}
		if(counter==7) {
			GLine lf=new GLine(226,382,198,382);
			add(lf);
		}
		if(counter==8) {
			GLine rf=new GLine(262,382,290,382);
			add(rf);
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}

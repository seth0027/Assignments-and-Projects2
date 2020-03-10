/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */



import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		 int [] total = new int[nPlayers];
		 int []uppers= new int[nPlayers];
		 int []lowers= new int[nPlayers];
		 ArrayList<ArrayList<Integer>> cats=new ArrayList<>();
		 for(int n=0;n<playerNames.length;n++) {
			 ArrayList<Integer> list=new ArrayList<>();
			 cats.add(list);
		 }
		 
		 
		for(int rounds=0;rounds<13;rounds++) {
		/* You fill this in! */
		for(int i=0;i<playerNames.length;i++) {
			
			display.printMessage(playerNames[i]+"'s turn! Click \"Roll Dice\" button to roll dice");
		display.waitForPlayerToClickRoll(i);
		
		int []diceVal=new int[N_DICE];
		for(int x=0;x<N_DICE;x++) {
			diceVal[x]=rgen.nextInt(1, N_DICE+1);
		}
		int [] d=diceVal;
		display.displayDice(diceVal);
		for(int j=0;j<2;j++) {
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
		display.waitForPlayerToSelectDice();
		
		for(int x=0;x<N_DICE;x++) {
		if(display.isDieSelected(x)) {
			//display.waitForPlayerToClickRoll(0);
			d[x]=rgen.nextInt(1,N_DICE+1);
			display.displayDice(d);
			
			
		}
		
		
		}}
		
		int category;
		do{display.printMessage("Select a category for this roll");
		
		 category=display.waitForPlayerToSelectCategory();
		
		}while(cats.get(i).contains(category));
		cats.get(i).add(category);
		
		
		switch(category) {
		case ONES: 
		{   
			int counter=0;
			for(int k=0;k<N_DICE;k++) {
			if(d[k]==1) {
				counter++;
				
			}}
			uppers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;
		}
		case TWOS: 
		{  
			int counter=0;
			for(int k=0;k<N_DICE;k++) {
			if(d[k]==2) {
				counter+=2;
				
			}}
			uppers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;
		}
		case THREES: 
		{  
			int counter=0;
			for(int k=0;k<N_DICE;k++) {
			if(d[k]==3) {
				counter+=3;
				
			}}
			uppers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;
		}
		case FOURS: 
		{  
			int counter=0;
			for(int k=0;k<N_DICE;k++) {
			if(d[k]==4) {
				counter+=4;
				
			
			}}
			uppers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;
		}
		case FIVES: 
		{   
			int counter=0;
			for(int k=0;k<N_DICE;k++) {
			if(d[k]==5) {
				counter+=5;
			
				
			}}
			uppers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;
		}
		case SIXES: 
		{ 
			int counter=0;
			for(int k=0;k<N_DICE;k++) {
			if(d[k]==6) {
				counter+=6;
				
				
			}}
			uppers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;
		}
		case THREE_OF_A_KIND : {
			int check=this.check1(d);
			if(check!=-1) {
				display.updateScorecard(category, i,check );
				lowers[i]+=check;
				total[i]+=check;
				
			}
			else {
				display.updateScorecard(category, i,0 );	
			}
			break;
		}
case FOUR_OF_A_KIND : {
	int check=this.check2(d);
	if(check!=-1) {
		display.updateScorecard(category, i,check );
		lowers[i]+=check;
		total[i]+=check;
		
	}
	else {
		display.updateScorecard(category, i,0 );	
	}
	break;
}
			
		
		case FULL_HOUSE : {
			if(YahtzeeMagicStub.checkCategory(d, FULL_HOUSE)) {
			display.updateScorecard(category, i,25 );
			lowers[i]+=25;
			total[i]+=25;}
			else {
				display.updateScorecard(category, i,0 );	
			}
			break;
		}
		case SMALL_STRAIGHT : {
			if(YahtzeeMagicStub.checkCategory(d, SMALL_STRAIGHT)) {
			display.updateScorecard(category, i,30 );
			total[i]+=30;
			lowers[i]+=30;}
			else {
				display.updateScorecard(category, i,0 );
			}
			break;
		}
		case LARGE_STRAIGHT : {
			if(YahtzeeMagicStub.checkCategory(d, LARGE_STRAIGHT)) {
			display.updateScorecard(category, i,40 );
			total[i]+=40;
			lowers[i]+=40;}
			else {
				display.updateScorecard(category, i,0 );
			}
			break;
		}
		case YAHTZEE : {
			int check=this.check3(d);
			if(check!=-1) {
				display.updateScorecard(category, i,check );
				lowers[i]+=check;
				total[i]+=check;
				
			}
			else {
				display.updateScorecard(category, i,0 );	
			}
			break;
		}
		case CHANCE :

			{int counter=0;
			for(int k=0;k<N_DICE;k++) {
			
				counter+=d[k];
			
				
			}
			lowers[i]+=counter;
			total[i]+=counter;
			display.updateScorecard(category, i, counter);
			break;}
		}
		if(i==12) {
		display.updateScorecard(UPPER_SCORE, i, uppers[i]);
		display.updateScorecard(LOWER_SCORE,i, lowers[i]);
		if(uppers[i]>=63) {
			total[i]+=35;
			
			display.updateScorecard(UPPER_BONUS, i, 35);
		}
		else {
			
			display.updateScorecard(UPPER_BONUS, i, 0);
		}
		
		}
		display.updateScorecard(TOTAL,i, total[i]);
		}
	}   int max=0;
	    int maxi=0;
		for(int z=0;z<nPlayers;z++) {
			if(total[z]>max) {max=total[z];maxi=z;}
			
		}
		display.printMessage("Congratulations, "+playerNames[maxi]+" you're the winner with total score of "+max  );
	}
	private int check1(int[] dice) {
		for(int i=0;i<dice.length;i++) {
			int x=i;
			
			if(x+1 >=dice.length ) {
				x=(x+1)%dice.length;
			}
			if(x+2 >=dice.length ) {
				x=(x+2)%dice.length;
			}
			
			if(dice[x]==dice[x+1] && dice[x+1]==dice[x+2]) {
				return dice[x]+dice[x+1]+dice[x+2];
			}
		}
		return -1;
	}
	
	private int check2(int[] dice) {
		for(int i=0;i<dice.length;i++) {
			int x=i;
			
			if(x+1 >=dice.length ) {
				x=(x+1)%dice.length;
			}
			if(x+2 >=dice.length ) {
				x=(x+2)%dice.length;
			}
			if(x+3 >=dice.length) {
				x=(x+3)%dice.length;
			}
			if(x+4 >=dice.length) {
				x=(x+4)%dice.length;
			}
			
			
			if(dice[x]==dice[x+1] && dice[x+1]==dice[x+2] && dice[x+3]==dice[x+2]) {
				return dice[x]+dice[x+1]+dice[x+2]+dice[x+3];
			}
		}
		return -1;
	}
	
	private int check4(int[] dice) {
		for(int i=0;i<dice.length;i++) {
			int x=i;
			if(x+1 >=dice.length ) {
				x=(x+1)%dice.length;
			}
			if(x+2 >=dice.length ) {
				x=(x+2)%dice.length;
			}
			if(x+3 >=dice.length) {
				x=(x+3)%dice.length;
			}
			if(x+4 >=dice.length) {
				x=(x+4)%dice.length;
			}
			
			
			 if ((dice[x]==dice[x+1] && dice[x+1]==dice[x+2]) && (dice[x+3] ==dice[x+4])) {
				return 25;
		}}
		return -1;
	}
	
	private int check5(int[] dice) {
		for(int i=0;i<dice.length;i++) {
			int x=i;
			if(x+1 >=dice.length ) {
				x=(x+1)%dice.length;
			}
			if(x+2 >=dice.length ) {
				x=(x+2)%dice.length;
			}
			if(x+3 >=dice.length) {
				x=(x+3)%dice.length;
			}
			if(x+4 >=dice.length) {
				x=(x+4)%dice.length;
			}
			
			
			 if (dice[x]+1==dice[x+1] && dice[x+1]+1==dice[x+2] && dice[x+2]+1 ==dice[x+3]) {
				return 30;
		}
			 else if (dice[x]+1==dice[x+1] && dice[x+1]+1==dice[x+2] && dice[+2]+1 ==dice[x+3] && dice[x+3]+1 ==dice[x+4]) {
               return 40;
			 }}
		return -1;
	}
	private int check3(int[] dice) {
		
			
			if(dice[0]==dice[1] && dice[1]==dice[2] && dice[2]==dice[3] && dice[3]==dice[4]) {
				return 50;
			}
		
		return -1;
	}
		
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen=RandomGenerator.getInstance();
	
}

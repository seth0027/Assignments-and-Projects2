/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int num=readInt("Enter a number: ");
		int result=0;
		int counter=0;
		
		while(result!=1) {
			++counter;
			if(num%2==0) {
				println(num+ " is even, so i take half "+num/2);
				num=num/2;
				
			}
			
			else if(num%2==1) {
				println(num+" is odd, so i take 3n+1 "+(3*num+1));
				num=3*num+1;
			}
			result=num;
			
		}
		println("The process took "+counter+" steps to reach 1");
		
	}
}


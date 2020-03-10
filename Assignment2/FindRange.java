/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		
		/* You fill this in */
		println("This program finds samllest and largest numbers ");
		int num1=this.readInt("?");
		if(num1==0)println("No smallest no largest");
		else {
			while(true) {
				int num2=readInt("?");
			int min=	Math.min(num1, num2);
				int max= Math.max(num1, num2);
				
				num1=num2;
				if(num2==0) {
					println("Smallest: "+min+"\n"+"Largest: "+max);
					break;
				}
			}
		}
	}
}


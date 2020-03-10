/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		GOval c1=new GOval(72,72);
		c1.setLocation(getWidth(), getHeight());
		c1.setFilled(true);
		c1.setFillColor(Color.RED);
		this.add(c1);
		GOval c2=new GOval(39,39);
		c2.setLocation(c1.getX()+10,c1.getX()+20);
		c2.setFilled(true);
		c2.setFillColor(Color.WHITE);
		add(c2);
	}
}

/*
 * File: Artistry.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import java.awt.Color;

import acm.graphics.*;
import acm.program.*;


public class Artistry extends GraphicsProgram {
	public void run() {
		/* You fill this in.  Remember that you must have
		 * 
		 * 1. At most twenty GObjects,
		 * 2. At least one filled object,
		 * 3. At least two different colors of objects, and
		 * 4. At least three different types of objects (not
		 *    counting the GLabel you use to sign your name).
		 * 
		 * Also, be sure to sign your name in the bottom-right
		 * corner of the canvas.
		 */
		
		GOval face=new GOval(100,100,500,500);
		face.setFilled(true);
face.setFillColor(Color.YELLOW);		
		add(face);
		GOval e1=new GOval(200,250,80,80);
		e1.setFilled(true);
		
		add(e1);
		GOval e2=new GOval (400,250,80,80);
		e2.setFilled(true);
		add(e2);
		GRect n=new GRect(100+200,100+250,90,70);n.setFilled(true);add(n);
		GLine s=new GLine(200,450,500,450);
		add(s);
		GLine s1=new GLine(150,400,200,450);
		add(s1);
		GLine s2=new GLine(550,400,500,450);
		add(s2);
		
		GLabel name=new GLabel("Artistry by XYZ");
		name.setLocation(getWidth()-name.getWidth(),getHeight()-name.getAscent());add(name);
		
	}
}

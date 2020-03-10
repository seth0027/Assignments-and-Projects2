/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
private RandomGenerator rgen=RandomGenerator.getInstance();
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		update();
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		entries.clear();
		update();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		if(!entries.contains(entry)) {
		entries.add(entry);
		update();
		}
	}
	public void deleteEntry(NameSurferEntry entry) {
		// You fill this in //
		
		if(entries.contains(entry)) {entries.remove(entry);
		update();}
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		this.removeAll();
		createGrid();
		displayData();
	}
	public void displayData() {
		//for(int i=0;i<entries.size();i++) {
			
		//}
		int seperation=getWidth()/12;
		int i=0;
		int rank=0;
		int rank2=0;
		for(NameSurferEntry e: entries) {
			GLabel name;
			Color random=rgen.nextColor();
			for(int j=0;j<NDECADES-1;j++) {
				i=j+1;
				 rank=e.getRank(j);
				 rank2=e.getRank(i);
				//GPoint point=new GPoint();
				//point.setLocation();
				if(rank==0) {
					rank=this.getHeight()-GRAPH_MARGIN_SIZE-20;
				}
				if(rank2==0) {
					rank2=this.getHeight()-GRAPH_MARGIN_SIZE-20;
				}
				if (rank>=this.getHeight()-GRAPH_MARGIN_SIZE) {
					rank=this.getHeight()-GRAPH_MARGIN_SIZE-20;
				}
				if (rank2>=this.getHeight()-GRAPH_MARGIN_SIZE) {
					rank2=this.getHeight()-GRAPH_MARGIN_SIZE-20;
				}
				GLine line=new GLine(j*seperation, rank+20,i*seperation, rank2+20);
				line.setColor(random);
	             add(line);		
	             if(rank!=this.getHeight()-GRAPH_MARGIN_SIZE-20) {
	             name=new GLabel (e.getName()+ " "+e.getRank(j));
	             name.setLocation(j*seperation, rank+20);
	             name.setColor(random);
	             add(name);}
	             else if(rank==this.getHeight()-GRAPH_MARGIN_SIZE-20) {
	            	  name=new GLabel (e.getName()+ " *");
		             name.setLocation(j*seperation, rank+20);
		             name.setColor(random);
		             add(name);
	            	 
	             }
			}
			if(e.getRank(NDECADES-1)!=0) {
				name=new GLabel (e.getName()+ " "+e.getRank(11));
				if(e.getRank(11)>=this.getHeight()-GRAPH_MARGIN_SIZE) {
					rank=this.getHeight()-GRAPH_MARGIN_SIZE;
	             name.setLocation(11*seperation, rank);}
				else {
					 name.setLocation(11*seperation, e.getRank(11)+20);
				}
	             name.setColor(random);
	             add(name);
			}
			else if(e.getRank(NDECADES-1)==0) {
				name=new GLabel (e.getName()+ " *");
				
	             name.setLocation(11*seperation, this.getHeight()-GRAPH_MARGIN_SIZE);
				
	           
	             name.setColor(random);
	             add(name);}
			}
		}
	
	public void createGrid(){
		double seperation=this.getWidth()/12;
		int year=1900;
		
		for(int i=0;i<NDECADES;i++) {
			GLine vertical=new GLine((i*seperation),0,(i*seperation),this.getHeight());
			add(vertical);
			
			GLabel label=new GLabel(Integer.toString(year));
			label.setLocation((i*seperation), this.getHeight());
			label.setFont("Dialog-18");
			add(label);
			year+=10;
		}
		
		GLine h1=new GLine(0,GRAPH_MARGIN_SIZE,this.getWidth(),GRAPH_MARGIN_SIZE);
		add(h1);
		
		
		GLine h2=new GLine(0,this.getHeight()-GRAPH_MARGIN_SIZE,this.getWidth(),this.getHeight()-GRAPH_MARGIN_SIZE);
		add(h2);
		
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> entries=new ArrayList<>();
}

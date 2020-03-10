/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
public static void main(String[] args) {
	new NameSurfer().start(args);
}
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setNorth();
		addActionListeners();
		graph = new NameSurferGraph();
		add(graph);
		
		//NameSurferEntry e=new NameSurferEntry("Aaron 193 208 218 274 279 232 132 36 32 31 41 77");
		//println(e);
	
	}
public void setNorth() {
	add(new JLabel("Name:"),NORTH);
	name=new JTextField(15);
	name.setActionCommand("Graph");
	name.addActionListener(this);
	add(name,NORTH);
	add(new JButton("Graph"),NORTH);
	add(new JButton("Clear"),NORTH);
	add(new JButton("Remove"),NORTH);
}
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String cmd=e.getActionCommand();
		if(!name.getText().isEmpty() && cmd.equals("Graph")) {
			if(db.findEntry(name.getText())!=null) {
				graph.addEntry(db.findEntry(name.getText()));
			};
		}
		if(cmd.contentEquals("Clear")) {
			graph.clear();
		}
		if(!name.getText().isEmpty() && cmd.equals("Remove")) {
			if(db.findEntry(name.getText())!=null) {
				graph.deleteEntry(db.findEntry(name.getText()));
			};
		}
	}
	private JTextField name;
	private NameSurferDataBase db=new NameSurferDataBase(NAMES_DATA_FILE );
	private NameSurferGraph graph;
}

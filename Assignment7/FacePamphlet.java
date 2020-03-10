/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
public class FacePamphlet extends Program 
					implements FacePamphletConstants {
public static void main(String[] args) {
	new FacePamphlet().start(args);
}
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	
	public FacePamphlet() {
		canvas=new FacePamphletCanvas();
		add(canvas);
	}
	public void init() {
		// You fill this in
		setWest();
		setNorth();
		this.addActionListeners();
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension d=tk.getScreenSize();
		this.setSize(d);
		//this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
    }
    private void setWest() {
    	status=new JTextField(FacePamphlet.TEXT_FIELD_SIZE);
    	status.setActionCommand("Change Status");
    	add(status,WEST);
    	add(new JButton("Change Status"),WEST);
    	add(new JLabel(EMPTY_LABEL_TEXT), WEST);
    	picture=new JTextField(FacePamphlet.TEXT_FIELD_SIZE);
    	picture.setActionCommand("Change Picture");
    	add(picture,WEST);
    	add(new JButton("Change Picture"),WEST);
    	add(new JLabel(EMPTY_LABEL_TEXT), WEST);
    	addFriend=new JTextField(FacePamphlet.TEXT_FIELD_SIZE);
    	addFriend.setActionCommand("Add Friend");
    	add(addFriend,WEST);
    	add(new JButton("Add Friend"),WEST);
    	
    	status.addActionListener(this);
    	picture.addActionListener(this);
    	addFriend.addActionListener(this);
    }
    public void setNorth() {
    	add(new JLabel("Name"),NORTH);
    	name=new JTextField(FacePamphlet.TEXT_FIELD_SIZE);
    	
    	add(name,NORTH);
    	add(new JButton("Add"),NORTH);
    	add(new JButton("Delete"),NORTH);
    	add(new JButton("LookUp"),NORTH);
    	name.setActionCommand("LookUp");
    	name.addActionListener(this);
    	add(new JLabel("     File"),NORTH);
    	file=new JTextField(TEXT_FIELD_SIZE);
    	add(file,NORTH);
    	file.setActionCommand("Load");
    	file.addActionListener(this);
    	add(new JButton("Load"),NORTH);
    	add(new JButton("Save"),NORTH);
    }
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String cmd=e.getActionCommand();
    	if(!(status.getText().equals("")) && cmd.equals("Change Status")) {
    		if(current!=null) {
    			current.setStatus(status.getText());
    			canvas.displayProfile(current);
    			canvas.showMessage("Status Updated to "+current.getStatus());
    			
    		}
    		else {
    			canvas.removeAll();
    			canvas.showMessage("Please select a profile to change status");

    			
    		}
    	}
    	if(!(picture.getText().equals("")) && cmd.equals("Change Picture")) {
    		if(current!=null) {
    			GImage image = null;
    			try {
    			image = new GImage(picture.getText());
    			} catch (ErrorException ex) {
    			// Code that is executed if the filename cannot be opened.
    			}
    			if (image!=null) {
    				current.setImageName(picture.getText());
    				current.setImage(image);
    				canvas.displayProfile(current);
    				canvas.showMessage("Picture Updated");
    			}
    			else {
    				canvas.displayProfile(current);
    				canvas.showMessage("Unable to Open Image File "+picture.getText());
    				
    			}
    		}
    		else {
    			canvas.removeAll();
    			canvas.showMessage("Please select a profile to change picture");

    			
    		}
    	}
    	if(!(addFriend.getText().equals("")) && cmd.equals("Add Friend")) {
    		if(current!=null) {
    			if(db.containsProfile(addFriend.getText())) {
    				if(current.getName().equals(addFriend.getText())) {
    					canvas.displayProfile(current);
    					canvas.showMessage("Cannot add yourself to friend List");
    				}
    				else if(current.addFriend(addFriend.getText())) {
    					db.getProfile(addFriend.getText()).addFriend(current.getName());
    					canvas.displayProfile(current);
    					canvas.showMessage(addFriend.getText()+" added as a friend");
    					
    				}
    				
    				else {
    					canvas.displayProfile(current);
    					canvas.showMessage(current.getName()+ " already has "+addFriend.getText());
    					
    				}
    			}
    			else {
    				canvas.displayProfile(current);
    				canvas.showMessage(addFriend.getText()+" does not exist");
    				
    			}
    		}
    		else {canvas.removeAll();
    			canvas.showMessage("Please select a Profile to add a Friend");
    			
    		}
    	}
    	
    	if(!(name.getText().equals("")) && cmd.equals("LookUp")) {
    		if(db.containsProfile(name.getText())) {
    			current=db.getProfile(name.getText());
    			canvas.displayProfile(current);
    			canvas.showMessage("Display "+name.getText());
    			}
    		else {
    			current=null;
    			canvas.removeAll();
    			canvas.showMessage("Profile with name "+name.getText()+" does not exist ");
    			
    		}
    	}
    	if(!(name.getText().equals("")) && cmd.equals("Add")) {
    		if(!db.containsProfile(name.getText())) {
    			db.addProfile(current=new FacePamphletProfile(name.getText()));
    			
    			canvas.displayProfile(current);
    			canvas.showMessage("A new Profile created");
    			
    			
    			}
    		else {
    			current=db.getProfile(name.getText());
    			
    			canvas.displayProfile(current);
    			canvas.showMessage("A Profile with the name "+current.getName()+" already exists");
    			
    			
    		}
    	}
    	if(!(name.getText().equals("")) && cmd.equals("Delete")) {
    		current=null;
    		if(db.containsProfile(name.getText())) {
    			println("Delete:  Profile of "+db.getProfile(name.getText()).getName()+" deleted");
    			db.deleteProfile(name.getText());
    			canvas.removeAll();
    			canvas.showMessage("Profile of "+name.getText()+" deleted");
    			
    			}
    		else {
    			canvas.removeAll();
    			canvas.showMessage("Profile with name "+name.getText()+" does not exist");
    			
    		}
    	}
    	if(!(file.getText().equals("")) && cmd.equals("Load")) {
    		if(db.readData2(file.getText())) {
    			canvas.removeAll();
    			canvas.showMessage("Loaded File "+file.getText());
    		}
    		else {
    			canvas.removeAll();
    			canvas.showMessage("Unable to open file "+file.getText());
    		}
    	}
if(!(file.getText().equals("")) && cmd.equals("Save")) {
	if(db.loadData(file.getText())) {
		canvas.removeAll();
		canvas.showMessage("Saved File "+file.getText());
	}
	else {
		canvas.removeAll();
		canvas.showMessage("Unable to open file "+file.getText());
	}
    	}
    	
    	
	}
    private JTextField status;
    private JTextField picture;
    private JTextField addFriend;
    private JTextField name;
    private JTextField file;
    private FacePamphletDatabase db=new FacePamphletDatabase();
    private FacePamphletProfile current=null;
    private FacePamphletCanvas canvas;

}

/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		GLabel message=new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		message.setLocation(300, 400);
		add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		this.removeAll();
		GLabel name=new GLabel(profile.getName());
		name.setLocation(LEFT_MARGIN,TOP_MARGIN+5);
		name.setColor(Color.BLUE);
		name.setFont(PROFILE_NAME_FONT);
		add(name);
		
		
		
		if(profile.getImage()==null) {
			GRect rect=new GRect(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(rect);
			GLabel im=new GLabel("No Image");
			im.setFont(PROFILE_IMAGE_FONT);
			im.setLocation(IMAGE_WIDTH/2-25,IMAGE_HEIGHT/2+20);
			add(im);
		}
		
		else if(profile.getImage()!=null) {
			GImage image=profile.getImage();
			image.setBounds(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			//image.scale(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		}
		
		if(profile.getStatus().equals("")) {
			GLabel stat=new GLabel("No current Status");
			stat.setLocation(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT+STATUS_MARGIN);
			stat.setFont(PROFILE_STATUS_FONT);
			add(stat);
			
		}
		else if(!profile.getStatus().equals("")) {
			GLabel stat=new GLabel(profile.getName()+" is "+profile.getStatus());
			stat.setLocation(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT + STATUS_MARGIN);
			stat.setFont(PROFILE_STATUS_FONT);
			add(stat);
		}
		
		GLabel friends= new GLabel("Friends:");
		friends.setLocation(WIDTH+350,TOP_MARGIN+IMAGE_MARGIN );
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friends);
		if(profile.getFriends().size()>0) {
		for(int i=0;i<profile.getFriends().size();i++) {
			GLabel list=new GLabel(profile.getFriends().get(i));
			list.setLocation(WIDTH+350,TOP_MARGIN+IMAGE_MARGIN +((i+1)*15) );
			list.setFont(PROFILE_FRIEND_FONT);
			add(list);
		}
		}
		
	}

	
}

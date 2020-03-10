/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.io.*;
import java.util.*;

import acm.graphics.GImage;
import acm.util.ErrorException;

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		// You fill this in
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		database.put(profile.getName(), profile);
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		// You fill this in.  Currently always returns null.
		if(database.containsKey(name)) {
		return database.get(name);}
		else return null;
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		if(database.containsKey(name)) {
		
		database.remove(name).removeFriend(name);}
		
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		// You fill this in.  Currently always returns false.
		return database.containsKey(name);
	}
	
	public boolean readData(String fileName) {
		
		File file=new File(fileName); 
		try(Scanner input=new Scanner(file);){
			if(!input.hasNext()) {
				return false;
		}
			
			else {	database.clear();
				while(input.hasNextLine()) {
					
					input.nextLine();
					String name=input.nextLine();
					String image=input.nextLine();
					String status=input.nextLine();
					
					
					ArrayList<String> friends=new ArrayList<>();
					
					String friend=input.nextLine();
					if(!friend.isEmpty()) {
						while(!friend.isEmpty()) {
							friends.add(friend);
							friend=input.nextLine();
						}
					}
					
					FacePamphletProfile p=new FacePamphletProfile(name);
					p.setStatus(status);
					GImage img=null;
					if(image.isEmpty()) {
						img=null;
						p.setImage(img);
					}
					else {
						try {
			    			img = new GImage(image);
			    			} catch (ErrorException ex) {
			    			
			    			}
			    			System.out.println(img);
							p.setImage(img);
					}
					
	    			
					
					for(String fri: friends) {
						p.addFriend(fri);
					}
					this.addProfile(p);
				}
				return true;
			}
			} catch (FileNotFoundException e) {
				System.out.println("NOT FOUND");
				return false;
			}
			
			
		}
	
public boolean readData2(String fileName) {
		try(BufferedReader rd=new BufferedReader(new FileReader(fileName));){
			database.clear();
			int counter=0;
			rd.readLine();
			while(true) {
				
				
				String name=rd.readLine();
				if(name==null)break;
				
				String image=rd.readLine();
				
				String status=rd.readLine();
				
				String friend=rd.readLine();
			
				ArrayList<String> friends = new ArrayList<>();
				if(!friend.isEmpty()) {
					String fri=friend;
					while(!fri.isEmpty()) {
						friends.add(fri);
						
						fri=rd.readLine();
					}
					

				}
				
								
				
				
				FacePamphletProfile p=new FacePamphletProfile(name);
				p.setStatus(status);
				p.setImageName(image);
				if(!image.isEmpty()) {
					p.setImage(new GImage(image));
				}
			    for(String f: friends) {
			    	p.addFriend(f);
			    }
			    this.addProfile(p);
			    
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
		}

	public boolean loadData(String fileName) {
		
		try(PrintWriter output=new PrintWriter(new FileWriter(fileName));){
			
			output.println(database.size());
			for(String name: database.keySet()) {
				output.println(name);
				if(database.get(name).getImage()==null) {
					output.println();
				}else {output.println(database.get(name).getImageName());}
				output.println(database.get(name).getStatus());
				for(String friend:database.get(name).getFriends()) {
					output.println(friend);
				}
				output.println();
			}
			return true;
		}catch(FileNotFoundException e) {
			return false;
		}
		catch(IOException es) {
			return false;
		}
		
	}
		
	
	private Map<String,FacePamphletProfile> database=new HashMap<>();

}

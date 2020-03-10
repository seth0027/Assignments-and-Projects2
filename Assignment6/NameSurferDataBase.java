import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import acm.util.ErrorException;
import acmx.export.java.io.FileReader;
import java.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try(BufferedReader rd=new BufferedReader(new FileReader(filename));){
			
			while(true) {
				String line=rd.readLine();
				if(line==null)break;
				
				NameSurferEntry e= new NameSurferEntry(line);
				database.put(e.getName(), e);
			}
			
			
			
		} catch (FileNotFoundException e) {
			throw new ErrorException(e);
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		String o=Character.toString(name.charAt(0)).toUpperCase();
	String	low=name.substring(1).toLowerCase();
	name=o+low;
	if(database.containsKey(name)) {
		return database.get(name);
	}
		
		// You need to turn this stub into a real implementation //
		return null;
	}
	
	private Map<String,NameSurferEntry> database = new HashMap<>();
}


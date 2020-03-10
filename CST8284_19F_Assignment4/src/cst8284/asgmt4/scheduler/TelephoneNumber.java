package cst8284.asgmt4.scheduler;

import java.io.Serializable;

public class TelephoneNumber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int areaCode, lineNumber, prefix;
	
	public TelephoneNumber(String phoneNumber) {
		Appointment.testInputNull(phoneNumber);
		testBadCharactersInInput(phoneNumber);
		testIncorrectFormat(phoneNumber);
		
		int areaCode = Integer.parseInt(phoneNumber.split("-")[0].trim());
		int prefix = Integer.parseInt(phoneNumber.split("-")[1].trim());
		int lineNumber = Integer.parseInt(phoneNumber.split("-")[2].trim());
		setAreaCode(areaCode); setPrefix(prefix); setLineNumber(lineNumber);
	}	
	
	public int getAreaCode() {return areaCode;}
	public void setAreaCode(int areaCode) {testStartsWith0or1(areaCode); this.areaCode = areaCode;}
	public int getPrefix() { return prefix;}
	public void setPrefix(int prefix) {this.prefix = prefix;}
	public int getLineNumber() {return lineNumber;}
	public void setLineNumber(int lineNumber) {this.lineNumber = lineNumber;}
	
	public String toString() {return "(" + getAreaCode() +") "+ getPrefix() + "-" + getLineNumber();}
	
	private void testStartsWith0or1(int areaCode) {
		String aCStr = Integer.toString(areaCode);
		if (aCStr.length() < 3 || aCStr.charAt(0)=='1')
		   throw new BadAppointmentDataException ("Area code can’t start with a '0' or a '1'", "Invalid Number");
	}
	
	private void testIncorrectFormat(String phoneNumber) {
		if (!phoneNumber.matches("^[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$")) {
			throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number",
				"Incorrect format");
		}
	}
	
	private void testBadCharactersInInput(String phoneNumber) {
		if (!phoneNumber.matches("^[0-9\\-]*$")) {
			throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character '-'",
				"Bad character(s) in input string");
		}
	}
}

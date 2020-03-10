package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.util.Calendar;

public class Appointment implements Serializable {
	private Calendar aptDate;
	private String firstName, lastName;
	private TelephoneNumber phone;
	private Activity activity;
	public static final long serialVersionUID = 1L;

	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity act) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, act);
	}

	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity act) {
			setFirstName(firstName.trim());
			setLastName(lastName.trim());
			setCalendar(cal);
			setPhone(phone);
			setActivity(act);
	}

	public Calendar getCalendar() {
		return aptDate;
	}

	public void setCalendar(Calendar aptDate) {
		//testInputNull(aptDate);
		//aptDate.setLenient(false);
		//try {
		  //  aptDate.getTime();
		//} catch (Exception e) {
		  //throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
		//}
		this.aptDate = aptDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		testInputNull(firstName);
		testNameCharacters(firstName);
		testStringLength(firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		testNameCharacters(lastName);
		testInputNull(lastName);
		testStringLength(lastName);
		this.lastName = lastName;
	}

	public TelephoneNumber getPhone() {
		return phone;
	}

	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String toString() {
		return getCalendar().getTime().toString() + "\n" + getFirstName() + " " + getLastName() + "\n"
				+ getPhone().toString() + "\n" + getActivity().toString();
	}

	public static void testInputNull(Object s) {
		if (s == null )
			throw new BadAppointmentDataException("Must enter a value", "Empty or null value entered");
	}

	public void testStringLength(String name) {
		if (name.length() > 30)
			throw new BadAppointmentDataException("Name cannot exceed 30 characters", "Name exceeds 30 characters");
	}

	public void testNameCharacters(String name) {
		if (!name.matches("^[-.,A-Za-z]+$")) 
			throw new BadAppointmentDataException(
					"Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')",
					"Illegal characters in name");
	}

}

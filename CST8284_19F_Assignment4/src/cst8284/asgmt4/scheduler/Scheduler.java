package cst8284.asgmt4.scheduler;

//import java.util.Scanner;
//**SPECIAL NOTE -> All references for JOptionPane Dialogs is taken from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html //

import javax.swing.JOptionPane;

import cst8284.asgmt4.scheduler.BadAppointmentDataException;
import cst8284.asgmt4.scheduler.Appointment;
import cst8284.asgmt4.employee.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Scheduler {
    SchedulerViewer sv=new SchedulerViewer();
    //AppointmentDialog ad=new AppointmentDialog();
	//private static Scanner scan = new Scanner(System.in);
	private static ArrayList<Appointment> appointments = new ArrayList<>();
	private static Employee employee;
public static String title;

public Scheduler() {
	
}
	public Scheduler(Employee emp) {
		title="Scheduling appointments for " + emp;
		setEmployee(emp);
	}

	private void setEmployee(Employee emp) {
		Scheduler.employee = emp;
	}

	public static Employee getEmployee() {
		return employee;
	}

	public void launch() {
		SchedulerViewer.load();
	}

	

	

	public  Appointment makeAppointmentFromUserInput() {
		try {
			String fullName =AppointmentDialog.name.getText(); 
			String phoneNumber = AppointmentDialog.phone.getText();
			Calendar cal = makeCalendarFromUserInput(false);
			String activity = AppointmentDialog.activity.getText();
			String type=getAct(getEmployee().getActivityType());
			Activity act = new Activity(activity, type);
			TelephoneNumber phone = new TelephoneNumber(phoneNumber);
			int yn=JOptionPane.showConfirmDialog(AppointmentDialog.f, "Are you sure you want to save  ?","Save ?",JOptionPane.YES_NO_OPTION);
			if (yn==0) {
			return (new Appointment(cal, fullName, phone, act));}
			else return null;
		} catch (BadAppointmentDataException ex) {
			
			JOptionPane.showMessageDialog(AppointmentDialog.f, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
		//references	https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
			//System.out.println(ex.getMessage());
			return null;
		}
	}
	private String getAct(ArrayList<String> s) {
		
		  for(int x=0;x<s.size();x++) {
			  if(AppointmentDialog.options.getSelectedIndex()==x)
				  
				  return s.get(x);
		  }
		  return "";
		  }
  
	
	public static Calendar makeCalendarFromUserInput(boolean suppressHour) {
		Calendar cal = Calendar.getInstance();
		int hour = 0;

		cal.clear();
		String date=AppointmentDialog.date.getText();
		if (date.trim().equals(""))
			throw new BadAppointmentDataException("Must enter a value", "Empty or null value ");
		try {
			int day = Integer.parseInt(date.substring(0, 2));

			int month = Integer.parseInt(date.substring(2, 4)) - 1; // offset by one to account for zero-based month in
																	// Calendar
			// System.out.println(month);
			if (day > 31 || month > 11 || day > 31 && month == 0 || day > 28 && month == 1 || day > 31 && month == 2
					|| day > 30 && month == 3 || day > 31 && month == 4 || day > 30 && month == 5
					|| day > 31 && month == 6 || day > 31 && month == 7 || day > 30 && month == 8
					|| day > 31 && month == 9 || day > 30 && month == 10 || day > 31 && month == 11)
				throw new BadAppointmentDataException("Bad calendar date entered;format is DDMMYYYY",
						"Bad Calendar format");

			int year = Integer.parseInt(date.substring(4, 8));

			if (!suppressHour) {
				String time=AppointmentDialog.time.getText();
				hour = processTimeString(time);
			}

			cal.set(year, month, day, hour, 0);
			return (cal);
		} catch (NumberFormatException ex) {
			throw new BadAppointmentDataException("Bad calendar date entered;format is DDMMYYYY",
					"Bad Calendar format");
		}
	}
	
	
	public static Calendar makeCalendarFromUserInput2(boolean suppressHour){
		Calendar cal = Calendar.getInstance();
		int hour = 0;

		cal.clear();
		try { String date=JOptionPane.showInputDialog(SchedulerViewer.frame, "Appointment Date (entered as DDMMYYYY): ", "Date", JOptionPane.QUESTION_MESSAGE);
		if (date.trim().equals(""))
			throw new BadAppointmentDataException("Must enter a value", "Empty or null value ");
		
			int day = Integer.parseInt(date.substring(0, 2));

			int month = Integer.parseInt(date.substring(2, 4)) - 1; // offset by one to account for zero-based month in
																	// Calendar
			// System.out.println(month);
			if (day > 31 || month > 11 || day > 31 && month == 0 || day > 28 && month == 1 || day > 31 && month == 2
					|| day > 30 && month == 3 || day > 31 && month == 4 || day > 30 && month == 5
					|| day > 31 && month == 6 || day > 31 && month == 7 || day > 30 && month == 8
					|| day > 31 && month == 9 || day > 30 && month == 10 || day > 31 && month == 11)
				throw new BadAppointmentDataException("Bad calendar date entered;format is DDMMYYYY",
						"Bad Calendar format");

			int year = Integer.parseInt(date.substring(4, 8));

			if (!suppressHour) {
				String time =JOptionPane.showInputDialog(SchedulerViewer.frame, "Appointment Time: ", "Time", JOptionPane.QUESTION_MESSAGE);
				hour = processTimeString(time);
			}

			cal.set(year, month, day, hour, 0);
			return (cal);
		} catch (NumberFormatException  | BadAppointmentDataException ex ) {
			throw new BadAppointmentDataException("Bad calendar date entered;format is DDMMYYYY",
					"Bad Calendar format");
		}
		}
	
	
	
	

	

	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":"))
			hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains(" "))
			hour = Integer.parseInt(t.split(" ")[0]);
		else
			hour = Integer.parseInt(t);
		return ((hour < 8) ? hour + 12 : hour);
	}

	private Appointment findAppointment(Calendar cal) {
		for (Appointment apt : getAppointments()) {
			
			if (cal.equals(apt.getCalendar())) {
				
				//JOptionPane.showMessageDialog(AppointmentDialog.f, apt, "Appointment Found", JOptionPane.INFORMATION_MESSAGE);
				return apt;}}
		
		return null;
	}
	
	public void find() {
		Appointment ap=findAppointment(makeCalendarFromUserInput2(false));
		if(ap!=null) {
			JOptionPane.showMessageDialog(AppointmentDialog.f, ap, "Appointment Found", JOptionPane.INFORMATION_MESSAGE);
		}
		
		else {
			JOptionPane.showMessageDialog(AppointmentDialog.f, "No Appointment found with this time", "Appointment Not Found", JOptionPane.WARNING_MESSAGE);
		}
	}

	public boolean saveAppointment(Appointment apt) {
		if (apt != null) {
			Calendar cal = apt.getCalendar(); // Check that the appointment does not already exist
			if (findAppointment(cal) == null) { // Time slot available, okay to add appointment
				getAppointments().add(apt);
				JOptionPane.showMessageDialog(AppointmentDialog.f, "Appointment Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
				return true;
			} // else time slot taken, need to make another choice
			JOptionPane.showMessageDialog(AppointmentDialog.f, "Appointment at that time already exist", " Not Saved", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	public boolean deleteAppointment(Calendar cal) {
		if (displayAppointment2(cal)) { // display existing appointment on this date/time
			int yn=JOptionPane.showConfirmDialog(AppointmentDialog.f, "Are you sure to delete the appointment", "Delete ?", JOptionPane.YES_NO_OPTION);
			//String okToChange = getResponseTo("\nEnter 'Yes' to delete this appointment");
			if (yn==0) { // okay to proceed with change/deletion?
				getAppointments().remove(findAppointment(cal));
				JOptionPane.showMessageDialog(AppointmentDialog.f, "Appointment Deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
				//System.out.println("Appointment deleted");
				return true;
			} else
				JOptionPane.showMessageDialog(AppointmentDialog.f, "Request Cancelled", "Delete Canceled", JOptionPane.INFORMATION_MESSAGE);
				//System.out.println("Request cancelled");
		}
		return false; // Appointment didn't exist at the date/time specified
	}

	private boolean displayAppointment2(Calendar cal) {
		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		JOptionPane.showMessageDialog(AppointmentDialog.f, (apt != null) ? "\n\n" + apt.toString() + "\n" : // Output the appointment as a string to the
			// console, otherwise...
"No appointment scheduled between " + hr + ":00 and " + (hr + 1) + ":00\n", "Appointment Info", JOptionPane.INFORMATION_MESSAGE);
		//System.out.print((apt != null) ? "\n\n" + apt.toString() + "\n" : // Output the appointment as a string to the
																			// console, otherwise...
				//"No appointment scheduled between " + hr + ":00 and " + (hr + 1) + ":00\n");
		return (apt != null);
	}
	public boolean changeAppointment(Calendar cal) {
		if (displayAppointment2(cal)) { // display existing appointment on this date/time
			int yn=JOptionPane.showConfirmDialog(AppointmentDialog.f, "Are you sure to change the appointment", "Change?", JOptionPane.YES_NO_OPTION);
			//String okToChange = getResponseTo("\nEnter 'Yes' to change the date and time of this appointment ");
			if (yn==0) {
				JOptionPane.showMessageDialog(AppointmentDialog.f, "Enter new date and time");
				//System.out.println("Enter new date and time");
				Calendar newCal = makeCalendarFromUserInput2(false); // get new date/time
				if (findAppointment(newCal) == null) { // appointment time not already taken
					findAppointment(cal).setCalendar(newCal); // set new date/time in appointment
					JOptionPane.showMessageDialog(AppointmentDialog.f, "Appointment Rebooked", "Re-Booked", JOptionPane.INFORMATION_MESSAGE);
					return true; // new appointment time set
				} else
					JOptionPane.showMessageDialog(AppointmentDialog.f, "That time is already booked for an appointment\n");
					//System.out.println("That time is already booked for an appointment\n");
			} else
				JOptionPane.showMessageDialog(AppointmentDialog.f, "Request Cancelled", "Change Canceled", JOptionPane.INFORMATION_MESSAGE);
		}
		return false; // Appointment does not exist, was unavailable, or cancelled
	}

	public boolean displayAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
	
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		
		
		//System.out.print((apt != null) ? "\n\n" + apt.toString() + "\n" : // Output the appointment as a string to the
																			// console, otherwise...
			//	"No appointment scheduled between " + hr + ":00 and " + (hr + 1) + ":00\n");
		
		SchedulerViewer.scrollText.append((apt != null) ? "\n\n" + apt.toString() + "\n" : // Output the appointment as a string to the
																			// console, otherwise...
				"No appointment scheduled between " + hr + ":00 and " + (hr + 1) + ":00\n");
		
		return (apt != null);}
	

	public void displayDaySchedule(Calendar cal) {
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			displayAppointment(cal);
		}
	}

	public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try (FileOutputStream fos = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			for (Appointment apt : apts)
				oos.writeObject(apt);
			JOptionPane.showMessageDialog(SchedulerViewer.frame, "Appointments Saved Successfully","Saved", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(SchedulerViewer.frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		apts.clear(); // remove all existing appointments from the ArrayList before loading from file
		try (FileInputStream fis = new FileInputStream(sourceFile);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			while (true)
				apts.add((Appointment) ois.readObject());
		} catch (EOFException ex) {
			JOptionPane.showMessageDialog(SchedulerViewer.frame, "Appointments loaded Successfully From "+sourceFile,"Done", JOptionPane.INFORMATION_MESSAGE);
			
			return true;
		} catch (IOException | ClassNotFoundException e) {
			return false;
		}
	}

	public static ArrayList<Appointment> getAppointments() {
		return appointments;
	}

}

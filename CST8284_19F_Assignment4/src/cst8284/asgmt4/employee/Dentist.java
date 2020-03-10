package cst8284.asgmt4.employee;


import java.util.ArrayList;
import java.util.Arrays;


public class Dentist extends Employee {
	private static String[] workDescription = {"Assessment", "Filling", "Crown", "Cosmetic Repair"};

	public Dentist(String fullName) {
		super (fullName);
	}

	public ArrayList<String> getActivityType() {
		
		return new ArrayList<>(Arrays.asList(workDescription));
	}
}

package cst8284.asgmt4.employee;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Employee {
	private String fullName;
	
	protected Employee() {this("unknown");}
	protected Employee(String fullName) {setName(fullName);}
	protected static Scanner scan = new Scanner(System.in);
	
	public void setName(String fullName) {this.fullName = fullName;}
	public String getName() {return fullName;}
	
	public abstract ArrayList<String> getActivityType();
	
	@Override
	public String toString() {return getName();}
}
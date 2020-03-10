package cst8284.asgmt4.scheduler;


import java.awt.Container;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


import cst8284.asgmt4.employee.Dentist;


/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

public class AppointmentDialog implements ActionListener{
	
	private static final GridBagConstraints textConstants = new GridBagConstraints(
    	0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
    	GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	private static final GridBagConstraints labelConstants = new GridBagConstraints(
    	1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0, 
    	GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
	private static  JButton save;
	private static  JButton find;
    private static  JButton change;
    private static  JButton delete;
    private static  JButton exit;
    public static JFrame f;
    Scheduler s=new Scheduler(new Dentist("Dr. Andrews"));
   
    public static JTextField name;
    public static JTextField phone;
    public static JTextField date;
    public static JTextField time;
    public static JTextField activity;
    public static JComboBox<String> options;
    
 
	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);	
	
	public  void showAppointmentDialog(){
	    f = new JFrame("Get, set, change or delete an appointment");  
	   
	  
	    
	    cp = f.getContentPane();

	    
	    find=new JButton("Find");
	    change=new JButton("Change");
	    delete=new JButton("Delete");
	    exit=new JButton("Exit");
	    save=new JButton("Save");
	    JButton[] btns= {save,find,change,delete,exit};
	   
	    
	    
	    cp.setLayout(new GridBagLayout());
       
	    name=setRow("Enter Client Name (as FirstName LastName):", 'n');
	    phone=setRow("Phone Number (e.g. 613-555-1212):", 'p');
	    date=setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	    time=setRow("Appointment Time:", 't');
	   activity= setRow("Activity Description", 'a');
	    
	    for(int i=0;i<btns.length;i++) {
	    	f.add(btns[i]);
	    	btns[i].addActionListener(this);
	    }
	   
	    
	       	    	
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    //getAct(Scheduler.getEmployee().getActivityType());
	   ArrayList<String> s=Scheduler.getEmployee().getActivityType();
	   AppointmentDialog.f.add(new JLabel("Select Activity Type: "));
		   options=new JComboBox<>();
		  for(int i=0;i<s.size();i++) {
			  options.addItem(s.get(i));
			  
		  }
		  options.setEditable(false);
		  options.setSelectedIndex(0);
		  AppointmentDialog.f.add(options);
	    f.setVisible(true);
	    
	  }
	
	
	
	
	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l; JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
	    cp.add(t = new JTextField(labelWidth), labelConstants);
	    t.setFocusAccelerator(keyboardShortcut);
	    return t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		  if(e.getSource()==find) { //source self study on ActionEvent Object. 
			  s.find();
		  }
		  
		  if(e.getActionCommand().contentEquals("Save")) {
			  s.saveAppointment(s.makeAppointmentFromUserInput());
			  
		  }
if(e.getActionCommand().equals("Change")) {
			  s.changeAppointment(Scheduler.makeCalendarFromUserInput2(false));
		  }
if(e.getActionCommand().equals("Delete")) {
	s.deleteAppointment(Scheduler.makeCalendarFromUserInput2(false));
	  
}
if(e.getActionCommand().equals("Exit")) {
	f.setVisible(false); 
	f.dispose();
}
		  
	}
	
	
}

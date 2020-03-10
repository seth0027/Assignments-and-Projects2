package cst8284.asgmt4.scheduler;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SchedulerViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Toolkit tk = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = tk.getScreenSize();
	public static final JTextArea scrollText = new JTextArea();

	private static ArrayList<String> listOfStrings = null;
	
	public static JFrame frame;
	static Scheduler s=new Scheduler();
	

	public static void load() {
		frame = new JFrame();
		
		frame.setTitle(Scheduler.title);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);
		
		//Uncomment the following code when you have finished TODO #1 and test your code.
		  //Make sure the word list appears in the scroll pane.  Then remove this comment.
		  
		
		
			frame.add(getWestPanel(), BorderLayout.WEST);
			frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
			frame.setPreferredSize(new Dimension(screenX, screenY));

			frame.pack();
			frame.setVisible(true);
		
	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}

	public static JPanel getWestPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(6, 1));
		JPanel westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		/* Uncomment each of the lines below as you create and test your ActionListener classes
		   according to the instructions for TODO #3.  Then delete this comment, load the long
		   word list, test your code by clicking the buttons, and demo your lab to the professor
		 */
		controlPanel.add(setWestPanelBtns(" Save Appointment",e -> new AppointmentDialog().showAppointmentDialog()));
	    controlPanel.add(setWestPanelBtns("  Display Appointment    ",new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				scrollText.setText("");
				try{s.displayAppointment(Scheduler.makeCalendarFromUserInput2(false));}
				catch(BadAppointmentDataException ex) {
					
					JOptionPane.showMessageDialog(SchedulerViewer.frame, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
					scrollText.setText("");
				}
				//scrollText.getText();
				
			}
	    	
	    }));
	    controlPanel.add(setWestPanelBtns("   Diplay Schedule  ",new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollText.setText("");
				try{s.displayDaySchedule(Scheduler.makeCalendarFromUserInput2(true));
				//scrollText.getText();
				}catch(BadAppointmentDataException ex) {
					
					JOptionPane.showMessageDialog(SchedulerViewer.frame, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
					scrollText.setText("");
				}
				
				
				
				
			}
	    	
	    }));
	    controlPanel.add(setWestPanelBtns("     Save Appointments to File      ",new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Scheduler.saveAppointmentsToFile(Scheduler.getAppointments(), "CurrentAppointments.apts");
				
			}
	    	
	    }));
	    controlPanel.add(setWestPanelBtns("     Load Appointments From File     ",new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Scheduler.loadAppointmentsFromFile("CurrentAppointments.apts", Scheduler.getAppointments() );
				
			}
	    	
	    }));
	    controlPanel.add(setWestPanelBtns("     EXIT     ",new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int o=JOptionPane.showConfirmDialog(frame, "Are you sure", "Exit ?", JOptionPane.YES_NO_OPTION);
				if(o==0) {  //references sample code using JOptionPane.
				frame.setVisible(false);
				frame.dispose();    //references stackoverflow.com/questions 
				}
				
			}
	    	
	    }));
		/* Uncomment this section when you are ready to display the file information in the 
		   left panel of the swing GUI as per TODO #2.  Be sure to remove this comment.*/
		 
		
		
		
		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel,ActionListener action) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(action);
		return btn;
	}

	// Adapted from: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
	

	public static ArrayList<String> getListOfStrings() {
		return listOfStrings;
	}

	}

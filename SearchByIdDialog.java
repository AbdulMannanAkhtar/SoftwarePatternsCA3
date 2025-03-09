/*
 * 
 * This is the dialog for Employee search by ID
 * 
 * */

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class SearchByIdDialog extends search {
	
	
	// constructor for SearchByIdDialog 
	public SearchByIdDialog(EmployeeDetails parent) {
		super(parent, "search by ID", "Enter ID: ");
		
	}
	

	@Override
	public String dialogTitle() {
		// TODO Auto-generated method stub
		return "Search By ID";
	}
	


	// action listener for save and cancel button
	public void searchFun() {
			try {
				Double.parseDouble(searchField.getText());
				parent.searchByIdField.setText(searchField.getText());
				parent.searchEmployeeById();
				dispose();
			}// end try
			catch (NumberFormatException num) {
				// display message and set colour to text field if entry is wrong
				searchField.setBackground(new Color(255, 150, 150));
				JOptionPane.showMessageDialog(null, "Wrong ID format!");
			}// end catch
		
	
	}// end actionPerformed


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



	



}// end class searchByIdDialog

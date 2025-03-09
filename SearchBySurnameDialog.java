/*
 * 
 * This is a dialog for searching Employees by their surname.
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

public class SearchBySurnameDialog extends search {
	
	
	// constructor for SearchByIdDialog 
	public SearchBySurnameDialog(EmployeeDetails parent) {
		super(parent, "search by Surname", "Enter Surname: ");
		
	}
	

	@Override
	public String dialogTitle() {
		// TODO Auto-generated method stub
		return "Search By Surname";
	}
	


	// 
	public void searchFun() {
		
		parent.searchByIdField.setText(searchField.getText());
		parent.searchEmployeeBySurname();
		dispose();
		
				
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



	



}// end class searchByIdSurname
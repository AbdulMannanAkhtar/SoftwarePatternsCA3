import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;


public class Navigation {
	
	private RandomFile application;
	private File file;
	private long currentByteStart;
	private Employee currentEmployee;
	
	public Navigation(RandomFile application, File file)
	{
		this.application = application;
		this.file = file;
		
		
	}
	private boolean isSomeoneToDisplay() {
		if(file == null)
		{
			return false;
		}
		boolean someoneToDisplay = false;
		// open file for reading
		application.openReadFile(file.getAbsolutePath());
		// check if any of records in file is active - ID is not 0
		someoneToDisplay = application.isSomeoneToDisplay();
		application.closeReadFile();// close file for reading
		// if no records found clear all text fields and display message
		return someoneToDisplay;
	}// end isSomeoneToDisplay
	
	Employee firstRecord() {
		// if any active record in file look for first record
		if (isSomeoneToDisplay()) {
			// open file for reading
			application.openReadFile(file.getAbsolutePath());
			// get byte start in file for first record
			currentByteStart = application.getFirst();
			// assign current Employee to first record in file
			currentEmployee = application.readRecords(currentByteStart);
			application.closeReadFile();// close file for reading
			// if first record is inactive look for next record
			if (currentEmployee.getEmployeeId() == 0)
				nextRecord();// look for next record
		} // end if
		return currentEmployee;
	}// end firstRecord
	
	// find byte start in file for previous active record
	Employee previousRecord() {
		// if any active record in file look for first record
		if (isSomeoneToDisplay()) {
			// open file for reading
			application.openReadFile(file.getAbsolutePath());
			// get byte start in file for previous record
			currentByteStart = application.getPrevious(currentByteStart);
			// assign current Employee to previous record in file
			currentEmployee = application.readRecords(currentByteStart);
			// loop to previous record until Employee is active - ID is not 0
			while (currentEmployee.getEmployeeId() == 0) {
				// get byte start in file for previous record
				currentByteStart = application.getPrevious(currentByteStart);
				// assign current Employee to previous record in file
				currentEmployee = application.readRecords(currentByteStart);
			} // end while
			application.closeReadFile();// close file for reading
		}
		return currentEmployee;
	}// end previousRecord
	
	Employee nextRecord() {
		// if any active record in file look for first record
		if (isSomeoneToDisplay()) {
			// open file for reading
			application.openReadFile(file.getAbsolutePath());
			// get byte start in file for next record
			currentByteStart = application.getNext(currentByteStart);
			// assign current Employee to record in file
			currentEmployee = application.readRecords(currentByteStart);
			// loop to previous next until Employee is active - ID is not 0
			while (currentEmployee.getEmployeeId() == 0) {
				// get byte start in file for next record
				currentByteStart = application.getNext(currentByteStart);
				// assign current Employee to next record in file
				currentEmployee = application.readRecords(currentByteStart);
			} // end while
			application.closeReadFile();// close file for reading
		} // end if
		return currentEmployee;
	}// end nextRecord
	
	Employee lastRecord() {
		// if any active record in file look for first record
		if (isSomeoneToDisplay()) {
			// open file for reading
			application.openReadFile(file.getAbsolutePath());
			// get byte start in file for last record
			currentByteStart = application.getLast();
			// assign current Employee to first record in file
			currentEmployee = application.readRecords(currentByteStart);
			application.closeReadFile();// close file for reading
			// if last record is inactive look for previous record
			if (currentEmployee.getEmployeeId() == 0)
				previousRecord();// look for previous record
		} // end if
		return currentEmployee;
	}// end lastRecord

}

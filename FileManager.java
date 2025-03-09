import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileManager {
	
	private RandomFile application = new RandomFile();
	// display files in File Chooser only with extension .dat
	private FileNameExtensionFilter datfilter = new FileNameExtensionFilter("dat files (*.dat)", "dat");
	// hold file name and path for current file in use
	private File file;
	
	private String generatedFileName;
	
	
	public FileManager(RandomFile application)
	{
		this.application = application;
	}
	
	public File getFile()
	{
		return file;
	}
	
	public void setFile(File file)
	{
		this.file = file;
	}
	
	void openFile(JFrame frame) {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open");
		fc.setFileFilter(datfilter);
		File newFile; 
		if ( file != null && file.length() != 0) {
			int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			// if user wants to save file, save it
			if (returnVal == JOptionPane.YES_OPTION) {
				saveFile(frame);// save file
			} // end if
		} // end if

		int returnVal = fc.showOpenDialog(frame);
		// if file been chosen, open it
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			newFile = fc.getSelectedFile();
			// if old file wasn't saved and its name is generated file name,
			// delete this file
			if (file.getName().equals(generatedFileName))
			{
				file.delete();// delete file
			}
			file = newFile;// assign opened file to file
			// open file for reading
			application.openReadFile(file.getAbsolutePath());
			application.closeReadFile();// close file for reading
		} // end if
	}// end openFile
	
	// save file
	void saveFile(JFrame frame) {
		// if file name is generated file name, save file as 'save as' else save
		// changes to file
		if (file.getName().equals(generatedFileName)) {
			saveFileAs(frame);
		}	
	// save file as 'save as'
		else {
			
			if(!file.exists())
			{
				JOptionPane.showMessageDialog(frame, "File doesnt exist");
				return;
			}
			
			application.openWriteFile(file.getAbsolutePath());
			application.closeWriteFile();

		} // end else
	}// end saveFile
	
	void saveFileAs(JFrame frame) {
		final JFileChooser fc = new JFileChooser();
		File newFile;
		String defaultFileName = "new_Employee.dat";
		fc.setDialogTitle("Save As");
		// display files only with .dat extension
		fc.setFileFilter(datfilter);
		fc.setApproveButtonText("Save");
		fc.setSelectedFile(new File(defaultFileName));

		int returnVal = fc.showSaveDialog(frame);
		// if file has chosen or written, save old file in new file
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			newFile = fc.getSelectedFile();
			// check for file name
			if (!checkFileName(newFile)) {
				// add .dat extension if it was not there
				newFile = new File(newFile.getAbsolutePath() + ".dat");
				// create new file
				application.createFile(newFile.getAbsolutePath());
			} // end id
			else
				// create new file
				application.createFile(newFile.getAbsolutePath());

			try {// try to copy old file to new file
				Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				// if old file name was generated file name, delete it
				if (file.getName().equals(generatedFileName))
					file.delete();// delete file
				file = newFile;// assign new file to file
			} // end try
			catch (IOException e) {
			} // end catch
		} // end if

	}// end saveFileAs
	
	void createRandomFile() {
		generatedFileName = getFileName() + ".dat";
		// assign generated file name to file
		file = new File(generatedFileName);
		// create file
		application.createFile(file.getName());
	}// end createRandomFile
	
	String getFileName() {
		String fileNameChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-";
		StringBuilder fileName = new StringBuilder();
		Random rnd = new Random();
		// loop until 20 character long file name is generated
		while (fileName.length() < 20) {
			int index = (int) (rnd.nextFloat() * fileNameChars.length());
			fileName.append(fileNameChars.charAt(index));
		}
		String generatedfileName = fileName.toString();
		return generatedfileName;
	}// end getFileName
	
	// check if file name has extension .dat
		private boolean checkFileName(File fileName) {
			boolean checkFile = false;
			int length = fileName.toString().length();

			// check if last characters in file name is .dat
			if (fileName.toString().charAt(length - 4) == '.' && fileName.toString().charAt(length - 3) == 'd'
					&& fileName.toString().charAt(length - 2) == 'a' && fileName.toString().charAt(length - 1) == 't')
				checkFile = true;
			return checkFile;
		}// end checkFileName

		
		
		

}

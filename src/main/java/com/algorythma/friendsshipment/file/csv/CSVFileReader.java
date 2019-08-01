package com.algorythma.friendsshipment.file.csv;

import javax.swing.JFileChooser;


/**
 * @author waseem
 *
 *	This class assume that the CSV file path/directoy is passed to the java runtime like the following:
 *	-DcsvFile=FILE/DIRECTORY path , example -DcsvFile=/home/waseem/Desktop/Backend-Dev-Test/
 *	in case the property was not passed a File chooser will pop up asking the user, to enter the target CSV,
 *	or the directory listing the candidate test CSV files.
 *
 */
public class CSVFileReader {

	public static String prepareSourceCSVFile() {
		String testCaseCSVPath = System.getProperty("csvFile");
			
		if (testCaseCSVPath == null) {
	
			javax.swing.JFileChooser csvFileChooser = new JFileChooser("Please Select CSV file");
			csvFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			csvFileChooser.showDialog(null,"CSV File/Folder");
			
			testCaseCSVPath = csvFileChooser.getSelectedFile().getAbsolutePath();
	
		}
		return testCaseCSVPath;
	}
	

}

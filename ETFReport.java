package ChocAn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class generates the Electronic Funds Transfer Report
 * This report returns the electronic funds transfer data compiled 
 * during the week.
 * 
 * @author Jacoby Benger
 */

public class ETFReport {
	private String providerName;
	private int providerID;
	private double weeklyTotalFee;
	
	private File report;
	private String fileName;
	
	private ChocAnProvider provider;
	
	/**
	 * Default constructor of report
	 */
	public ETFReport(int ID)
	{
		providerName = "";
		providerID = ID;
		weeklyTotalFee = 0.0;
	}
	
	/**
	 * This next function needs to be able to iterate through the provider directory and create a 
	 * report for each provider
	 * @param num is the ID number of the corresponding provider to be paid
	 */
	public ETFReport(ChocAnDirectory mainDirectory, int num) 
	{
		providerID = num;
		provider = mainDirectory.findProvider(providerID);
		providerName = provider.providerName;
		weeklyTotalFee = provider.getWeeklyFee();
		fileName = providerName + "_ETFReciept.txt";
	}
	
	/**
	 * This function creates the electronic funds transfer report
	 */
	public void createReport() 
 	{
 		FileWriter fw = null;
 		PrintWriter pw = null;
 		
 		try {
			fw = new FileWriter(fileName);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

 		pw = new PrintWriter(fw);
 		    
 	    pw.println("-----WEEKLY ELECTRONIC FUNDS TRANSFER RECEIPT-----");
 	    pw.printf("Provider: %s", providerName);
		pw.printf("Provider ID: %d", providerID);
 	    pw.println();
 	    pw.printf("Weekly Total Fee: %.2f", weeklyTotalFee);

 		
	    try {
	    	if (pw != null)	pw.close();
	    	if (fw != null) fw.close();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
 		report = new File(fileName);
 	}	
}

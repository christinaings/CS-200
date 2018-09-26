package ChocAn;
import java.io.*;

/**
 * 
 * @author Jacoby Benger
 * This class is responsible for creating a report that outlines the 
 * services that a Provider provided for that given week. 
 *
 */
public class ProviderSummaryReport {
	private String providerName;
	private int providerID;
	private String providerAddress;
	private String providerCity;
	private String providerState;
	private int providerZIP;
	
	private double totalFee;
	
	private String fileName;
	private File report;
	
	private ChocAnProvider prov;
	
	/**
	 * Default Constructor
	 */
	public ProviderSummaryReport() 
	{
		providerName = "";
		providerID = 0;
		providerAddress = "";
		providerCity = "";
		providerState = "";
		providerZIP = 0;
	}
	
	/**
	 * Explicit Constructor 
	 * @param IDnum is the provider 9-digit ID number
	 * @param mainDirectory is the up-to-date directory of members and providers
	 */
	public ProviderSummaryReport(ChocAnDirectory mainDirectory, int IDnum) 
	{
		prov = mainDirectory.findProvider(IDnum);
		providerName = prov.getProviderName();
		providerID = IDnum;
		providerAddress = prov.getProviderAddress();
		providerCity = prov.getProviderCity();
		providerState = prov.getProviderState();
		providerZIP = prov.getProviderZip();
		fileName = providerName + "_ProviderSummaryReport.txt";
		totalFee = prov.getWeeklyFee();
	}
	/**
	 * Writes and formats Provider Report Class
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
 		    
 	    pw.println("----WEEKLY SERVICES PROVIDED REPORT----");
 	    pw.printf("Name: %s \n", providerName);
 	    pw.printf("ID Number: %d \n", providerID);
 	    pw.printf("Address: %s \n", providerAddress);
 	    pw.printf("City: %s \n", providerCity);
 	    pw.printf("State: %s \n", providerState);
 	    pw.printf("ZIP: %d \n", providerZIP);
 	    pw.println();
 	    pw.printf("Weekly Bills Total: %.2f \n", totalFee);
 	    pw.println();
 	    pw.println("Services Provided this week: ");

 		
 		//Reading in From Provider Class Record and writing to new file
 		String line;
 		String provReport = prov.getWeeklyServiceRecord();
 		FileReader read = null;
 		BufferedReader br = null;
 		try {
 			read = new FileReader(provReport);
 			br = new BufferedReader(read);
 			
 	 		while((line = br.readLine()) != null)
 	 		{
 	 			pw.println(line);
 	 		}
 		}catch(IOException e) {
 			e.printStackTrace();
 		}
 		
 		
	    try {
	    	if (pw != null)	pw.close();
	    	if (fw != null) fw.close();
	    	if (read != null) read.close();
	    	if (br != null) br.close();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
 		report = new File(fileName);
 	}
 	
 	
}
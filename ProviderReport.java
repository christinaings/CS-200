package ChocAn;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to create the weekly services provided report for ChocAn Providers
 */

public class ProviderReport {
	private String providerName;
	private int providerID;
	private String providerAddress;
	private String providerCity;
	private String providerState;
	private int providerZIPCode;
	public String providerEmail;
	private double serviceFEE;
	public String userComment;
	private String comment;
	
	private String fileName;
	private File report;
	
	private ChocAnDirectory dir;
	private ChocAnProvider provider;
	private ProviderServiceDirectory prov;

	/**
	 * Default Constructor
	 */
	
	public ProviderReport()
	{
		providerName = "";
		providerID = 0;
		providerAddress = "";
		providerCity = "";
		providerState = "";
		providerZIPCode = 0;
		providerEmail = "";
		serviceFEE = 0.0;
		dir = new ChocAnDirectory();
	}
	
	/**
	 * Explicit constructor using the Provider ID as a parameter
	 */
	
	public ProviderReport(int num)
	{
		providerID = num;
		dir = new ChocAnDirectory();
		provider = dir.findProvider(providerID);
		providerName = provider.providerName;
		providerAddress = provider.providerAddress;
		providerCity = provider.providerCity;
		providerState = provider.providerState;
		providerZIPCode = provider.providerZipCode;
		providerEmail = provider.providerEmail;
		serviceFEE = provider.weeklyTotalFee;
		fileName = providerName + "_report.txt";		
	}
	
	/**
	 * This function generates the report
	 */
	
	public void createReport() 
 	{
 		FileWriter fw = null;
 		PrintWriter pw = null;
 		
 		report = new File(fileName);
 		try {
			report.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 		try 
 		{
 			fw = new FileWriter(report); 
 		    pw = new PrintWriter(fw);
 		    
 		    pw.println("-----SERVICE PROVIDED REPORT-----");
 		    pw.printf("Provider: %s", providerName);
			pw.printf("Provider ID Number: %d", providerID);
			pw.printf("Address: %s", providerAddress);
			pw.printf("City: %s", providerCity);
			pw.printf("State: %s", providerState);
			pw.printf("ZIP: %d", providerZIPCode);
			pw.printf("Provider Email: %s", providerEmail);
 		    pw.println();
 		    pw.printf("Total Fee for Service Provided: %s", serviceFEE);
 		    pw.printf("Additional Comments: %s", comment); 
 		}
 		catch(IOException e)
 		{
 			e.printStackTrace();
 		}
 	}
	
	/**
	 * This function pulls the service fee using the serviceCode as an integer parameter
	 */
	
	public ChocAnService pullServiceFee(int num2)
	{
		int serviceCode = num2;
		ChocAnService serviceFee;
		prov = new ProviderServiceDirectory();
		serviceFee = prov.searchForService(serviceCode);
	    return serviceFee;
	}
	
	/**
	 * Call this function in the reportGenerator class/Interactive module
	 * to add a user comment parameter to the report
	 * @param userComment
	 */
	
	public void addComment(String userComment)
	{
			comment = userComment;			 
	}	
}	

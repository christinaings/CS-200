package ChocAn;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Christina Alexander
 *
 */

public class ChocAnProvider {
	public int providerID;
	protected String providerName;
	protected String providerAddress;
	protected String providerCity;
	protected String providerState;
	protected int providerZipCode;
	protected String weeklyServiceRecord;
	protected double weeklyTotalFee;
	protected String providerEmail;
	
	public ChocAnProvider(ChocAnDirectory mainDirectory) {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		providerID = generateID(mainDirectory);
		System.out.println("Now creating new provider...");
		System.out.println("Input Provider Name:");
		try
		{
			providerName = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter Street Address:");
		try
		{
			providerAddress = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter State:");
		try
		{
			providerState = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter City:");
		try
		{
			providerCity = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter Six Digit Zip Code:");
		try
		{
			providerZipCode = Integer.parseInt(bufferRead.readLine());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter Provider Email:");
		try
		{
			providerEmail = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		weeklyServiceRecord =  Integer.toString(providerID) + providerName + Integer.toString(Calendar.WEEK_OF_YEAR) + Integer.toString(Calendar.YEAR);
		
		System.out.println("New Provider Created with a Provider ID: " + providerID);
	}
	
	/**
	 * This Constructor allows the Interactive terminal to create a Provider with a specified providerID
	 * @param providerID
	 * 
	 */
	public ChocAnProvider(int providerID) 
	{
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		this.providerID = providerID;
		System.out.println("Now creating new provider...");
		System.out.println("Input Provider Name:");
		try
		{
			providerName = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter Street Address:");
		try
		{
			providerAddress = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter State:");
		try
		{
			providerState = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter City:");
		try
		{
			providerCity = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter Six Digit Zip Code:");
		try
		{
			providerZipCode = Integer.parseInt(bufferRead.readLine());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Enter Provider Email");
		try
		{
			providerEmail = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		weeklyServiceRecord =  Integer.toString(providerID) + providerName + Integer.toString(Calendar.WEEK_OF_YEAR) + Integer.toString(Calendar.YEAR);
		
		System.out.println("New Provider Created with a Provider ID: " + providerID);
	}
	/**
	 * Creates a provider based on the given parameters
	 * @param name
	 * @param Address
	 * @param City
	 * @param State
	 * @param zip
	 * @param email
	 */
	
	public ChocAnProvider(String name, String Address, String City, String State, int zip, String email,int ID)
	{
		providerName = name;
		providerAddress = Address;
		providerCity = City;
		providerState = State;
		providerZipCode = zip;
		providerEmail = email;
		providerID = ID;
		weeklyServiceRecord =  Integer.toString(providerID) + providerName + Integer.toString(Calendar.WEEK_OF_YEAR) + Integer.toString(Calendar.YEAR);
	
	}
	
	/**
	 * generates random 9 digit ID for the provider
	 * @return
	 */
	private int generateID(ChocAnDirectory mainDirectory) {
		Random rand = new Random();
		int num = 0;
	
		while(mainDirectory.doesProviderExist(num) )
			num = rand.nextInt(900000000) + 100000000;
		return num;
	}
	
	/**
	 * This displays the Provider's information
	 */
	public void viewInfo() {
		System.out.println("Provider Name: " + providerName+ "\n"
				+ "Provider Address: " + providerAddress+ "\n"
				+ "Provider City: " + providerCity+ "\n"
				+ "Provider Email: " + providerState+ "\n"
				+ "Provider ZipCode: " + providerZipCode);
		return;
	}
	
	/**
	 * This allows you to add a service to the provider's weekly service record
	 * @param s
	 */
	public void addService(String s)
	{
		FileWriter fw = null;
		BufferedWriter bw = null;

	    byte data[] = s.getBytes();
	    
	    Path p = Paths.get(weeklyServiceRecord);
	    if (!(new File(weeklyServiceRecord).exists()))
	    {
	    	try (OutputStream out = new BufferedOutputStream(
	    			Files.newOutputStream(p, CREATE, APPEND))) {
	    		out.write(data, 0, data.length);
	    	} catch (IOException x) {
	    		System.err.println(x);
	    	}
	    }
	    else
	    {
	    	File file = new File(weeklyServiceRecord);
	    	try{
	    		fw = new FileWriter(file,true);
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	bw = new BufferedWriter(fw);
	    	try{
	    		bw.write(s);
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
	    try {
	    	if (bw != null)	bw.close();
	    	if (fw != null) fw.close();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	}

	/**
	 * 
	 * @return provider name
	 */
	public String getProviderName()
	{
		return providerName;
	}
	
	/**
	 * 
	 * @return providers address
	 */
	public String getProviderAddress()
	{
		return providerAddress;
	}
	
	/**
	 * 
	 * @return providers state
	 */
	public String getProviderState()
	{
		return providerState;
	}
	
	/**
	 * 
	 * @return providers city
	 */
	public String getProviderCity()
	{
		return providerCity;
	}
	
	/**
	 * 
	 * @return provider Zip Code
	 */
	public int getProviderZip()
	{
		return providerZipCode;
	}
	
	/**
	 * 
	 * @return the total weekly fee
	 */
	public double getWeeklyFee()
	{
		return weeklyTotalFee;
	}
	
	/**
	 * updates the weekly fee
	 * @param fee
	 */
	public void updateWeeklyFee(double fee)
	{
		weeklyTotalFee = fee;
	}
	public String getEmail()
	{
		return providerEmail;
	}
	public String getWeeklyServiceRecord()
	{
		return weeklyServiceRecord;
	}
}

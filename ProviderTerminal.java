package ChocAn;
import java.util.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;


/**
 * @author Christina Alexander
 */
public class ProviderTerminal {
	private int providerID;

	
	/**
	 * Provider Terminal Constructor
	 * @param num
	 */
	public ProviderTerminal(int num)
	{
		providerID = num;
	}
	/**
	 * runs the provider terminal
	 * @throws IOException 
	 */
	public void runProviderTerminal(ChocAnDirectory mainDirectory) {
		Scanner scanProvider = new Scanner(System.in);
		System.out.println("Welcome to the Provider Terminal\n\n"
				+ "Enter 'Add Service' to register a New Service \n"
				+ "Enter 'Request Provider Directory' to Recieve a Copy of the Provider Directory \n"
				+ "Enter 'Quit' to exit the Provider Terminal\n");
		String inputString = scanProvider.nextLine();
		while ( !("Quit".equals(inputString)) ) {
			
			if (inputString.equals("Add Service")) {
				billChocAn(mainDirectory);
				System.out.println("Service Added");
			}
			else if (inputString.equals("Request Provider Directory")) 
			{
				try {
					requestProviderDirectory();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (inputString.equals("Quit")) { 
				return;
			}
			System.out.println("Enter 'Add Service' to register a New Service \n"
				+ "Enter 'Request Provider Directory' to Recieve a Copy of the Provider Directory \n"
				+ "Enter 'Quit' to exit the Provider Terminal\n");
			inputString = scanProvider.nextLine();
		}
		return;
	}
	/**
	 * verifies the member
	 * @param ID
	 * @return true or false
	 */
	public boolean verifyMember(ChocAnDirectory mainDirectory, int ID)
	{
		return mainDirectory.doesMemberExist(ID);
	}
	/**
	 * generates the information for the new service
	 * @param memberID
	 * @return information for new service
	 */
	private String generateDATA(ChocAnDirectory mainDirectory, String memberID)
	{
		String outData = "",tempData = "";
		
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		outData = format.format(c.getTime()) + '\n';
		
		System.out.println("Enter Date Service Was Rendered in Format MM-DD-YYYY or \"QUIT\" to exit");
			try{
				tempData = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			tempData.toUpperCase();
			
		
		if (tempData.equals("QUIT")) return "exit";
		outData += tempData + '\n' + Integer.toString(providerID) + '\n'+ memberID + '\n';
		String ServiceCode = "";
		ProviderServiceDirectory dir = new ProviderServiceDirectory();
		
		ChocAnService service;
		
		while(true)
		{
			System.out.println("Input Service Code Number or \"QUIT\" to Quit");
			try{
				ServiceCode = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			if (ServiceCode.equals("QUIT"))	return "exit";
			if (dir.verifyService(Integer.parseInt(ServiceCode)))
			{
				service = dir.searchForService(Integer.parseInt(ServiceCode));
				System.out.println(service.getDescriptionString() + "\n" + "Correct Service? Enter 'yes' or 'no'");
				try{
					tempData = bufferRead.readLine();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				if (tempData.equals("yes")) break;
			}
			else
				System.out.println("Invalid Service Code...");
		}
		
		if (ServiceCode.equals("exit"))	return "exit";
		outData += ServiceCode + '\n';
		tempData = "";

		System.out.println("Enter Any Additional Comments. Enter \"EXIT\" to Exit.");
		try{
			tempData = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		tempData.toUpperCase();
		
		String comments = "";
		while (!tempData.equals("EXIT")) {
			try {
			tempData = bufferRead.readLine();
			comments += tempData + " ";
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			tempData.toUpperCase();
		}
		outData += comments + "\n";
		String currFee = service.getCostString();
		System.out.println("Fee to be Paid is " + currFee);
		ChocAnProvider provider = mainDirectory.findProvider(providerID);
		double fee = provider.getWeeklyFee() + Double.parseDouble(currFee);
		provider.updateWeeklyFee(fee);
		
		return outData;
		
	}
			
	/**
	 * calls generateData and stores the information in the correct provider and member files
	 */
	private void createServiceRecord(ChocAnDirectory mainDirectory){
		
		String memberID = "";
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		System.out.println("Input Nine Digit Member ID");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try{
			memberID = bufferRead.readLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		if ( mainDirectory.doesMemberExist(Integer.parseInt(memberID)) == false)
			{
			System.out.println("This is Not a Valid Member. Now Exiting the ChocAn System...");System.exit(0);
			}
		ChocAnMember mem = mainDirectory.findMember(Integer.parseInt(memberID));
		
		System.out.println(mem.getMemberStatus());
		
		if (mem.getMemberStatus().equals("Invalid") || mem.getMemberStatus().equals("Member Suspended")) return;
		
		String s = Integer.toString(providerID);
		s += memberID;
		String toWrite = generateDATA(mainDirectory, memberID);
		
		if (toWrite.equals("exit")) return;
	    byte data[] = toWrite.getBytes();
	    
	    Path p = Paths.get(s);
	    if (!(new File(s).exists()))
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
	    	File file = new File(s);
	    	try {
	    		
	    	fw = new FileWriter(file,true);
	    	}
	    	catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	bw = new BufferedWriter(fw);
	    	try {
	    	bw.write(toWrite);
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
	    ChocAnMember member = mainDirectory.findMember(Integer.parseInt(memberID));
	    member.addService(toWrite);
	    
	    ChocAnProvider provider = mainDirectory.findProvider(providerID);
	    provider.addService(toWrite);
	}
	/**
	 * Creates a new bill to ChocAn
	 */
	private void billChocAn(ChocAnDirectory mainDirectory)
	{
		createServiceRecord(mainDirectory);
	}
	/**
	 * Requests the Provider Service Directory and emails it to the Provider
	 * @throws IOException 
	 */
	private void requestProviderDirectory() throws IOException
	{
			ProviderServiceDirectory dir = new ProviderServiceDirectory();
			File source = dir.compileDirectory();
			File dest = new File("ProviderServiceDirectory.txt");
			dest.createNewFile();
			Path path = Paths.get(source.getAbsolutePath());
			OutputStream os = new FileOutputStream(dest);
			Files.copy(path,os);
		System.out.println("Sent message successfully....");
	}
}
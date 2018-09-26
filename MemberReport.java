 package ChocAn;
 import java.io.*;
 
/**
 * 
 * @author Jacoby Benger
 * This class is responsible for creating a report that outlines the weekly services 
 * received for ChocAn members.
 */
 public class MemberReport {
 	private String memberName;
 	private int memberID;
 	private String memberAddress;
 	private String memberCity;
 	private String memberState;
 	private int memberZIP;
 	
// 	private int weeklyTotal; //can this be written into the member object class
 	
// 	private String filePath; //where do these files need to be created?? 
 	private String fileName;
 	private File report;
 	
 	private ChocAnDirectory dir;
 	private ChocAnMember member;
 	
 	/**
 	 * Default Constructor 
 	 */
 	public MemberReport() 
 	{
 		memberName = "";
 		memberID = 0;
 		memberAddress = "";
 		memberCity = "";
 		memberState = "";
 		memberZIP = 0;
 		dir = new ChocAnDirectory();
 	}
 	
 	/**
 	 * Explicit Constructor
 	 * @param num is the members 9-digit ID number
 	 */
 	public MemberReport(ChocAnDirectory mainDirectory, int num) 
 	{
 		memberID = num;
 		member = mainDirectory.findMember(memberID);
 		memberName = member.memberName;
 		memberAddress = member.memberAddress;
 		memberCity = member.memberCity;
 		memberState = member.memberState;
 		memberZIP = member.memberZipCode;
 		fileName = memberName + "_MemberReport.txt";
 	}
 	
 	/**
 	 * Writes and formats Member info into text file
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
 		    
 	    pw.println("----WEEKLY SERVICES RECIEVED REPORT----");
 	    pw.printf("Name: %s \n", memberName);
 	    pw.printf("ID Number: %d \n", memberID);
 		pw.printf("Address: %s \n", memberAddress);
 	    pw.printf("City: %s \n", memberCity);
 	    pw.printf("State: %s \n", memberState);
	    pw.printf("ZIP: %d \n", memberZIP);
 	    pw.println();
	    pw.println("Services Received this week: ");
 		
 		//Reading in From Member Class Record and writing to new file
 		String line;
 		String memReport = member.weeklyServicesRecieved();
 		FileReader read = null;
 		BufferedReader br = null;
 		try {
 			read = new FileReader(memReport);
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
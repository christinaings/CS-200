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
public class ChocAnMember {
	public int memberID;
	protected String membershipStatus;
	protected String memberName;
	protected String memberAddress;
	protected String memberState;
	protected String memberCity;
	protected String memberEmail;
	protected int memberZipCode;
	public String recordFilePath;
	
	
		public ChocAnMember(String name,String address, String state, String city, int zip, String email,int ID)
		{
			int id = ID;
			int numDigits = 0;
			while(id > 0)
			{
				id = id / 10;
				++numDigits;
			}
			if (numDigits != 9) {
				System.out.println("Invalid ID Number");
				return;
			}
			membershipStatus = "Valid";
			memberName = name;
			memberAddress = address;
			memberState = state;
			memberCity = city;
			memberEmail = email;
			memberZipCode = zip;
			memberID = ID;
			recordFilePath =  Integer.toString(memberID) + memberName + Integer.toString(Calendar.WEEK_OF_YEAR) + Integer.toString(Calendar.YEAR);
		}
		/**
		 * Main constructor for ChocAn members
		 */
		public ChocAnMember(ChocAnDirectory mainDirectory)
		{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			memberID = generateID(mainDirectory);
			membershipStatus = "Valid";
			System.out.println("Input Member Name:");
			
			try
			{
				memberName = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter Street Address:");
			try
			{
				memberAddress = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter State:");
			try
			{
				memberState = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter City:");
			try
			{
				memberCity = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter Six Digit Zip Code:");
			try
			{
				memberZipCode = Integer.parseInt(bufferRead.readLine());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter Email:");
			try
			{
				memberEmail = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

			recordFilePath =  Integer.toString(memberID) + memberName + Integer.toString(Calendar.WEEK_OF_YEAR) + Integer.toString(Calendar.YEAR);
			System.out.println("New Member Activated with a Member ID: "+ memberID);
		}
		
		/**
		 * 
		 * @param memberID
		 * This constructor allows the interactive terminal to create a member with a certain inputted ID number
		 */
		public ChocAnMember(int memberID) 
		{
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			this.memberID = memberID;
			membershipStatus = "Valid";
			System.out.println("Input Member Name:");
			
			try
			{
				memberName = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter Street Address:");
			try
			{
				memberAddress = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter State:");
			try
			{
				memberState = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter City:");
			try
			{
				memberCity = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter Six Digit Zip Code:");
			try
			{
				memberZipCode = Integer.parseInt(bufferRead.readLine());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Enter Email:");
			try
			{
				memberEmail = bufferRead.readLine();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

			recordFilePath =  Integer.toString(memberID) + memberName + Integer.toString(Calendar.WEEK_OF_YEAR) + Integer.toString(Calendar.YEAR);
			System.out.println("New Member Activated with a Member ID: "+ memberID);
			
		}
	
		/**
		 * 
		 * generates a random 9 digit ID
		 * @return randomly generated ID
		 */
		private int generateID(ChocAnDirectory mainDirectory) {
			Random rand = new Random();
			int num = 0;
		
			while( mainDirectory.doesMemberExist(num) )
				num = rand.nextInt(900000000) + 100000000;
			return num;
		}
	

		/**
		 * This displays the member's information
		 */
		public void viewInfo() {
			System.out.println("Member Status: " + membershipStatus+ "\n"
					+ "Member Name: " + memberName+ "\n"
					+ "Member Address: " + memberAddress+ "\n"
					+ "Member State: " + memberState+ "\n"
					+ "Member City: " + memberCity+ "\n"
					+ "Member Email: " + memberEmail+ "\n"
					+ "Member ZipCode: " + memberZipCode);
					return;
		}
		
		/**
		 * This will add a service to the member's weekly service records
		 * @param s
		 */
		public void addService(String s)
		{
			FileWriter fw = null;
			BufferedWriter bw = null;

		    byte data[] = s.getBytes();
		    
		    Path p = Paths.get(recordFilePath);
		    if (!(new File(recordFilePath).exists()))
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
		    	File file = new File(recordFilePath);
		    	try {
					fw = new FileWriter(file,true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	bw = new BufferedWriter(fw);
		    	try {
					bw.write(s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
		 * returns the location of the member's weekly services received
		 * @return
		 */
		public String weeklyServicesRecieved()
		{
			return recordFilePath;
		}
		
		/**
		 * this updates the file location of the member's weekly services recieved
		 * @param filePath
		 */
		public void setFileLocation(String filePath)
		{
			recordFilePath = filePath;
		}
		
		/**
		 * This allows the member's status to be changed
		 * @param status
		 */
		public void updateMemberStatus(String status)
		{
			membershipStatus = status;
		}
		
		/**
		 * this returns the member's current status
		 * @return
		 */
		public String getMemberStatus()
		{
			return membershipStatus;
		}
}

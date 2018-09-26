package ChocAn;

import java.util.Scanner;

//import com.opencsv.*;

import java.io.*;

/**
 * 
 * @author Cole Gannaway
 *
 */
public class InteractiveTerminal {
	ChocAnMember newMember;
	ChocAnProvider newProvider;
	private String inputOption = "";
	private int inputID;
	
	/**
	 * 
	 *  Constructor that does nothing because it is in essance an interface
	 */
	public InteractiveTerminal( ) 
	{
	}
	
	/**
	 * An interface that uses a Scanner 'in' that saves input to a String to choose a function to run
	 * If the input String is 'Quit', the program exits the Interactive Mode
	 * The Scanner 'in' also saves input to an int 'inputID' if a memberID or providerID is needed as a parameter of a function
	 * @param mainDirectory
	 */
	public void runInteractiveMode(ChocAnDirectory mainDirectory) 
	{		
	  	Scanner in = new Scanner(System.in);
	  	Scanner inputString = new Scanner(System.in);
		printInteractiveModeMenu();
		inputOption = inputString.nextLine();

		while (inputOption != ("Quit") ) {
			if ("Create Member".equals(inputOption)) 
			{
				createNewMember(mainDirectory);
			}
			else if ("Create Provider".equals(inputOption)) 
			{
				createNewProvider(mainDirectory);
			}
			else if ( "Update Member".equals(inputOption) ) 
			{
				System.out.println("Enter ID Number: ");
				inputID = in.nextInt();
				updateMember(mainDirectory,inputID);
			}
			else if ("Update Provider".equals(inputOption)) 
			{
				System.out.println("Enter ID Number: ");
				inputID = in.nextInt();
				updateProvider(mainDirectory, inputID);
			}
			else if ("Generate".equals(inputOption)) 
			{
				reportGenerator generator = new reportGenerator();
				System.out.println("Possible Reports - MemberReport, ProviderSummaryReport, ETFReport");
				System.out.println("Enter Type of Report: ");
				String reportType = inputString.nextLine();
				System.out.println("Enter ID Number: ");
				int ID = in.nextInt();
				generator.generate(mainDirectory, ID, reportType);
			}
			else if ("View Member".equals(inputOption)) 
			{
				System.out.println("Enter ID Number: ");
				inputID = in.nextInt();
				viewMember(mainDirectory,inputID);
			}
			else if ("View Provider".equals(inputOption)) 
			{
				System.out.println("Enter ID Number: ");
				inputID = in.nextInt();
				viewProvider(mainDirectory,inputID);
			}
			else if ("Delete Member".equals(inputOption)) 
			{
				System.out.println("Enter ID Number: ");
				inputID = in.nextInt();
				deleteMember(mainDirectory, inputID);
			}
			else if ( "Delete Provider".equals(inputOption)) {
				System.out.println("Enter ID Number: ");
				inputID = in.nextInt();
				deleteProvider(mainDirectory, inputID);
			}
			else if ("Quit".equals(inputOption)) {
				return;
			}
			else 
			{
				System.out.println("Error something went wrong try again"); 
			}		
			printInteractiveModeMenu();
			inputOption = inputString.nextLine();
		}
		return;
	}
	
	/**
	 * This Prints the Menu for the User to pick from a variety of functions
	 */
	private void printInteractiveModeMenu() 
	{
		System.out.println("\nWelcome to Interactive Mode\n"
				+ "Enter 'Create Member' to register a New Member \n"
				+ "Enter 'Create Provider' to register a New Provider \n"
				+ "Enter 'Update Member' to update a Member Account \n"
				+ "Enter 'Update Provider' to update a Provider Account \n"
				+ "Enter 'View Member' to see Member's info\n"
				+ "Enter 'View Provider' to see Provider's info\n"
				+ "Enter 'Generate' to generate any type of report\n"
				+ "Enter 'Delete Member' to delete a current Member account \n"
				+ "Enter 'Delete Provider' to delete a current Provider account\n"
				+ "Enter 'Quit' to exit Interactive Mode\n");
	}
	
	/**
	 * A newMember object is created and updated in the mainDirectory
	 * @param mainDirectory
	 */
	public void createNewMember(ChocAnDirectory mainDirectory)
	{
		newMember = new ChocAnMember(mainDirectory);
		mainDirectory.registerMember( newMember );
	}
	
	/**
	 * A newProvider object is created and updated in the mainDirectory
	 * @param mainDirectory
	 */
	private void createNewProvider(ChocAnDirectory mainDirectory)
	{
		newProvider = new ChocAnProvider(mainDirectory);
		mainDirectory.registerProvider( newProvider );
	}
	
	/**
	 * The if the memberID key is exists then the Member object will be removed from the HashMap in the mainDirectory
	 * @param mainDirectory
	 * @param memberID
	 */
	private void deleteMember(ChocAnDirectory mainDirectory, int memberID)
	{
		if (mainDirectory.doesMemberExist(memberID) == false)
	  	{
	  		System.out.println("Error: Member not found");
	  		return;
	  	}
		mainDirectory.deleteMember(memberID);
		
	}
	
	/**
	 * The if the providerID key is exists then the Provider object will be removed from the HashMap in the mainDirectory
	 * @param mainDirectory
	 * @param providerID
	 */
	private void deleteProvider(ChocAnDirectory mainDirectory, int providerID) 
	{
		if (mainDirectory.doesProviderExist(providerID) == false)
	  	{
	  		System.out.println("Error: Provider not found");
	  		return;
	  	}
		mainDirectory.deleteProvider(providerID);
	}
	
	/**
	 * if the Member exists, its information will be displayed, a new Member will be created, and its inputed information will overwrite the current Member
	 * if it does not exist, then an error message will be printed 
	 * @param mainDirectory
	 * @param memberID
	 */
	public void updateMember(ChocAnDirectory mainDirectory, int memberID) 
	{
	  	if (mainDirectory.doesMemberExist(memberID) == false)
	  	{
	  		System.out.println("Error: Member not found");
	  		return;
	  	}
	  	mainDirectory.findMember(memberID).viewInfo();
	  	System.out.println("\n**Enter information to overwrite current Member**");
	  	newMember = new ChocAnMember(memberID);
	  	mainDirectory.writeOverMember(newMember);
	  	return;
	}
	
	/**
	 * if the Provider exists, its information will be displayed, a new Provider will be created, and its inputed information will overwrite the current Provider
	 * if it does not exist, then an error message will be printed 
	 * @param mainDirectory
	 * @param providerID
	 */
	public void updateProvider(ChocAnDirectory mainDirectory,int providerID)
	{
		if (mainDirectory.doesProviderExist(providerID) == false)
	  	{
	  		System.out.println("Error: Provider not found");
	  		return;
	  	}
	  	mainDirectory.findProvider(providerID).viewInfo();
	  	System.out.println("\n**Enter information to overwrite current Provider**");
	  	newProvider = new ChocAnProvider(providerID);
	  	mainDirectory.writeOverProvider(newProvider);
	  	return;
	}
	
	/**
	 * if the Member exists, its information will be displayed
	 * if it does not exist, then an error message will be printed 
	 * @param mainDirectory
	 * @param memberID
	 */
	private void viewMember(ChocAnDirectory mainDirectory,int memberID) {
		if (mainDirectory.doesMemberExist(memberID) == false)
	  	{
	  		System.out.println("Error: Member not found");
	  		return;
	  	}
		mainDirectory.viewMemInfo(memberID);
		return;
	}
	
	/**
	 * if the Provider exists, its information will be displayed
	 * if it does not exist, then an error message will be printed
	 * @param mainDirectory
	 * @param providerID
	 */
	private void viewProvider(ChocAnDirectory mainDirectory,int providerID) {
		if (mainDirectory.doesProviderExist(providerID) == false)
	  	{
	  		System.out.println("Error: Provider not found");
	  		return;
	  	}
		mainDirectory.viewProvInfo(providerID);
		return;
	}
	
}

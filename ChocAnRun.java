package ChocAn;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Christina Alexander
 *
 */

public class ChocAnRun {
	private static int readInt() {
		int value = 0;
		Scanner scan = new Scanner(System.in);
		value = scan.nextInt();
		return value;
	}
	private static String readString() {
		String word = "";
		Scanner scan = new Scanner(System.in);
		word = scan.nextLine();
		return word;
	}
	/**
	 * 
	 * @param args
	 * Allows the user to navigate between the three ChocAn Terminals
	 * @throws IOException 
	 */
  public static void main(String[] args)
	{
		/* set the reference variable to the new Directory*/
		ChocAnDirectory mainDirectory = new ChocAnDirectory();
		
	  	/* set the reference variables to the new Directory and HashMap*/
	  	mainDirectory.mDir = new HashMap<Integer, ChocAnMember>();					  	
		mainDirectory.pDir = new HashMap<Integer, ChocAnProvider>();
		
		/* add a member to the HashMap directly via main*/
		mainDirectory.registerMember(new ChocAnMember("Morty","23 Crazy Street", "Wisconsin", "CrazyTown", 99999, "morty@gmail.com",123456789));
		mainDirectory.registerProvider(new ChocAnProvider("Nick Saban", "990 University Blvd", "Alabama", "Tuscaloosa", 35401, "rtr@alabama.com", 987654321));
		
		String in = "",pass = "";
		Scanner scan = new Scanner(System.in);
		Scanner scanID = new Scanner(System.in);
		while(!in.equals("exit"))
		{
			System.out.println("Welcome to ChocAn."+'\n' + "Please Select the Terminal You Would Like to Access:");
			System.out.println("1. Interactive Terminal");
			System.out.println("2. Provider Terminal");
			System.out.println("3. Acme Terminal");
			System.out.println("EXIT: exit");
			
			in = readString();
			in.toLowerCase();
			
			if (in.equals("interactive terminal") || in.equals("1"))
			{
				while (!pass.equals("return"))
				{
					System.out.println("Please Enter Password:");
					pass = readString();
					pass.toLowerCase();
					if (pass.equals("CS200DreamTeam"))
					{
						/*declare a terminal   */
					  	InteractiveTerminal term = new InteractiveTerminal();
						term.runInteractiveMode(mainDirectory);
						in = "";
					}
					else
					{
						System.out.println("Invalid Password. Reenter Password or Enter 'return' to Select a Different Terminal");
					}
				}
			}
			else if (in.equals("provider terminal") || in.equals("2"))
			{
				int ID = 0;
				while(ID != -1)
				{
					ID = 0;
					System.out.println("Enter Your Provider ID or Enter '-1' to Return and Select a Different Terminal.");
					ID = scanID.nextInt();
					if (ID == -1) 
					{	
					}
					else 
					{
						if( mainDirectory.doesProviderExist(ID))
						{
							ProviderTerminal term = new ProviderTerminal(ID);
							term.runProviderTerminal(mainDirectory);
							in = "";
						}
						else 
						{
							System.out.println("Invalid ID");
						}
					}
				}
			}
			else if (in.equals("acme terminal") || in.equals("3"))
			{
				System.out.println("Please Enter Password:");
				pass = readString();
				while (!pass.equals("return")) {
					if (pass.equals("WeGetAnA!!"))
					{
						AcmeMembershipUpdater update = new AcmeMembershipUpdater();
						update.runAcmeTerminal(mainDirectory);
						in = "";
					}
					else {
						System.out.println("Invalid Password. Reenter Password or Enter 'return' to Select a Different Terminal.");
						pass = readString();
					}
				}
			}
		}
	System.out.println("Thank You. Have a Nice Day!");
	
 }
  
}

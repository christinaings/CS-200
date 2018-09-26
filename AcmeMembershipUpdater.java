package ChocAn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is designed to allow Acme a running terminal, from which they can update the status
 * of each directory entry's membership.
 */

public class AcmeMembershipUpdater {
	
	private int memberID;
	public String memberStatus;
	
	private ChocAnMember member;
	
	/**
	 * Default constructor
	 */
	
	public AcmeMembershipUpdater() {}
	
	/**
	 * Enables the interactive terminal that simulates ACME Accounting's terminal
	 * 
	 */
	
	public void runAcmeTerminal(ChocAnDirectory mainDirectory)
	{
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the Acme Terminal\n\n"
				+ "Enter 'Update Status' to Update a Member's Status\n"
				+ "Enter 'Quit' to exit Interactive Mode\n");
		String inputOption = "";
		
		while (inputOption != ("Quit") ) {
			try {
				inputOption = bufferRead.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (inputOption.equals("Update Status"))
				{
					String ID = "";
					System.out.println("Please Enter the ID of Member to Update");
					try {
						ID = bufferRead.readLine();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					
					System.out.println("Please Enter the New Status of this Member");
					
					try {
						memberStatus = bufferRead.readLine();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					membershipUpdatesList(mainDirectory, Integer.parseInt(ID));
				}
		}
		return;
	}
	
	/**
	 *This function updates the membership status of the given member ID parsed parameter
	 *It utilizes the memberStatus string set in the above function to reflect the most recent
	 *membership status provided by Acme
	 */
	
	public void membershipUpdatesList(ChocAnDirectory mainDirectory, int num)
	{
		memberID = num;
		member = mainDirectory.findMember(memberID);
		member.updateMemberStatus(memberStatus);	
	}
}

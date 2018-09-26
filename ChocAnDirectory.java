package ChocAn;

import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author Cole Gannaway
 *
 */
public class ChocAnDirectory {

	HashMap<Integer, ChocAnMember> mDir;
	HashMap<Integer, ChocAnProvider> pDir;
	/**
	 * 
	 * Constructor does nothing because ChocAnDirectory is a mediator between the 2 HashMaps
	 */
	public ChocAnDirectory()
	{		
	}
	
	/**
	 * The memberAccount is added to the membershipDirectory HashMap
	 * @param memberAccount
	 */
	public void registerMember(ChocAnMember memberAccount) 
	{
		mDir.put(memberAccount.memberID, memberAccount);
		System.out.println("Successfully added a member");
		return;
	}
	
	/**
	 * The providerAccount is added to the providerDirectory HashMap
	 * @param providerAccount
	 */
	public void registerProvider(ChocAnProvider providerAccount) 
	{
		pDir.put(providerAccount.providerID, providerAccount);
		System.out.print("Successfully added provider\n");
		return;	
	}
	
	/**
	 * A memberID is used to find a Member account in the HashMap
	 * If the memberID exists, then the corresponding Member account is returned
	 * If the memberID does not exist, then null is returned
	 * @param memberID
	 * @return type ChocAnMember
	 */
	public ChocAnMember findMember(int memberID) 
	{
		if ( (mDir.containsKey(memberID)) == false ) 
		{
			return null;
		}
		return mDir.get(memberID);
	}
	
	/**
	 * A providerID is used to find a Provider account in the HashMap
	 * If the providerID exists, then the corresponding Provider account is returned
	 * If the providerID does not exist, then null is returned
	 * @param providerID
	 * @return
	 */
	public ChocAnProvider findProvider(int providerID)
	{
		if ( (pDir.containsKey(providerID)) == false ) 
		{
			return null;
		}
		return pDir.get(providerID);
	}
	
	/**
	 * A memberID is used to find a Member account in the HashMap
	 * If it exists, that Member is removed from the membershipDirectory
	 * If it does not exist, not an error message is printed
	 * @param memberID
	 */
	public void deleteMember(int memberID) 
	{
		if ( (mDir.containsKey(memberID)) == false ) 
		{
			System.out.println("Error: Member Not Found\n");
			return;
		}
		mDir.remove(memberID);
		System.out.println("Successfully deleted Member\n");
		return;
	}
	
	/**
	 * 
	 * A providerID is used to find a Provider account in the HashMap
	 * If it exists, that Provider is removed from the providerDirectory
	 * If it does not exist, not an error message is printed
	 * @param providerID
	 */
	public void deleteProvider(int providerID) 
	{
		if ( (pDir.containsKey(providerID)) == false ) 
		{
			System.out.println("Error: Member Not Found\n");
			return;
		}
		pDir.remove(providerID);
		System.out.println("Successfully deleted Provider\n");
		return;
	}
	
	/**
	 * A memberID is used to find a Member account in the HashMap
	 * If it exists, return true
	 * If it does not exist, return false
	 * @param memberID
	 * @return boolean
	 */
	public boolean doesMemberExist(int memberID) 
	{
		return mDir.containsKey(memberID);
	}
	
	/**
	 * A providerID is used to find a Provider account in the HashMap
	 * If it exists, return true
	 * If it does not exist, return false
	 * @param providerID
	 * @return
	 */
	public boolean doesProviderExist(int providerID) 
	{
		return pDir.containsKey(providerID);
	}
	
	/**
	 * A Member account is passed through to the ChocAnDirectory to replace the current Member information with that ID
	 * @param member
	 */
	public void writeOverMember(ChocAnMember member) 
	{
		mDir.replace(member.memberID, member);
		return;
	}
	
	/**
	 * A Provider account is passed through to the ChocAnDirectory to replace the current Provider information with that ID
	 * @param provider
	 */
	public void writeOverProvider(ChocAnProvider provider) 
	{
		pDir.replace(provider.providerID, provider);
	}
	
	/**
	 * A memberID is used to find a Member account in the HashMap
	 * If the memberID exists, then the corresponding Member account information is displayed
	 * If the memberID does not exist, then an error message is printed
	 * @param memberID
	 */
	public void viewMemInfo(int memberID) 
	{
		if ( (mDir.containsKey(memberID)) == false ) 
		{
			System.out.println("Error: Member Not Found\n");
			return;
		}
		findMember(memberID).viewInfo();
		return;
	}
	
	/**
	 * A providerID is used to find a Provider account in the HashMap
	 * If the providerID exists, then the corresponding Provider account information is displayed
	 * If the providerID does not exist, then an error message is printed
	 * @param providerID
	 */
	public void viewProvInfo(int providerID) 
	{
		if ( (pDir.containsKey(providerID)) == false ) 
		{
			System.out.println("Error: Member Not Found\n");
			return;
		}
		findProvider(providerID).viewInfo();
		return;
	}
	
	/**
	 * Tells the total number of Members in the membershipDirectory HashMap
	 * @return Integer[]
	 */
	public Integer[] getMemberList()
	{
		Set<Integer> keys = mDir.keySet();
		Integer[] keyArray = keys.toArray(new Integer[keys.size()]);
		return keyArray;	}
	
	/**
	 * Tells the total number of Providers in the providerDirectory HashMap
	 * @return Integer[] 
	 */
	public Integer[] getProviderList()
	{
		Set<Integer> keys = pDir.keySet();
		Integer[] keyArray = keys.toArray(new Integer[keys.size()]);
		return keyArray;
	}
	
}

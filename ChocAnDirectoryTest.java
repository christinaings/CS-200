package ChocAn;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Christina Alexander
 *
 */


public class ChocAnDirectoryTest {

	ChocAnDirectory dir = new ChocAnDirectory();
	@Test //test success
	public void testAddMember() {
		ChocAnMember trump = new ChocAnMember("Donald Trump","White House","DC","Washington",41909,"donaldtrump@whitehouse.gov",000000001);
		dir.registerMember(trump);
		System.out.print("The member was inserted " + dir.doesMemberExist(000000001) + "\n");
	}
	
	@Test //for sanity
	public void testAddDeleteMember() {
		ChocAnMember QueenElizabeth = new ChocAnMember("Queen Elizabeth","Windsor Palace","London","London",99999,"queenE@windsor.co.uk",333333333);
		dir.registerMember(QueenElizabeth);
		dir.deleteMember(333333333);
		System.out.println("Now looking for deleted member " + dir.doesMemberExist(000000001));
	}
	
	@Test //for failure
	public void memberTestForFailure() {
		//Queen Elizabeth should have had this ID then been deleted
		dir.viewMemInfo(333333333);
	}
	public void testAddProvider() {
		ChocAnProvider strange = new ChocAnProvider("Dr. Stephen Vincent Strange, MD.", "100 Broadway St", "New York", "New York", 22222, "stange@hotmail.com",676767676);
		dir.registerProvider(strange);
		System.out.print("The provider was inserted " + dir.doesProviderExist(676767676) + "\n");
	}
	
	@Test //for sanity
	public void testAddDeleteProvider() {
		ChocAnProvider QueenElizabeth = new ChocAnProvider("Dr. House","Princeton-Plainsboro Teaching Hospital","Hoboken","New Jersey",31431,"houseMD@princetonplainsboro.org",333333333);
		dir.registerProvider(QueenElizabeth);
		dir.deleteProvider(333333333);
		System.out.println("Now looking for deleted provider " + dir.doesMemberExist(000000001));
	}
	
	@Test //for failure
	public void providerTestForFailure() {
		//Dr. House should have had this ID then been deleted
		dir.viewProvInfo(333333333);
	}
}

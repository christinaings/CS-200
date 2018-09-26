package ChocAn;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ChocAnProviderTest {
	ChocAnDirectory mainDirectory;
	ChocAnProvider provider;
	
	@Before
	public void setUp() throws Exception {
		mainDirectory = new ChocAnDirectory();
	  	mainDirectory.mDir = new HashMap<Integer, ChocAnMember>();					  	
		mainDirectory.pDir = new HashMap<Integer, ChocAnProvider>();
		mainDirectory.registerProvider(new ChocAnProvider("Nick Saban", "990 University Blvd", "Alabama", "Tuscaloosa", 35401, "rtr@alabama.com", 987654321));
		provider = mainDirectory.findProvider(987654321);
	}
	
	/**
	 * getEmail works
	 */
	@Test
	public void testForSuccess() {
		assertEquals(provider.getEmail(), "rtr@alabama.com");
	}
	 
	/**
	 * getProviderAddress records should work the same as findProvider in the mainDirectory
	 */
	@Test
	public void test2ForSuccess() {
		assertEquals( provider.getProviderAddress(), mainDirectory.findProvider(987654321).providerAddress);
	}
	
	/**
	 * getWeeklyService records should not return empty
	 */
	@Test
	public void test3ForFailure() {
		assertNotEquals(provider.getWeeklyServiceRecord(), "");
	}
	

}

package ChocAn;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * 
 * @author Jacoby Benger
 * 
 */

import org.junit.Before;
import org.junit.Test;

public class ChocAnMemberTest {
	ChocAnMember member;

	@Before
	public void setUp() throws Exception {
		member = new ChocAnMember("Jacoby Benger", "990 University Blvd", "Fort Collins", "Colorado", 80526, "jdb@gmail.com", 987654321);
	}

	@Test 
	public void testForMemberCreation() {
		assertNotNull(member);
	}
	
	@Test (expected = NullPointerException.class)
	public void testForFailure() {
		member.recordFilePath = null;
		member.addService("hope this fails");
	}
	
	@Test
	public void testForMemberShipStatus() {
		assertEquals(member.getMemberStatus(), "Valid");
	}

}

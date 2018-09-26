package ChocAn;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class ChocAnTesting {
	
	ChocAnDirectory chocDirectory;
	ChocAnMember exampleMember;
	
	@Before
	
	public void setup() throws Exception
	{
		chocDirectory = new ChocAnDirectory();
		chocDirectory.mDir = new HashMap<Integer, ChocAnMember>();
		chocDirectory.registerMember(new ChocAnMember("ExampleMember","1515 Example Street","ExState","Example City",101010,"exampleemail@email.com",585858));
		exampleMember = chocDirectory.findMember(585858);
	}
	
	
	@Test
	public void memberNameTest()
	{
		assertEquals(exampleMember.memberName,"ExampleMember");
		fail("Member name addition not functioning correctly");
	}
	
	@Test
	public void memberIDFailTest()
	{
		assertNotEquals(exampleMember.memberID,"");
		fail("Member ID addition not functioning correctly");
	}
	
	@Test
	public void memberZipCodeTest()
	{
		assertEquals(exampleMember.memberZipCode,101010);
		fail("Member ZIP Code not added correctly");
	}
}

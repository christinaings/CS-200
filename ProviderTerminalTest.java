package ChocAn;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ProviderTerminalTest {
	
	ChocAnDirectory mainDirectory;
	ProviderTerminal providerTerminal;
	
	@Before
	public void setup()
	{
		mainDirectory = new ChocAnDirectory();
		mainDirectory.mDir = new HashMap<Integer, ChocAnMember>();
		mainDirectory.pDir = new HashMap<Integer, ChocAnProvider>();
		mainDirectory.registerProvider(new ChocAnProvider("Nick Saban", "990 University Blvd", "Alabama", "Tuscaloosa", 35401, "rt@alabama.com", 111111111));
		mainDirectory.registerMember(new ChocAnMember("Morty","100 Nowhere", "Missery", "Hell", 90210, "morty@rick.com", 123456789));
	}

	@Test
	public void startTerminalTest()
	{
		providerTerminal = new ProviderTerminal(111111111);
		assertTrue(providerTerminal != null);
	}
	
	@Test
	public void verifyMember()
	{
		providerTerminal = new ProviderTerminal(111111111);
		assertTrue(providerTerminal.verifyMember(mainDirectory, 123456789));
	}
	
	@Test
	public void addService() throws InterruptedException
	{
		providerTerminal = new ProviderTerminal(111111111);
		providerTerminal.runProviderTerminal(mainDirectory);
		
		
		
	}
}

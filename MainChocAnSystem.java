package ChocAn;

import java.io.File;


/**
 * @author Jacob Brookins
 * 
*/

public class MainChocAnSystem {
    
    /**
     * Default constructor
     */
    public MainChocAnSystem()
    {
    }


    public void runMainAccounting(ChocAnDirectory mainDirectory)
    {
    		Integer[] members = mainDirectory.getMemberList();
    		Integer[] providers = mainDirectory.getProviderList();
    		reportGenerator reporter = new reportGenerator();
    		
    		for(int i = 0; i < members.length; i++)
    		{
    			reporter.generate(mainDirectory,members[i], "MemberReport");
    		}
    		
    		for(int i = 0; i < providers.length; i++)
    		{
    			reporter.generate(mainDirectory,providers[i], "ProviderReport");
    			reporter.generate(mainDirectory,providers[i], "ProviderSummaryReport");
    		}
    		
    		reporter.generate(mainDirectory,0, "ETFReport");
    }
    
    /**
     * @return file of all services and their cost
     */
    public File generateServiceDirectory()
    {
        ProviderServiceDirectory dir = new ProviderServiceDirectory();
        return dir.compileDirectory();
    }
    
}

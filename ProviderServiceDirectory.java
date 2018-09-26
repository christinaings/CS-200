package ChocAn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import ChocAn.ChocAnService;

/** 
 * A Directory of Provider Services.
 * @author Jacob Brookins
 */
public class ProviderServiceDirectory {
    
  TreeMap<Integer, ChocAnService> services;
    
    
    /**
     * Default constructor for this class. Creates a few dummy services
     */
  public ProviderServiceDirectory()
  {
      services = new TreeMap<Integer, ChocAnService>();
        
        ChocAnService tempService = new ChocAnService(111111111, 120, "Example service");
        services.put(new Integer(tempService.getId()), tempService);
        
        tempService = new ChocAnService(234525168, 130, "Example service 2");
        services.put(new Integer(tempService.getId()), tempService);
        
        tempService = new ChocAnService(123456789, 320, "Example service 3");
        services.put(new Integer(tempService.getId()), tempService);
        
        tempService = new ChocAnService(999999999, 152, "Example service 4");
        services.put(new Integer(tempService.getId()), tempService);
        
        tempService = new ChocAnService(111911111, 178, "Example service 5");
        services.put(new Integer(tempService.getId()), tempService);
        
    }
    
    public ChocAnService searchForService(int serviceID) {
		return services.get(serviceID);
	}
    
    public boolean verifyService(int serviceID)
    {
    	return services.containsKey(serviceID);
    }
    
    /**
     * This function compiles the directory into a file and returns it
     * @return a file containing the provider directory as a comma separated value sheet
     */
    public File compileDirectory()
    {
    	String Filename = "Directory.txt";
    	StringBuilder tmpString = new StringBuilder();
    	FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(Filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	PrintWriter printWriter = new PrintWriter(fileWriter);
    	String headers = "Code , Description , Fee";
    	printWriter.println(headers);
    	for(Map.Entry<Integer, ChocAnService> entry : services.entrySet())
    	{
    		ChocAnService tmpService = entry.getValue();
    		tmpString.append(tmpService.getIdString()).append(" , ").append(tmpService.getDescriptionString()).append(" , ").append(tmpService.getCostString());
    		printWriter.println(tmpString.toString());
    		tmpString.setLength(0);
    	}
    	
    	printWriter.close();
    	File file = new File(Filename);
    	return file;
    }
}

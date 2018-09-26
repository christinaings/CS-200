package ChocAn;

/**
 * 
 * @author Jacoby Benger
 *
 */

public class reportGenerator {
	private String reportType;
	private int IDnum;
	
	/**
	 * Default Constructor
	 */
	public reportGenerator() 
	{
		reportType = "";
		IDnum = 0;
	}
	
	/**
	 * Report Creating Function
	 * @param rType is a string corresponding to the report to be created
	 * @param num is Member/Provider ID number for the reports to be generated 
	 */
	public void generate(ChocAnDirectory mainDirectory, int num, String rType) 
	{
		IDnum = num;
		reportType = rType;
		
		if(reportType.equals("MemberReport")) 
		{
			//calling report methods to write text file 
			MemberReport mr = new MemberReport(mainDirectory, IDnum);
			mr.createReport();
			System.out.println("Member Report Generated");
		}
		else if(reportType.equals("ProviderReport")) 
		{
			ProviderReport pr = new ProviderReport(IDnum);
			pr.createReport();
			System.out.println("Provider Report Generated");
		}
		else if(reportType.equals("ProviderSummaryReport")) 
		{
			ProviderSummaryReport psr = new ProviderSummaryReport(mainDirectory, IDnum);
			psr.createReport();
			System.out.println("Weekly Provider Summary Report Generated");
		}
		else if(reportType.equals("ETFReport"))
		{
			ETFReport etf = new ETFReport(mainDirectory, IDnum);
			etf.createReport();
			System.out.println("ETF Report Generated");
		}
		else
		{
			System.out.println("INVALID REPORT TYPE");
		}
	}
}

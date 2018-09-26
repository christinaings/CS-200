package ChocAn;

/**
 * @author Jacob Brookins
 */
 
public class ChocAnService 
{
    private Integer id;
    private Float cost;
    private String description;
    
    
    /**
     * @param newId The ID for the service
     * @param newCost The fee paid to the provider of the service
     * @param newDescription The description of the service
     */
    public ChocAnService(int newId, float newCost, String newDescription)
    {
        id = new Integer(newId);
        cost = new Float(newCost);
        description = newDescription;
    }
    
    /**
     * @param newCost the new cost for the service
     */
    public void updateCost(float newCost)
    {
        cost = new Float(newCost);
    }
    
    /**
     * @param newDescription the new description for the service
     */
    public void updateDescription(String newDescription)
    {
    	description = newDescription;
    }
    
    /**
     * @return the description of the service
     */
    public String getDescriptionString()
    {
    	return description;
    }
    
    /**
     * @return the cost of the service
     */
    public String getCostString()
    {
    	return cost.toString();
    }
    
    
    /**
     * @return the id of the service
     */
    public String getIdString()
    {
    	return id.toString();
    }
    
    public int getId()
    {
    	return id.intValue();
    }
}
/*
 * Author: Cheng Zixin
 * Start date: 20 May 2016
 * End date: 22 May 2016
 * Description: Accessing and modifying the data in a product object,
 *              and return product data back to interface class.
 */
public class Store 
{
	private Product[] callaghan ;
	private Product[] lambton;	
	// Constructor
	public Store()
	{
		callaghan = new Product[1];
		lambton = new Product[1];	
	}
	
	
	
    private int replenishQuantity = 0;
    private int inventory = 0;
	int totalReplenishQuantity = 0;
	int replenishCount = 0;
	int totalInventory = 0;
    
	private Product[] resizeArray(Product[] oldArray)
	{
		int newLength = oldArray.length + 1;		
		Product[] newArray = new Product[newLength];
		for (int count = 0; count < oldArray.length; count ++)
			newArray[count] = oldArray[count];
		return newArray;		
	}
	
 	/*
	 * Validate data (number)
	 * Precondition: user already type in a number
	 * Postcondition: return the result - whether the name is valid
	 */
 	public boolean isValidNumber (double number)
 	{
 		boolean isValid = false;
 		if (number >= 0)
 			isValid = true;
 		return isValid;
 	}
 	
 	/*
 	 * Validate data (name)
  	 * validStatus: 0 - valid name, 1 - invalid length, 2 - name clash
  	 * Precondition: user already type in a name
  	 * Postcondition: return the result - valid status
 	 */
  	public int isValidName (String store, String name)
  	{
  		int validStatus = 0;
  		if (name.length() < 3 || name.length() >= 10)
             validStatus = 1;  		
  		if (store.equals("lambton"))
  		{
  			
  			for (int count = 0; count < lambton.length; count ++)
  			{
  				if (lambton[0]!= null && name.equals(lambton[count].getName()))
  				{
  					validStatus = 2;
  				    break;
  				}
  			}
  		}
  		else if (store.equals("callaghan"))
  		{
  			for (int count = 0; count < callaghan.length; count ++)
  			{
  				if (callaghan[0]!= null && name.equals(callaghan[count].getName()))
  				{
  					validStatus = 2;
  				    break;
  				}
  			}
  		} 		
  		return validStatus;
  	}
  	
 
 	
  	/*
  	 *  Remove a specific product record
  	 *  Precondition: have a valid record number
  	 *  Postcondition: a specific product record becomes to null
  	 */
 	public void removeRecord(String store,int record)
 	{
 		Product nullProduct = new Product();
 		// Change product value to null
 		if (store.equals("lambton"))
 			lambton[record] = nullProduct;
 		else
 			callaghan[record] = nullProduct;
 	}
 	
 	/*
 	 * Check which product need to show and return back to interface class
 	 * 0 means not found
 	 * Precondition: have a valid name
 	 * Postcondition: return back product number based on product name
 	 */
 	public int selectProduct (String store, String name)
 	{
 		int productNumber = -1;
 		if (store.equals("lambton"))
 		{
 			for (int count = 0; count < lambton.length; count ++)
 			{	if (lambton[0] != null)
 			    {
 				if (name.equals(lambton[count].getName()) )
 				{
 				    productNumber = count;
 				    break;
 				}
 			}    
 			}		
 		}
 		else
 		{
            for (int count = 0; count < callaghan.length; count ++)
 			{	
            	if (callaghan[0] != null)
 			    {
 				if (name.equals(callaghan[count].getName()) )
 				{
 					productNumber = count;
 				    break;
 		   	    }
 			    }
 			}		
 		}
 		return productNumber;
 	}
 	
 	/*
 	 * Check the size of an array and return back the array size
 	 * Precondition: Program envoke getArraySize() with a valid store name
 	 * Postcondition: Get the array size and return back
 	 */
 	public int getArraySize (String store)
 	{
 		int size = 0;
 		if (store.equals("lambton"))
 			size = lambton.length;
 		else
 			size = callaghan.length;
 		return size;
 	}
 	
 	/*
 	 * Check whether an array is null
 	 * Precondition: Program envoke isNull() with a valid store name
 	 * Postcondition: Return back the result (true or false)
 	 */
 	public boolean isNull (String store)
 	{
 		boolean isNull = false;

 		if (store.equals("lambton"))
 		{
 			if (lambton[0] == null)
 				isNull = true;
 		}
 		else
 		{
 			if (callaghan[0] == null)
 				isNull = true;
 		}
 		return isNull;
 	}
	
 	/*
 	 * Get name of a specific product
 	 * Precondition: have a record number
 	 * Postcondition: return back product name based on product number
 	 */
	public String getName(String store,int recordNumber)
	{
		String name = null; // Initialize name
		if (store.equals("lambton"))
			name = lambton[recordNumber].getName();
		else
			name = callaghan[recordNumber].getName();		
		return name;
	}
	
	/*
	 * Get demand rate of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back demand rate based on product number
	 */
    public int getDemandRate (String store, int recordNumber)
	{
	    int demandRate = 0;
	    if (store.equals("lambton"))
	    	demandRate = lambton[recordNumber].getDemandRate();
	    else
	    	demandRate = callaghan[recordNumber].getDemandRate();
		return demandRate;
	}
	
    /*
	 * Get setup cost of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back setup cost based on product number
	 */
	public double getSetupCost (String store, int recordNumber)
	{
		double setupCost = 0;
	    if (store.equals("lambton"))
	    	setupCost = lambton[recordNumber].getSetupCost();
	    else
	    	setupCost = callaghan[recordNumber].getSetupCost();
		return setupCost;
	}
	
	/*
	 * Get unit cost of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back unit cost based on product number
	 */
	public double getUnitCost (String store, int recordNumber)
	{
		double unitCost  = 0;
		if (store.equals("lambton"))
			unitCost = lambton[recordNumber].getUnitCost();
		else
			unitCost = callaghan[recordNumber].getUnitCost();
		return unitCost;
	}
	
	/*
	 * Get inventory cost of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back inventory cost based on product number
	 */
	public double getInventoryCost (String store, int recordNumber)
	{
		double inventoryCost = 0;
	    if (store.equals("lambton"))
	    	inventoryCost = lambton[recordNumber].getInventoryCost();
	    else
	    	inventoryCost = callaghan[recordNumber].getInventoryCost();
		return inventoryCost;
	}
	
	/*
	 * Get selling price of a specific product
     * Precondition: have a valid record number
     * Postcondition: return back selling price based on product number
	 */
	public double getSellingPrice (String store, int recordNumber)
	{
		double sellingPrice = 0;
	    if (store.equals("lambton"))
	    	sellingPrice = lambton[recordNumber].getSellingPrice();
	    else
	    	sellingPrice = callaghan[recordNumber].getSellingPrice();
		return sellingPrice;
	}
	
	
	/*
	 * Create new product, return which record store the data
	 * Return 0 means no more space to store the product data, the program will resize the array.
	 * Precondition: Have all valid data of a product
	 * Postcondition: a new product created and return back the record number,
	 *                or failed to create a product and return back 0
	 */
	public void createProduct (String store, String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice)
	{
		if (store.equals("lambton"))
		{
			// Check whether the array has space.
			int length = lambton.length;
			if (lambton[length-1] != null)
			{
				// If has no space, revoke function resizeArray() to resize
				lambton = resizeArray(lambton);
				lambton[length] = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);	
			}
			else
			{
				int nullIndex = 0;
				for (int count = 0; count < lambton.length; count++)
				{
					if (lambton[count] == null)
					{
						nullIndex = count;
					}
				} 
				lambton[nullIndex] = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);	
			}
		}
		else
		{
			// Check whether the array has space.
			int length = callaghan.length;
			if (callaghan[length-1] != null)
			{
				// If has no space, revoke function resizeArray() to resize
				callaghan = resizeArray(callaghan);
				callaghan[length] = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);
			}			
			else
			{
				int nullIndex = 0;
				for (int count = 0; count < callaghan.length; count++)
				{
					if (callaghan[count] == null)
					{
						nullIndex = count;
					}
				} 
				callaghan[nullIndex] = new Product(name,demandRate,setupCost,unitCost,inventoryCost,sellingPrice);	
			}
		}
	}
	

	/*
	 * Modify an existing record
	 * Precondition: Have all valid data of a product
	 * Postcondition: the data of an existing product record had been changed based on the value of parameters
	 */
	public void modifyRecord (String store, String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice)
	{
		int productNumber = selectProduct(store, name);
		if (store.equals("lambton"))
		{
			lambton[productNumber].setDemandRate(demandRate);
			lambton[productNumber].setSetupCost(setupCost);
			lambton[productNumber].setUnitCost(unitCost);
			lambton[productNumber].setInventoryCost(inventoryCost);
			lambton[productNumber].setSellingPrice(sellingPrice);
		}
		else if (store.equals("callaghan"))
		{
			callaghan[productNumber].setDemandRate(demandRate);
			callaghan[productNumber].setSetupCost(setupCost);
			callaghan[productNumber].setUnitCost(unitCost);
			callaghan[productNumber].setInventoryCost(inventoryCost);
			callaghan[productNumber].setSellingPrice(sellingPrice);
		}
	}

	/*
	 * Calculate and return round EOQ value
	 * Precondition: Have valid demand rate, setup cost and inventory cost
	 * Post condition: return back the EOQ value (round to a integer)
	 */
	public int calculateEOQ (double demandRate, double setupCost, double inventoryCost)
	{
		double EOQ = Math.sqrt(2 * setupCost * demandRate / inventoryCost);
		return (int) Math.round(EOQ);
	}
		
	/*
	 * Calculate replenish strategy for a specific week 
	 * Return the total replenishment quantity, then use this data to calculate profit.
	 * Precondition: function calculateEOQ() had already beed executed and get a valid EOQ number, 
	 *               and user already provided a valid week number
	 * Postconditions: Get the result of inventory, replenish count, replenish quantity, total inventory and total replenish quantity,
	 *                 and stored to the relevant variables
	 */
	public void calculateReplenishmentStrategy(int week, int demandRate, int EOQ)
	{
		inventory = EOQ; // Initial inventory value
        replenishCount = 0;
		for (int count = 1; count <= week; count ++)
		{
			if (inventory < demandRate)
			{
				replenishQuantity = EOQ / demandRate * demandRate - inventory;
				inventory = inventory + replenishQuantity - demandRate;	
				replenishCount ++;
			}
			else
			{
				inventory = inventory - demandRate;
				if (count == 1)
				{
					replenishQuantity = EOQ;
					replenishCount ++;
				}
				else
				    replenishQuantity = 0;
			}
			
		}
		totalReplenishQuantity += replenishQuantity;
		totalInventory += inventory;
	}
	
	/*
	 * Get replenish quantity of a specific product
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and get a valid value of replenish quantity
     * Postcondition: return back replenish quantity of a specific product
	 */
	public int getReplenishQuantity()
	{
		return replenishQuantity;
	}
	
	/*
	 * Get inventory of a specific product in a specific week
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and get a valid value of inventory
     * Postcondition: return back inventory of a specific product and a specific week
	 */
	public int getInventory()
	{
		return inventory;
	}
	
	/*
	 * Calculate profit value a specific product
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and user already provided a valid week number, 
     *               and the program provide a valid record number
     * Postcondition: return back the profit value of a specifc product based on the replenishment strategy
	 */
	public double getProfit(String store, int recordNumber, int week)
	{
		double profit = 0;
		double cost = 0;
		if (store.equals("lambton"))
		{
			cost = replenishCount * lambton[recordNumber].getSetupCost() + 
					totalReplenishQuantity * lambton[recordNumber].getUnitCost() +
					totalInventory * lambton[recordNumber].getInventoryCost();
			profit = (lambton[recordNumber].getDemandRate() * week * lambton[recordNumber].getSellingPrice()) - cost;
		}
		else
		{
			cost = replenishCount * callaghan[recordNumber].getSetupCost() + 
					totalReplenishQuantity * callaghan[recordNumber].getUnitCost() +
					totalInventory * callaghan[recordNumber].getInventoryCost();
			profit = (callaghan[recordNumber].getDemandRate() * week * callaghan[recordNumber].getSellingPrice()) - cost;
		}
		return profit;
	}
	
	/*
	 * Calculate cost value a specific product
     * Precondition: functions calculateEOQ() and calculateReplenishmentStrategy() had already been excuted,
     *               and user already provided a valid week number, 
     *               and the program provide a valid record number
     * Postcondition: return back the cost value of a specifc product based on the replenishment strategy
	 */
	public double getCost (String store, int recordNumber, int week)
	{		
		double cost = 0;
		if (store.equals("lambton"))
		{
			cost = replenishCount * lambton[recordNumber].getSetupCost() + 
					totalReplenishQuantity * lambton[recordNumber].getUnitCost() +
					totalInventory * lambton[recordNumber].getInventoryCost();			
		}
		else
		{
			cost = replenishCount * callaghan[recordNumber].getSetupCost() + 
					totalReplenishQuantity * callaghan[recordNumber].getUnitCost() +
					totalInventory * callaghan[recordNumber].getInventoryCost();			
		}
		return cost;
	}
}

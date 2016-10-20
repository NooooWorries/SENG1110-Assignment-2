/*
 * Author: Cheng Zixin
 * Start date: 20 May 2016
 * End date: 20 May 2016
 * Description: Hold the required instance data for a product,
 *              and access and modify the data for a product.
 */
public class Product 
{
    // Claim attributes
	private String name;
	private int demandRate;
	private double setupCost;
	private double unitCost;
	private double inventoryCost;
	private double sellingPrice;
	
	// Constructors
	public Product (String name, int demandRate, double setupCost, double unitCost, double inventoryCost, double sellingPrice)
	{
		this.name = name;
		this.demandRate = demandRate;
		this.setupCost = setupCost;
		this.unitCost = unitCost;
		this.inventoryCost = inventoryCost;
		this.sellingPrice = sellingPrice;
	}
	
	public Product() // accept null value
	{
		this.name = null;
		this.demandRate = 0;
		this.setupCost = 0;
		this.unitCost = 0;
		this.inventoryCost = 0;
		this.sellingPrice = 0;
	}
	
	// Methods
	// Accessors
	
	/*
	 * Precondition: none
	 * Postcondition: return back name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/*
	 * Precondition: none
	 * Postcondition: return back demand rate
	 */
	public int getDemandRate()
	{
		return this.demandRate;
	}
	
	/*
	 * Precondition: none
	 * Postcondition: return back setup cost
	 */
	public double getSetupCost()
	{
		return this.setupCost;
	}
	
	/*
	 * Precondition: none
	 * Postcondition: return back unit cost
	 */
	public double getUnitCost()
	{
		return this.unitCost;
	}
	
	/*
	 * Precondition: none
	 * Postcondition: return back inventory cost
	 */
	public double getInventoryCost()
	{
		return this.inventoryCost;
	}
	
	/*
	 * Precondition: none
	 * Postcondition: return selling price
	 */
	public double getSellingPrice()
	{
		return this.sellingPrice;
	}
	
	// Mutators
	
	/*
	 * Precondition: have a valid name
	 * Postcondition: name get a value
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 * Precondition: have a valid demand rate
	 * Postcondition: demand rate get a value
	 */
	public void setDemandRate(int demandRate)
	{
		this.demandRate = demandRate;
	}
	
	/*
	 * Precondition: have a valid setup cost
	 * Postcondition: setup cost get a value
	 */
	public void setSetupCost(double setupCost)
	{
		this.setupCost = setupCost;
	}
	
	/*
	 * Precondition: have a valid unit cost
	 * Postcondition: unit cost get a value
	 */
	public void setUnitCost (double unitCost)
	{
		this.unitCost = unitCost;
	}
	
	/*
	 * Precondition: have a valid inventory cost
	 * Postcondition: inventory cost get a value
	 */
	public void setInventoryCost(double inventoryCost)
	{
		this.inventoryCost=inventoryCost;
	}
	
	/*
	 * Precondition: have a valid selling price
	 * Postcondition: selling price get a value
	 */
	public void setSellingPrice (double sellingPrice)
	{
		this.sellingPrice = sellingPrice;
	}
	
}

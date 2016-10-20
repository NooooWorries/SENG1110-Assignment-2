/*
 * Author: Cheng Zixin
 * Start date: 20 May 2016
 * End date: 22 May 2016
 * Description: The interface class to evaluate public methods of store class, 
 *              and receive all input from the user, display all output,
 *              and check for invalid inputs & display all error messages
 */
import java.util.Scanner;
import java.io.*;
public class StarberksInterface 
{
	// Claim new variables
    static Store store = new Store(); // Create a new store instance variable
    static Scanner scanner = new Scanner(System.in); // Create a new scanner
    
    // Variables for product
    static String storeName; // Lambton, Callaghan
    static String name; 
    static int demandRate;
	static double setupCost;
	static double unitCost;
	static double inventoryCost;
	static double sellingPrice;	
	
	// Variables for calculation
	static double[] profitLambton = new double[1]; // An array to store profit for lambton
	static double[] profitCallaghan = new double[1]; // An array to store profit for callaghan
	static double[] costLambton = new double[1];// An array to store cost for lambton
	static double[] costCallaghan = new double[1];// An array to store cost for callaghan
	
	static int[] weekLambton = new int[1]; // Week number for lambton
	static int[] weekCallaghan = new int[1]; // Week number for callaghan
	
	static int menuSelection;
	
	public void run()
	{
		menuSelection = 0; // Menu selection for user
		displayMenu(); // Display the menu
	    menuSelection = inputMenuSelection(); // Ask user to select a function from the menu	    
	    // Main control flow for this program
	    switch (menuSelection)
	    {
	    case 1:
	    	selectStore();
	    	storeMenuFlow();
	    	break;
	    case 2:
	    	displayStore();
	    	break;
	    case 3:
	    	openFile();
	    	break;
	    case 4:
	    	saveFile();
	    	break;
	    case 5:
	    	System.exit(0);
	    	break;
	    default:
		    System.out.println("Error: Please type in an integer from 1 to 5");
		    storeMenuFlow();   
	    }
		
	}
	
	public static void main(String[] args) 
	{
		StarberksInterface intFace = new StarberksInterface();
        intFace.run();
	}
	
	/*
	 * Run function 
	 * Precondition: none
	 * Postcondition: Flow of control finished
	 */
	public static void storeMenuFlow ()
	{
		
    	displayStoreMenu();
    	menuSelection = inputMenuSelection();
    	switch(menuSelection)
	    {
	    case 1:
		    inputProduct();
		    storeMenuFlow(); 
		    break;
	    case 2:
	    	deleteProduct();
	    	storeMenuFlow(); 
		    break;
	    case 3:
	    	showProduct();
	    	storeMenuFlow(); 
		    break;
	    case 4:
	    	displayAllProduct();
	    	storeMenuFlow(); 
		    break;
	    case 5:
	    	main(null);
	    	break;
	    default:
		    System.out.println("Error: Please type in an integer from 1 to 5");
		    storeMenuFlow();   
		} 
	}
	
	
	/*
	 * Display the second-level menu - choose store
	 * Precondition: user choose 1 in main menu
	 * Postcondition: Second level menu shows on the screen
	 */
	private static void selectStore()
	{
		System.out.println("Please enter in the store name");
		storeName = scanner.next();
		if (!storeName.equals("lambton") && !storeName.equals("callaghan"))
		{
			System.out.println("Store not exist, please try again");
            selectStore(); // Recursion: ask for store name again
		}
	}
	
	/*
	 * Display all product with a sorting model (Sort by demand rate or sort by name)
	 * Preconditions: User choose 4 in store menu, and choose a sorting model
	 * Postconditions: All products with sorting display on the screen
	 */
	private static void displayAllProduct()
	{
		int listCount = listProduct();
		if (listCount == 0)
		{
			System.out.println("no products");
			storeMenuFlow();
		}
		else
		{
			System.out.println("Please choose a sorting model");
			System.out.println("1 - Sort by name");
			System.out.println("Other numbers - Sort by demand rate");
			int sortingModel = scanner.nextInt();
			int length = store.getArraySize(storeName);
			Product[] sort = new Product[length];
			System.out.println("========Product table ========");
			System.out.println("Name\tDemand\tSetup cost\tUnit cost\tInventory Cost\t Selling Price");
			for (int count = 0; count< length; count ++)
			{
				name = store.getName(storeName, count);
				demandRate = store.getDemandRate(storeName, count);
				setupCost = store.getSetupCost(storeName, count);
				unitCost = store.getUnitCost(storeName, count);
				inventoryCost = store.getInventoryCost(storeName, count);
				sellingPrice = store.getSellingPrice(storeName, count);
				sort[count] = new Product(name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
			}
			
			
			if (sortingModel == 1)
			{
				// Sorting - sort by name	
				Product temp = new Product(); // temp - swap value	
					for (int j = 0; j < length - 1; j ++)
					{
					    if (sort[j].getName().compareToIgnoreCase(sort[j+1].getName())>0)
					    {
					    	temp = sort[j];
						    sort[j] = sort [j + 1];
						    sort [j + 1] = temp;
					    }
				    }				
			}
			else
			{
				// Sorting - Bubble sorting - sort by demand rate
				int i, j; // i - first loop, j - second loop
				Product temp = new Product(); // temp - swap value								
				for (i = 0; i < length - 1; i++) 
				{
					for (j = 0; j < length - 1 - i; j ++)
					{
						if (sort[j].getDemandRate() < sort[j+1].getDemandRate())
						{
						    temp = sort[j];
						    sort[j] = sort [j + 1];
						    sort [j + 1] = temp;
						}
					}
				}		
			}
			for (int count = 0; count < length; count ++)
			{
				if (!(sort[count].equals(null)))
				{
				    System.out.println(sort[count].getName() + "\t" +
			                           sort[count].getDemandRate() + "\t" +
						               sort[count].getSetupCost() + "\t\t" +
			                           sort[count].getUnitCost() + "\t\t" +
						               sort[count].getInventoryCost() + "\t\t" +
			                           sort[count].getSellingPrice());
				}
			}
		}
	}
	
	/*
	 * Read a solid format data file and store file data in arrays
	 * Preconditions: User choose 3 - Open file from main menu
	 *                User type in a valid data file name
	 *                Data file format is correct
	 * Postconditions: Data from external data file stored into arrays.
	 */
	private static void openFile()
	{
		// Variables
        System.out.println("Please provide file name: ");
	    String fileName = scanner.next();
	    Scanner inputStream = null;
	    String line = null;
	    String[] lambton; // Lambton string array
        String[] callaghan; // Callaghan string array
	    int lambtonCount = 0; // Number of products in lambton
	    int callaghanCount = 0; // Number of products in callaghan
	    // Ask for file name and read data from external file
        try
        {
            inputStream = new Scanner (new File (fileName +".dat"));
        }
	  	catch (FileNotFoundException e)
        {
            System.out.println ("The file does not exist");
            main(null);
        }
	   	while (inputStream.hasNextLine ())
        {
            line = line + "\n" + inputStream.nextLine ();         
        }
        inputStream.close ();
        // Get the string array for each store
        if (line.indexOf("Callaghan:") != -1 && line.indexOf("Lambton:")!= -1)
        {
            lambton = line.substring(0, line.indexOf("Callaghan:")).split("\n");
            callaghan = line.substring(line.indexOf("Callaghan:")).split("\n");
    
            // Calculate how many products in a store
            for (int count = 0; count < lambton.length; count ++)
            {
        	    if (lambton[count].indexOf("Name:") != -1 )
          	    {
           		    lambtonCount ++;
           	    }
            }
            for (int count = 0; count < callaghan.length; count ++)
            {
              	if (callaghan[count].indexOf("Name:") != -1)
              	{
               		callaghanCount ++;
               	}
            }
        
            // Store data
            for (int count = 0; count < lambtonCount; count ++)
            {
                String name = lambton[3 + count * 7].replace("Name: ", "");
                int demandRate = Integer.parseInt(lambton [4 + count * 7].replace("demand rate: ", ""));
                double setupCost = Double.parseDouble(lambton [5 + count * 7].replace("setup cost: ",""));
                double unitCost = Double.parseDouble(lambton [6 + count * 7].replace("unit cost: ", ""));
                double inventoryCost = Double.parseDouble(lambton [7 + count * 7].replace("inventory cost: ",""));
                double sellingPrice = Double.parseDouble( lambton [8 + count * 7].replace ("selling price: ",""));           
                // Overwrite if product already exist
                if (store.selectProduct("lambton", name) != -1)
                {
                	store.modifyRecord("lambton", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                }
                else
                {
                    store.createProduct("lambton", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                }         
            }
           
            for (int count = 0; count < callaghanCount; count ++)
            {
                String name = callaghan[2 + count * 7].replace("Name: ", "");
                int demandRate = Integer.parseInt(callaghan [3 + count * 7].replace("demand rate: ", ""));
                double setupCost = Double.parseDouble(callaghan [4 + count * 7].replace("setup cost: ",""));
                double unitCost = Double.parseDouble(callaghan [5 + count * 7].replace("unit cost: ", ""));
                double inventoryCost = Double.parseDouble(callaghan [6 + count * 7].replace("inventory cost: ",""));
                double sellingPrice = Double.parseDouble(callaghan [7 + count * 7].replace ("selling price: ",""));         
                // Overwrite if product already exist
                if (store.selectProduct("callaghan", name) != -1)
                {
                	store.modifyRecord("callaghan", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                }
                else
                {
                    store.createProduct("callaghan", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                }
            }   
        }
        else if (line.indexOf("Callaghan:") == -1 && line.indexOf("Lambton:")!= -1)
        {
            lambton = line.split("\n");
            for (int count = 0; count < lambton.length; count ++)
            {
              	if (lambton[count].indexOf("Name:") != -1 )
               	{
               		lambtonCount ++;            		
               	}
            }
            for (int count = 0; count < lambtonCount; count ++)
            {
                 String name = lambton[3 + count * 7].replace("Name: ", "");
                 int demandRate = Integer.parseInt(lambton [4 + count * 7].replace("demand rate: ", ""));
                 double setupCost = Double.parseDouble(lambton [5 + count * 7].replace("setup cost: ",""));
                 double unitCost = Double.parseDouble(lambton [6 + count * 7].replace("unit cost: ", ""));
                 double inventoryCost = Double.parseDouble(lambton [7 + count * 7].replace("inventory cost: ",""));
                 double sellingPrice = Double.parseDouble( lambton [8 + count * 7].replace ("selling price: ",""));           
                 // Overwrite if product already exist
                 if (store.selectProduct("lambton", name) != -1)
                 {
                 	store.modifyRecord("lambton", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                 }
                 else
                 {
                     store.createProduct("lambton", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                 }            
            }   	
        }
        else if (line.indexOf("Callaghan:") != -1 && line.indexOf("Lambton:") == -1)
        {
            callaghan = line.split("\n");
            for (int count = 0; count < callaghan.length; count ++)
            {
                if (callaghan[count].indexOf("Name:") != -1 )
                {
                	callaghanCount ++;            		
              	}
                }
           for (int count = 0; count < callaghanCount; count ++)
           {
                 String name = callaghan[3 + count * 7].replace("Name: ", "");
                 int demandRate = Integer.parseInt(callaghan [4 + count * 7].replace("demand rate: ", ""));
                 double setupCost = Double.parseDouble(callaghan[5 + count * 7].replace("setup cost: ",""));
                 double unitCost = Double.parseDouble(callaghan [6 + count * 7].replace("unit cost: ", ""));
                 double inventoryCost = Double.parseDouble(callaghan[7 + count * 7].replace("inventory cost: ",""));
                 double sellingPrice = Double.parseDouble( callaghan [8 + count * 7].replace ("selling price: ",""));           
                 // Overwrite if product already exist
                 if (store.selectProduct("callaghan", name) != -1)
                 {
                    store.modifyRecord("callaghan", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                 }
                 else
                 {
                     store.createProduct("callaghan", name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
                 }    
             }  	
          }
          main(null);      
		}
	
	/*
	 * Store data in array to an external data file
	 * Precondition: User select 4 - Save file in main menu, and
	 *               There are at least one valid record in arrays.
	 * Postconditions: data stored in the external data file.
	 */
	private static void saveFile()
	{
		if (store.isNull("lambton") && store.isNull("callaghan"))
		{
			System.out.println("No data to save.");
		}
		else
		{
		    System.out.println("Please provide a file name");
		    String fileName = scanner.next();
		    PrintWriter outputStream = null; 
		    try 
		    {
			    outputStream = new PrintWriter (fileName + ".dat");
		    } 
		        catch (FileNotFoundException e) 
		    {
			    System.out.println("File not found.");
		    } 
		    if (!(store.isNull("lambton")))
		    {
		        outputStream.println("Lambton:");
		        outputStream.println();
		        int lambtonCount = store.getArraySize("lambton");
		        for (int count = 0; count < lambtonCount; count ++)
		        {
		        	if (store.getName("lambton", count) != null)
		        	{
		    	        outputStream.println("Name: "+ store.getName("lambton", count));
		    	        outputStream.println("demand rate: "+ store.getDemandRate("lambton", count));
		    	        outputStream.println("setup cost: "+ store.getSetupCost("lambton", count));
		    	        outputStream.println("unit cost: "+ store.getUnitCost("lambton", count));
		    	        outputStream.println("inventory cost: "+ store.getInventoryCost("lambton", count));
		        	    outputStream.println("selling price: "+ store.getSellingPrice("lambton", count));	
		        	    outputStream.println();
		        	}
		        }
		    }
		    if  (!(store.isNull("callaghan")))
		    {
		    	outputStream.println("Callaghan:");
		        outputStream.println();
		        int callaghanCount = store.getArraySize("callaghan");
		        for (int count = 0; count < callaghanCount; count ++)
		        {
		    	    outputStream.println("Name: "+ store.getName("callaghan", count));
		    	    outputStream.println("demand rate: "+ store.getDemandRate("callaghan", count));
		    	    outputStream.println("setup cost: "+ store.getSetupCost("callaghan", count));
		    	    outputStream.println("unit cost: "+ store.getUnitCost("callaghan", count));
		    	    outputStream.println("inventory cost: "+ store.getInventoryCost("callaghan", count));
		        	outputStream.println("selling price: "+ store.getSellingPrice("callaghan", count));
		            outputStream.println();
		        }
		    }
		    else
			{
				System.out.println("Incorrect data format or blank file.");
			}
	    	outputStream.close ();
		}	
		main(null);
	}
	private static void displayStore()
	{
		// Display lambton
		System.out.println("Store: Lambton");
		int lambtonSize = store.getArraySize("lambton");
		if (store.isNull("lambton"))
		{
			System.out.println("Number of products: 0");
		}
		else
		{
		    System.out.println("Number of products: " + lambtonSize);
		    for (int count = 0; count < lambtonSize; count ++)
		    {
		    	System.out.println("Product " + (count + 1) + ": " + store.getName("lambton", count));
		    }
		}
		
		// Display callaghan
		System.out.println("Store: Callaghan");
		int callaghanSize = store.getArraySize("callaghan");
		if (store.isNull("callaghan"))
		{
			System.out.println("Number of products: 0");
		}
		else
		{
		    System.out.println("Number of products: " + callaghanSize);
		    for (int count = 0; count < callaghanSize; count ++)
		    {
		    	System.out.println("Product " + (count + 1) + ": " + store.getName("callaghan", count));
		    }
		}
		main(null);
	}
	
	/*
	 * Resize an array if an array is already full
	 * Precondition: Program found that no more space for a specific array. 
	 * Postcondition: Copy all data in a new array and size + 1, then return the new array back
	 */
	private static int[] resizeIntArray(int[] oldArray)
	{
		int newLength = oldArray.length + 1;		
		int[] newArray = new int[newLength];
		for (int count = 0; count < oldArray.length; count ++)
			newArray[count] = oldArray[count];
		return newArray;		
	}
	
	/*
	 * Resize an array if an array is already full
	 * Precondition: Program found that no more space for a specific array. 
	 * Postcondition: Copy all data in a new array and size + 1, then return the new array back
	 */
	private static double[] resizeDoubleArray(double[] oldArray)
	{
		int newLength = oldArray.length + 1;		
		double[] newArray = new double[newLength];
		for (int count = 0; count < oldArray.length; count ++)
			newArray[count] = oldArray[count];
		return newArray;		
	}
	
	/*
	 * Input menu selection, return selection
	 * Precondition: none
	 * Postcondition: Menu selection that selected by user had been stored
	 */
	private static int inputMenuSelection()
	{	
		int selection = scanner.nextInt();
		return selection;
	}
	
	/* 
	 * Display main menu
	 * Precondition: none
	 * Postcondition: Menu displayed on screen
	 */
	private static void displayMenu()
 	{
 		System.out.println("------------MAIN MENU-----------");
 		System.out.println("(1) Choose Store");
 		System.out.println("(2) Display stores");
 		System.out.println("(3) Open");
 		System.out.println("(4) Save");
 		System.out.println("(5) Exit");
 		System.out.println("---------------------------");
 		System.out.println("Please type in an integer from 1 to 5");
 	}
	
	private static void displayStoreMenu()
	{
		System.out.println("------------STORE MENU-----------");
 		System.out.println("(1) Add/Edit product");
 		System.out.println("(2) Delete product");
 		System.out.println("(3) Display product");
 		System.out.println("(4) Display all products");
 		System.out.println("(5) Exit Store");
 		System.out.println("---------------------------");
 		System.out.println("Please type in an integer from 1 to 5");
	}
	/*
	 * Start to input a product 
	 * Precondition: variable menuSelection is equals to 1
	 * Postcondition: product name stored
	 */
	private static void inputProduct()
	 { 
        int createModel = 1; // 1 means create a new product, 2 means modify a existing product
        int modifyStatus = 1; // 1 means provide a new name, 2 means modity the data (if error happened)
        
		// Code for collect product information
		// Collect product name and validate it.
		System.out.println("Please enter the product name:");
		name = scanner.next().toLowerCase(); // Lower case required
		int validState = store.isValidName(storeName, name);		
		// If product name is invalid, ask user to try again.
		while (validState != 0)
		{
			if (validState == 1)
			    System.out.println("Invalid product name, please try again. (String with 3 <= length <= 10 )");
			if (validState == 2)
			{
				System.out.println("Product already exist. \nEnter in 1 to provide a new name\nEnter in 2 to modify the data");
			    modifyStatus = scanner.nextInt();
			}
			if (modifyStatus == 1)
			{
				// Provide a new name
			    System.out.println("Please enter the product name: ");
			    name = scanner.next().toLowerCase();
				validState = store.isValidName(storeName, name);
			}
			else
			{
				// Modify a existing product
				inputData();
				createModel = 2;
				break;
			}	
		}
		if (modifyStatus ==1)
		    inputData();
		createProduct(createModel);
    }
    
	/*
	 * Input product data 
	 * Precondition: function displayMenu() excuted and a valid name had been stored
	 * Postcondition: product data stored
	 */
	private static void inputData()
	{	
		boolean isValidNumber = false;		
		// Collect demand rate and validate it
		System.out.println("Please enter the demand rate: ");
		demandRate = scanner.nextInt();
		isValidNumber = store.isValidNumber(demandRate);
		// If demand rate is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid demand rate, please try again (Non-negative integer)");
			System.out.println("Please enter the demand rate: ");
			demandRate = scanner.nextInt();
			isValidNumber = store.isValidNumber(demandRate);	
		}
			
		// Collect setup cost and validate it
		System.out.println("Please enter the setup cost: ");
		setupCost = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If setup cost is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid setup cost, please try again (Non-negative integer)");
		    System.out.println("Please enter the setup cost: ");
			setupCost = scanner.nextDouble();
			isValidNumber = store.isValidNumber(setupCost);
		}

		//Collect unit cost and validate it
		System.out.println("Please enter the unit cost: ");
		unitCost = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If unit cost is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid unit cost, please try again (Non-negative integer)");
			System.out.println("Please enter the unit cost: ");
		    unitCost = scanner.nextDouble();
		   	isValidNumber = store.isValidNumber(unitCost);
		}	
			
		// Collect inventory cost and validate it
		System.out.println("Please enter the inventory cost: ");
		inventoryCost = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If inventory cost is invalid, ask user to try again.
		while(isValidNumber == false)
		{
			System.out.println("Invalid inventory cost, please try again (Non-negative integer)");
			System.out.println("Please enter the inventory cost: ");
			inventoryCost = scanner.nextDouble();
			isValidNumber = store.isValidNumber(inventoryCost);
		}
		
		// Collect selling price and validate it
		System.out.println("Please enter the selling price: ");
		sellingPrice = scanner.nextDouble();
		isValidNumber = store.isValidNumber(setupCost);
		// If selling price is invalid, ask user to try again.
		while (isValidNumber == false)
		{
			System.out.println("Invalid selling price, please try again (Non-negative integer)");
			System.out.println("Please enter the selling price: ");
			sellingPrice = scanner.nextDouble();
			isValidNumber = store.isValidNumber(setupCost);
		}
	}
    
	/*
	 * Create new product
	 * Ask for input model, 1 means create a new record, 2 means modify existing record
	 * Precondition: function inputProduct() excuted and valid product data had been stored
	 * Postcondition: product created, 
	 *                or a product record had been removed (when the space is full and user ask to delete), 
	 *                or a product had been modified (when product name already exist in the database).
	 */
	private static void createProduct(int createModel)
	{
		// Create a new product
		if (createModel == 1)
		{
			store.createProduct(storeName, name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
		}
		// Modify existing record
		else
		{
			store.modifyRecord(storeName, name, demandRate, setupCost, unitCost, inventoryCost, sellingPrice);
		}
		
	}
	
	/*
	 *  Ask user which product need to show and send product name to store.showProduct(), 
	 *  then receive product number and show product data
	 *  Precondition: variable menuSelection is equals to 2
	 *  Postcondition: Product data shows on the screen,
	 *                 or error message shows on the screen (Product not found, or no product record in database).
	 */
	private static void showProduct()
	{
		if (store.isNull(storeName))
			System.out.println("No data in store " + storeName);
		else
		{
			int listCount = listProduct();
			if (listCount == 0)
			{
				System.out.println("no products");
				storeMenuFlow();
			}
		    System.out.println("Enter the product name: ");
		    String name = scanner.next().toLowerCase(); // Variable name: product name that need to check
		    int productNumber = store.selectProduct(storeName, name);
			while (productNumber == -1)
		    {
		    	System.out.println("Product not found, please try again");
		    	System.out.println("Enter the product name: ");
			    name = scanner.next().toLowerCase(); // Variable name: product name that need to check
			    productNumber = store.selectProduct(storeName, name);
		    }
		    // Show the output
    	    System.out.println("Product name: " + store.getName(storeName, productNumber));
	   	    System.out.println("Demand rate: " +  store.getDemandRate(storeName,productNumber));
	        System.out.println("Setup cost: " +  store.getSetupCost(storeName,productNumber));
	   	    System.out.println("Unit cost: " +  store.getUnitCost(storeName,productNumber));
            System.out.println("Inventory cost: " +  store.getInventoryCost(storeName,productNumber));
		    System.out.println("Selling price: " +  store.getSellingPrice(storeName,productNumber));
	        System.out.println("------------------------------");
	        
	        // Ask for replenishment strategy.
	        System.out.println("Do you wish to show replenishment strategy?");
	        System.out.println("1 - Yes");
	        System.out.println("Other Number - No");
		    int selection = scanner.nextInt();
		    if (selection == 1)
		    	showReplenishmentStrategy(name);
		    else
		    	storeMenuFlow();
		}
	}
    
	private static int listProduct()
	{
		System.out.println("Product list:");
		int arraySize = store.getArraySize(storeName);
		int listCount = 0;
		for (int count = 0; count < arraySize; count++)
		{
			String name = store.getName(storeName,count);
			if (name != null)
			{
			    System.out.println(name);
			    listCount++;
			}
		}
		return listCount;
	}
	
	/*
	 * Delete a specific product
	 * Preconditions: User select 3- delete product in second-level menu
	 * Postconditions: Ask user to enter in a product to delete. If product exist, delete it.
	 */
	private static void deleteProduct()
	{
		if (store.isNull(storeName))
		{
			System.out.println("no products");
			storeMenuFlow();
		}
		else
		{
			int listCount = listProduct();
			if (listCount == 0)
			{
				System.out.println("no products");
				storeMenuFlow();
			}
			System.out.println("Which product do you want to delete?");
			String product = scanner.next();
			int deleteIndex = store.selectProduct(storeName, product);
			if (deleteIndex == -1)
				System.out.println("The product does not exist.");				
			else
			{
				store.removeRecord(storeName, deleteIndex);
				System.out.println("The product was deleted.");	
			}
			storeMenuFlow();	
		}
	}
	/*
	 * Ask user the product which need to provide replenishment strategy and show it
	 * Precondition: variable menuSelection is equals to 3
	 * Postcondition: Replenishment strategy shows on the screen,
	 *                or error message shows on the screen (Invalid week number, or no feasible replenishment strategy)
	 */
	private static void showReplenishmentStrategy(String productName)
	{
		if (store.isNull(storeName))
			System.out.println("No data in store " + storeName);
		else
		{				    
		    int recordNumber = store.selectProduct(storeName, productName);		    		    
		    System.out.println("Please enter the week number: ");
		    int week = scanner.nextInt();
		    // validate week number
		    while(week <=0)
		    {
		    	System.out.println("Week number should be more than or equals to 1 ");
		    	System.out.println("Please enter the week number: ");
			    week = scanner.nextInt();
		    }
		    // get required values of a specific product to generate replenishment strategy
		    int demandRate = store.getDemandRate (storeName, recordNumber);
		    double setupCost = store.getSetupCost(storeName, recordNumber);
		    double inventoryCost = store.getInventoryCost(storeName, recordNumber);
		    // Calculate and display the replenishment strategy
		    System.out.println("Week\tQuantity Order\tDemand\tInventory");
		    int EOQ = store.calculateEOQ(demandRate, setupCost, inventoryCost);
		    // If EOQ is less than demandRate, the replenishment strategy will be not available
		    boolean availability = true;
		    if (EOQ < demandRate)
		    {
		    	System.out.println("Is not possible to have a replacement strategy with the inputs, please type in the data again.");
		    	availability = false;
		    	createProduct(2);
		    }
		    if (availability == true)
		    {
		        for (int count = 1; count <= week; count++)
		        {
		    	    store.calculateReplenishmentStrategy(count, demandRate, EOQ);
		    	    int replenishQuantity = store.getReplenishQuantity();
		    	    int inventory =store.getInventory();
		    	    System.out.println(count + "\t" + replenishQuantity + "\t\t" + demandRate + "\t" + inventory);
		        }
		    }
		    // Store week number and get profit/ cost
		    if (storeName.equals("lambton"))
		    {
		    	weekLambton = resizeIntArray(weekLambton);
		    	profitLambton = resizeDoubleArray(profitLambton);
		    	weekLambton[recordNumber] = week;
		    	profitLambton[recordNumber] = store.getProfit("lambton", recordNumber, week);
		    	costLambton[recordNumber] = store.getCost("lambton", recordNumber, week);
		    	System.out.println("Total cost: "+ costLambton[recordNumber] );
		    	System.out.println("Net profit: "+ profitLambton[recordNumber] );
		    }
		    else
		    {
		    	weekCallaghan = resizeIntArray (weekCallaghan);
		    	profitCallaghan = resizeDoubleArray(profitCallaghan);
		    	weekCallaghan[recordNumber] = week;
		    	profitCallaghan[recordNumber] = store.getProfit(storeName, recordNumber, week);
		    	costCallaghan[recordNumber] = store.getCost("callaghan", recordNumber, week);
		    	System.out.println("Total cost: "+ costCallaghan[recordNumber] );
		    	System.out.println("Net profit: "+ profitCallaghan[recordNumber] );
		    }		    
		}
	}	
}

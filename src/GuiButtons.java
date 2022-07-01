//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * This class stores all the button for the FoodQuickGUI window.
 * 
 * @author Yureeve Rambridge
 */
public class GuiButtons{
	
	// Array list stores all string values retrieved from text fields
    static ArrayList<String> listOfStrings  = new ArrayList<String>();
	static // Array list stores all integer values retrieved from text fields
    ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
	// Below captures what is inserted into the text fields.
    // 'restaurantLocation' is used twice in the array list and once out side the array list so a variable was created.
	static String restaurantLocation = FoodQuickGUI.txtRestaurantLocation.getText();
	
	public static void addIncompletedOrders(){
		
		// Captures integers from the text fields 
		// Stores the order number in the array list 0
		listOfIntegers.add(InvoiceWriter.orderNumber());
		
		// Captures strings from the text fields 
		// Stores and gets Customers Name 0
		listOfStrings.add(FoodQuickGUI.txtCustomerName.getText());
		
		// Stores and gets Customers Number 1  
		listOfStrings.add(FoodQuickGUI.txtCustomerNumber.getText());
		
		// Stores and gets Customers Location 2
		listOfStrings.add(FoodQuickGUI.txtCustomerLocation.getText());
		
		// Stores and gets Customers Address 3
		listOfStrings.add(FoodQuickGUI.txtCustomerAddress.getText());
		
		// Stores and gets Customers Email 4
		listOfStrings.add(FoodQuickGUI.txtCustomerEmail.getText());
		
		// Stores and gets Restaurant Name 5
		listOfStrings.add(FoodQuickGUI.txtRestaurantName.getText());
		
		// Stores Restaurant Location 6
		listOfStrings.add(restaurantLocation);
		
		// Stores and gets Restaurant Number 7
		listOfStrings.add(FoodQuickGUI.txtRestaurantNumber.getText());
		listOfStrings.add(returnsAllOrders());
		
		// Stores and gets the assigned driver   9
		//listOfStrings.add(QuickFoodMS2.assignDriver(restaurantLocation));
		
		// Stores the Instructions 10
		listOfStrings.add(FoodQuickGUI.txtInstructions.getText());
		QuickFoodMS2.addNewCustomer(listOfStrings.get(0), listOfStrings.get(1), listOfStrings.get(2), listOfStrings.get(3), listOfStrings.get(4));
		
		// Inserts new fields invoice table.
		 try {
			 QuickFoodMS2.PreparedStatement = QuickFoodMS2.Connection.prepareStatement("INSERT INTO invoice_table("
			 		+ "Order_Number, Customer_Name, customer_Number, Customer_Loction, Customer_Adress, Customer_Email, Restaurant_Name, Restaurant_Location,"
			 		+ "Restaurant_Contact, Customer_Order, Customer_Instructions)values(?,?,?,?,?,?,?,?,?,?,?)");
			 
			 // Start int value for the index for PreparedStatement.setString statement.
			 int indexValuesOfStrings = 2;
			 
			 // Loops through string array list and adds value to the invoice table.
			 for (String  stringValue: listOfStrings) {
				 QuickFoodMS2.PreparedStatement.setString(indexValuesOfStrings, stringValue);
				 indexValuesOfStrings++;
			 }
			 // Start int value for the index for PreparedStatement.setString statement.
			 int indexValuesOfIntegers = 1;
			 
			 // Loops through integer array list and adds value to the invoice table.
			 for (Integer integerValue : listOfIntegers) {
				 QuickFoodMS2.PreparedStatement.setLong(indexValuesOfIntegers, (long)integerValue); 
			 }
			 QuickFoodMS2.PreparedStatement.executeUpdate();
			 
			 // Adds Customer And order number details to "customer and order number" table.
			 QuickFoodMS2.addCustomerNameAndOrderNumber(listOfStrings.get(0),listOfIntegers.get(0));
			 
			 // Reloads tables in FoodQuickGUI window
			 QuickFoodMS2.TableLoad();
			 
			 // Pop up alert is shown to inform user that the Invoice Table has been updated
			 JOptionPane.showMessageDialog(null, "Invoice Table Updated");
		 }catch (SQLException e1){
				   e1.printStackTrace();
		 }
		 

			// Adds Customer And order number details to "customer and order number" table.
			QuickFoodMS2.addCustomerNameAndOrderNumber(listOfStrings.get(0), listOfIntegers.get(0));
			listOfStrings.clear();
			listOfIntegers.clear();
	}
	/**
	 * Add New Button method
	 */
	public static void finalizeButton(){

		// Inserts new fields invoice table.

		String test = FoodQuickGUI.txtSearch.getText();
		int test1 = 0;
		
		if (test.contains("")) {
			addIncompletedOrders();
//			System.out.println() 
		}else {
			test1 = Integer.valueOf (FoodQuickGUI.txtSearch.getText()) ;
//			System.out.println() 
		}


		// Stores the total amount in the array list 1
		listOfIntegers.add(returnsTotalAmount());
		
		// Captures integers from the text fields 
		// Stores the order number in the array list 0
		//listOfIntegers.add(InvoiceWriter.orderNumber());
		
		// Captures strings from the text fields 
		// Stores and gets Customers Name 0
		listOfStrings.add(FoodQuickGUI.txtCustomerName.getText());
		
		// Stores and gets Customers Number 1  
		listOfStrings.add(FoodQuickGUI.txtCustomerNumber.getText());
		
		// Stores and gets Customers Location 2
		listOfStrings.add(FoodQuickGUI.txtCustomerLocation.getText());
		
		// Stores and gets Customers Address 3
		listOfStrings.add(FoodQuickGUI.txtCustomerAddress.getText());
		
		// Stores and gets Customers Email 4
		listOfStrings.add(FoodQuickGUI.txtCustomerEmail.getText());
		
		// Stores and gets Restaurant Name 5
		listOfStrings.add(FoodQuickGUI.txtRestaurantName.getText());
		
		// Stores Restaurant Location 6
		listOfStrings.add(FoodQuickGUI.txtRestaurantLocation.getText());
		
		// Stores and gets Restaurant Number 7
		listOfStrings.add(FoodQuickGUI.txtRestaurantNumber.getText());
		
		// Stores all orders 8
		listOfStrings.add(returnsAllOrders());
		
		// Stores and gets the assigned driver   9
		listOfStrings.add(QuickFoodMS2.assignDriver(restaurantLocation));
		
		// Stores the Instructions 10
		listOfStrings.add(FoodQuickGUI.txtInstructions.getText());


		 try {
			 QuickFoodMS2.PreparedStatement = QuickFoodMS2.Connection.prepareStatement("UPDATE invoice_table "
			 		+ "SET Customer_Name = ?, customer_Number = ?, Customer_Loction = ?, Customer_Adress = ?, Customer_Email = ?, Restaurant_Name = ?, Restaurant_Location = ?, "
			 		+ "Restaurant_Contact = ?, Customer_Order = ?, Driver_Name = ?, Customer_Instructions = ?, Total_Amount  = ? "
			 		+ "WHERE Order_Number = ? OR Customer_Name  = ? OR Customer_Number  = ? ");

			 // Start int value for the index for PreparedStatement.setString statement.
			 int indexValuesOfStrings = 1;
			 
			 // Loops through string array list and adds value to the invoice table.
			 for (String  stringValue: listOfStrings) {
				 QuickFoodMS2.PreparedStatement.setString(indexValuesOfStrings, stringValue);
				 indexValuesOfStrings++;
			 }
			 // Start int value for the index for PreparedStatement.setString statement.
			 int indexValuesOfIntegers = 12;
			 
			 // Loops through integer array list and adds value to the invoice table.
			 for (Integer integerValue : listOfIntegers) {
				 QuickFoodMS2.PreparedStatement.setLong(indexValuesOfIntegers, (long)integerValue);
//				 indexValuesOfIntegers= 13; 
			 }
			 QuickFoodMS2.PreparedStatement.setLong(13, (long) test1);
			 QuickFoodMS2.PreparedStatement.setString(14, test);
			 QuickFoodMS2.PreparedStatement.setString(15, test);
			 QuickFoodMS2.PreparedStatement.executeUpdate();
			 
			 // Reloads tables in FoodQuickGUI window
			 QuickFoodMS2.TableLoad();
			 
			 // Pop up alert is shown to inform user that the Invoice Table has been updated
			 JOptionPane.showMessageDialog(null, "Invoice Table Updated");
		 }catch (SQLException e1){
				   e1.printStackTrace();
		 }
			// Adds new customers to 'customer detail' table.
		 	//QuickFoodMS2.addNewCustomer(listOfStrings.get(0), listOfStrings.get(1), listOfStrings.get(2), listOfStrings.get(3), listOfStrings.get(4));
		 
			// Adds new restaurant to 'restaurant detail' table.
			QuickFoodMS2.addNewRestaurant(listOfStrings.get(5), restaurantLocation, listOfStrings.get(7));
			// Adds Customer And Location details to "customer and location" table.
			QuickFoodMS2.addCustomerAndLocation(listOfStrings.get(2), listOfStrings.get(0));

         try {
    		 
    		 QuickFoodMS2.PreparedStatement = QuickFoodMS2.Connection.prepareStatement("select Order_Number from invoice_table WHERE Order_Number = ? OR Customer_Name  = ? OR Customer_Number  = ?");
    		 
    		 QuickFoodMS2.PreparedStatement.setLong(1, (long) test1);
    		 QuickFoodMS2.PreparedStatement.setString(2, test);
    		 QuickFoodMS2.PreparedStatement.setString(3, test);
    		 
             QuickFoodMS2.ResultSet = QuickFoodMS2.PreparedStatement.executeQuery();
             QuickFoodMS2.ResultSet.next();
             InvoiceWriter.invoiceGenerator(Integer.valueOf (QuickFoodMS2.ResultSet.getString(1)));
             
			 // Adds Customer And order number details to "customer and order number" table.
			 QuickFoodMS2.addCustomerNameAndOrderNumber(listOfStrings.get(0), Integer.valueOf (QuickFoodMS2.ResultSet.getString(1)));
             
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		 // Method Generates invoice 
		 
		 // Deletes every thing in the Arraylists when order is completed.
         //updateCustomerButton();
		listOfStrings.clear();
		listOfIntegers.clear();
		orders.clear();
		prices.clear();
		// Resets text field blank.
		//clearButton();
	}
	/**
	 * Clears all text in JText fields input.
	 */
	public static void clearButton() {
     	// Text fields reset to blank
		// Customer Name Field
		FoodQuickGUI.txtCustomerName.setText("");
		
		// Customer Number Field
		FoodQuickGUI.txtCustomerNumber.setText("");
		
		// Customer Location Field
		FoodQuickGUI.txtCustomerLocation.setText("");
		
		// Customer Address Field
		FoodQuickGUI.txtCustomerAddress.setText("");
		
		// Customer Email Field
		FoodQuickGUI.txtCustomerEmail.setText("");
		
		// Restaurant Name Field
		FoodQuickGUI.txtRestaurantName.setText("");
		
		// Restaurant Location Field
		FoodQuickGUI.txtRestaurantLocation.setText("");
		
		// Restaurant Number Field
		FoodQuickGUI.txtRestaurantNumber.setText("");
		
		// Search Field
		FoodQuickGUI.txtSearch.setText("");
		
		// Enter Order Field
		FoodQuickGUI.txtEnterOrder.setText("");
		
		//Quantity of Order Field
		FoodQuickGUI.txtQuantityofOrder.setText("");
		
		// Instructions Field
		FoodQuickGUI.txtInstructions.setText("");
		
		// Price Of Order
		FoodQuickGUI.txtPriceOfOrder.setText("");
		
		// Puts the cursor into Customer Name Field
		FoodQuickGUI.txtCustomerName.requestFocus();
	}
	 /**
	 * Method searches for customers using order number, name or contact number.
	 */
	public static void searchFunction() {
		 try {
			 	// Gets value entered into txtSearch text field.
	            String searchValue = FoodQuickGUI.txtSearch.getText();
	            
	            // Searches the search value.
	            QuickFoodMS2.PreparedStatement = QuickFoodMS2.Connection.prepareStatement("SELECT * FROM invoice_table WHERE Order_Number = ? "
	            																			+ "OR Customer_Name = ? OR customer_Number = ?");
	            // Searches if it's an Order number.
	            QuickFoodMS2.PreparedStatement.setString(1, searchValue);
	            
	            // Searches if it's a customer's name.
	            QuickFoodMS2.PreparedStatement.setString(2, searchValue);
	            
	            // Searches if it's s customer's number.
	            QuickFoodMS2.PreparedStatement.setString(3, searchValue);
	            QuickFoodMS2.ResultSet = QuickFoodMS2.PreparedStatement.executeQuery();

	            // If the search value exists all fields in the row are retrieved
	            if(QuickFoodMS2.ResultSet.next()==true)
	            {
	                // Text fields in Food Quick window are populated with the respective data.
	            	// Customer Name 
					FoodQuickGUI.txtCustomerName.setText(QuickFoodMS2.ResultSet.getString(2));
					
					// Customer Number 
					FoodQuickGUI.txtCustomerNumber.setText(QuickFoodMS2.ResultSet.getString(3));
					
					// Customer Location
					FoodQuickGUI.txtCustomerLocation.setText(QuickFoodMS2.ResultSet.getString(4));
					
					// Customer Address
					FoodQuickGUI.txtCustomerAddress.setText(QuickFoodMS2.ResultSet.getString(5));
					
					// Customer Email
					FoodQuickGUI.txtCustomerEmail.setText(QuickFoodMS2.ResultSet.getString(6));
					
					// Restaurant Name
					FoodQuickGUI.txtRestaurantName.setText(QuickFoodMS2.ResultSet.getString(7));
					
					// Restaurant Location
					FoodQuickGUI.txtRestaurantLocation.setText(QuickFoodMS2.ResultSet.getString(8));
					
					// Restaurant Number
					FoodQuickGUI.txtRestaurantNumber.setText(QuickFoodMS2.ResultSet.getString(9));
					
					// Customer Instructions
					FoodQuickGUI.txtInstructions.setText(QuickFoodMS2.ResultSet.getString(12));
					
					// Set cursor back to customer name
					FoodQuickGUI.txtSearch.requestFocus();
	            }else {
	            	FoodQuickGUI.txtInstructions.setText("");
	            }
	        }catch (SQLException ex) { 
	        	ex.printStackTrace();
	        }
	 }
	 /**
	 * Method updates the existing customer details after the search is completed.
	 */
	public static void updateCustomerButton() {
	 	// ArrayList a to store updated values of customers and condition values to be run simultaneously.
	    ArrayList<String> updateCustomer = new ArrayList<String>();
	    // Gets Customer Name from the customer name text filed
	    updateCustomer.add(FoodQuickGUI.txtCustomerName.getText());
	    
	    // Gets Customer Number from the customer number text filed
	    updateCustomer.add(FoodQuickGUI.txtCustomerNumber.getText());
	    
	    // Gets Customer Location from the customer location text filed
	    updateCustomer.add(FoodQuickGUI.txtCustomerLocation.getText());
	    
	    // Gets Customer Address from the customer address text filed
	    updateCustomer.add(FoodQuickGUI.txtCustomerAddress.getText());
	    
	    // Gets Customer Email from the customer email text filed
	    updateCustomer.add(FoodQuickGUI.txtCustomerEmail.getText());
	    
	    // Gets Customer Search value from the search text filed to check if SQL condition is met
	    updateCustomer.add(FoodQuickGUI.txtSearch.getText());
	    
	    // Gets Customer Search value from the  search text filed to check if SQL condition is met
	    updateCustomer.add(FoodQuickGUI.txtSearch.getText());
	    
	    // Gets Customer Number to check if SQL condition is met
	    updateCustomer.add(FoodQuickGUI.txtCustomerNumber.getText());
	    
	    // Gets Customer Name to check if SQL condition is met
	    updateCustomer.add(FoodQuickGUI.txtCustomerName.getText());
		 try {
			 // Gets the searched row.
			 QuickFoodMS2.PreparedStatement = QuickFoodMS2.Connection.prepareStatement(""
			 		+ "UPDATE customer_details "
			 		+ "SET Customer_Name = ?, customer_Number= ?, Customer_Loction = ?, Customer_Adress = ?, Customer_Email  = ? "
			 		+ "WHERE customer_Number = ? OR Customer_Name = ? OR Customer_Number = ? OR Customer_Name = ?");
			 
			 // Updates the customer details table by looping through the array list and performing the updates
			 for (int x = 0; x < updateCustomer.size(); x++) {
				 
				 // Adds one to 'x' to select the cell that the updated item must be inserted into.
				 QuickFoodMS2.PreparedStatement.setString(x + 1, updateCustomer.get(x));
			 }
			 // Update is executed
			 QuickFoodMS2.PreparedStatement.executeUpdate();
			 
			 // Reloads updated Table and tables
			 QuickFoodMS2.TableLoad();
			 
			 // Pop up alert is shown to inform user that the customer has been updated
			JOptionPane.showMessageDialog(null, "Customer Updated");						           
		 }catch (SQLException e1){
			e1.printStackTrace();
			}
	 }
	 	// Static ArrayList the store all orders and costs individually 
	 	// ArrayList for Order 
	    static ArrayList<String> orders = new ArrayList<String>();
	    
	    // ArrayList for prices 
	    static ArrayList<Double> prices = new ArrayList<Double>();
	 /**
	 * Button to get a single order thats inputed the text fields at a time
	 */
	public static void orderButton() {
		 // Storage for price and quantity of orders 
		 int price, quantity;
		 
		 // Gets the food the customer wants
		 String enteredOrder = FoodQuickGUI.txtEnterOrder.getText();
		 
		 // Gets Cost of that item
		 price = Integer.parseInt(FoodQuickGUI.txtPriceOfOrder.getText());  
		 
		 // Gets quantity of item that customer wants 
		 quantity = Integer.parseInt( FoodQuickGUI.txtQuantityofOrder.getText());
		 
		 // Concatenates the 'quantity' with the item (enteredOrder) the customer wants and the 'price' to be used later and stored in to the 'orders' Array list.
		 orders.add(quantity + " x " + enteredOrder + " (" + "R" + price +")");
		 
		 // Calculated the total for this item by 'price' x the 'quantity' and stores it in the 'price' array list.
		 prices.add((double) (price * quantity));
		 
		 // After that is resets the text fields of the following to  blank so next order can be taken.
		 FoodQuickGUI.txtEnterOrder.setText("");
		 FoodQuickGUI.txtPriceOfOrder.setText("");
		 FoodQuickGUI.txtQuantityofOrder.setText("");
		 
		 // Pop window informing that the order has been added and to enter the next order.
		 JOptionPane.showMessageDialog(null, "Order Added, Please enter next order.");		
	 }
	 /**
	 * Method calculates the sum or the array list "prices" and returns it
	 * 
	 * @return sum int value adds all the values in the array list.
	 * 
	 * @see prices
	 */
	public static Integer returnsTotalAmount() {
		 double sum = prices.stream().mapToDouble(a -> a).sum();
		 return (int) sum;
	 }
	 /**
	 * Method gets all the items from the array list "orders" and returns it.
	 * 
	 * @return concatenatedOrder string value combines all the orders into a string
	 */
	public static String returnsAllOrders() {
		 String concatenatedOrder;
		 
		 	// If there are no orders the field is set to null.
	        if(orders.isEmpty()) {
	        	 concatenatedOrder = null;
	        } else {
	        	// else orders are added together
	        	concatenatedOrder = "";
	        	
	        	// Loops through array list and adds them together
				 for(String order : orders){
					 concatenatedOrder += order + "\r\n ";
				 }
	        }
		return concatenatedOrder;
	 }
}

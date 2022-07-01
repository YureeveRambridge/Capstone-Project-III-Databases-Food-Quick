 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
/**
 * This File only deals with updating and extracting data from the QuickFoodMS database tables.
 * 
 * @author Yureeve Rambridge
 */
public class QuickFoodMS2 {
	static Connection Connection;
	static Statement Statement;
	static ResultSet ResultSet;
	static PreparedStatement PreparedStatement;
	/**
	 * Method for the Connection to the database QuickFoodMS thought the project.
	 */
	public static void connect(){
		// Getting Access to the database
			try {
				Connection = DriverManager.getConnection(
						//DataBase Location
						"jdbc:sqlserver://LAPTOP-44TLE8MQ;database=QuickFoodMS" ,
						// User Name
						"QuickFoodMS" ,
						// Password
						"QuickFoodMS"
						);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	/**
	 * This Method Loads all the databases tables into the windows builder application
	 * when called.
	 */
	public static void TableLoad(){
		// Hash Map to store all the tables form the database and where the table must be selected from in the database.
		 Map<String, JTable> tableMap = new HashMap<>();
		/*
		 * Below adds the Code to the Hash Map to get the tables from the Database along with where the Table must be stored in the window builder application.
		 * The Code is the Key and the Table where it must be stored is the Value.
		 */
		 // customer_details Table:
		 tableMap.put("SELECT * FROM customer_details", FoodQuickGUI.customerDetailsTable);
		 
		 // restaurant_details Table
		 tableMap.put("SELECT * FROM restaurant_details", FoodQuickGUI.restaurantDetailsTable);
		 
		 // driver_details Table
		 tableMap.put("SELECT * FROM driver_details", FoodQuickGUI.driverDeatilsTable);
		 
		 // customer_and_location Table alphabetically sorted by the Customer_location column
		 tableMap.put("SELECT * FROM customer_and_location ORDER BY Customer_loction ASC", FoodQuickGUI.customerAndLocationTable);
		 
		 // customer_and_order_number Table alphabetically sorted by the CustomerName column
		 tableMap.put("SELECT * FROM customer_and_order_number ORDER BY Customer_Name ASC", FoodQuickGUI.customerAndOrderNumberTable);
		 
		 // invoice_table Table 
		 tableMap.put("SELECT * FROM invoice_table", FoodQuickGUI.invoiceTable);
		 
		 // invoice_table Table that shows customers with missing information 
		 tableMap.put("SELECT * FROM invoice_table "
		 		+ "WHERE Customer_Order IS NULL OR Customer_Name IS NULL OR customer_Number IS NULL OR Customer_Email IS NULL "
		 		+ "OR Customer_Loction IS NULL OR Customer_Adress IS NULL OR Restaurant_Name IS NULL OR Restaurant_Location IS NULL "
		 		+ "OR Restaurant_Contact IS NULL ", FoodQuickGUI.incompletedTable);
		 
		 // Below Runs above process.
    	try {
    		// Loops through the Hash Map and loads all the data from the DB into the tables in the window.
    		for (Map.Entry<String, JTable> getTable : tableMap.entrySet()) {
    			
    			// Takes the key from the hash map and gets the table form the DB.
        		PreparedStatement = Connection.prepareStatement(getTable.getKey());
        		ResultSet = PreparedStatement.executeQuery();
        		
        		// This takes the hash value and loads the table to the window
        		getTable.getValue().setModel(DbUtils.resultSetToTableModel(ResultSet));   
    	    }
    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("Error in TableLoad");    		
    		}
    }
	/**
	 * Adds new customer to table when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param customerName string value 
	 * @param customerNumber string value
	 * @param customerLocation string value
	 * @param customerAddress string value
	 * @param customerEmail string value
	 */
	public static void addNewCustomer(String customerName, String customerNumber, String customerLocation, String customerAddress, String customerEmail) {
		 try {
			 // Connects to customer_details table.
			PreparedStatement = Connection.prepareStatement("INSERT INTO customer_details("
				 		+ "Customer_Name, customer_Number, Customer_Loction, Customer_Adress, Customer_Email"
				 		+ ")values(?,?,?,?,?)");
			
			 // Adds customerName to index 1
			 PreparedStatement.setString(1, customerName);
			 
			 // Adds customer Number to index 2
			 PreparedStatement.setString(2, customerNumber);
			 
			// Adds customer Location to index 3
			 PreparedStatement.setString(3, customerLocation);
			 
			// Adds customer Address to index 4
			 PreparedStatement.setString(4, customerAddress);
			 
			// Adds customer Email to index 5
			 PreparedStatement.setString(5, customerEmail);
			 PreparedStatement.executeUpdate();
			 
			 // Reloads updated Table and tables
			 TableLoad();
			 
			 // Pop up alert is shown to inform user that the customer_details has been updated
			 JOptionPane.showMessageDialog(null, "customer_details updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Adds Restaurant details to table when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param restaurantName string value
	 * @param restaurantNumber string value
	 * @param restaurantLocation string value
	 */
	public static void addNewRestaurant(String restaurantName, String restaurantNumber, String restaurantLocation) {
		 try {
			PreparedStatement = Connection.prepareStatement("INSERT INTO restaurant_details("
				 		+ "Restaurant_Name, Restaurant_Location, Restaurant_Contact"
				 		+ ")values(?,?,?) ");
			 PreparedStatement.setString(1, restaurantName);
			 PreparedStatement.setString(2, restaurantNumber);
			 PreparedStatement.setString(3, restaurantLocation);
			 PreparedStatement.executeUpdate();
			 
			 // Reloads updated Table and tables
			 TableLoad();
			 JOptionPane.showMessageDialog(null, "restaurant_details updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Assigns Driver with the least amount of deliveries to the customer when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param customerLocation string value
	 * @return driverName string value. 
	 * 					  Driver with least amount of deliveries is returned
	 */
	public static String assignDriver(String customerLocation) {
		
		// SQl query statement. Gets the minimum amount of deliveries from driver_details.
		String query = "SELECT Name, MIN(Number_Of_Deliveries) AS del FROM driver_details WHERE Location = '"+customerLocation+"' GROUP BY Name ORDER BY del ASC";
		// storage for drivers name
		
		String driverName = "";
		
		// Storage for number of deliveries
		int numberOfdeliveries;
		 try {
			 QuickFoodMS2.Statement = QuickFoodMS2.Connection.createStatement();
			 
			 // Executes Query
			 QuickFoodMS2.ResultSet = QuickFoodMS2.Statement.executeQuery(query);
			 
			 // Loops thought ResultSet
	         while(QuickFoodMS2.ResultSet.next()){
	        	 
	        	 // Gets the drivers Name and deliveries
	 				driverName = QuickFoodMS2.ResultSet.getString("Name");
	 				numberOfdeliveries = QuickFoodMS2.ResultSet.getInt("del");
	 				
	 				// Method updates driver deliveries
	 				QuickFoodMS2.updateDeliveries(driverName, numberOfdeliveries);
	 				break;
	          }
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 // Driver name is returned for the invoice
		return driverName;
	}
	/**
	 * Increases the amount of deliveries for a driver when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param driverName string value 
	 * @param numberOfdeliveries int value
	 */
	public static void updateDeliveries(String driverName, int numberOfdeliveries) {
		
		// Storage for deliveries and adds one to it when driver is assigned 
		int updated_Deliveries = numberOfdeliveries + 1;
		
		// SQl query statement adds updated deliveries to table 
		String update_Deliveries= "UPDATE driver_details SET Number_Of_Deliveries = "+updated_Deliveries+" WHERE Name = '"+driverName+"';";
		try {
			PreparedStatement = Connection.prepareStatement(update_Deliveries);
			PreparedStatement.executeUpdate();
			// Reloads updated Table and tables
			TableLoad();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method updates Customer Name And Order Number table when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param customerName string value
	 * @param orderNumber int value 
	 */
	public static void addCustomerNameAndOrderNumber(String customerName, int orderNumber) {
		
		// SQL query statement 
		String addCustomerNameAndOrderNUmber= "INSERT INTO customer_and_order_number (Customer_Name, Order_Number) values(?,?)";
		
		 try {// Inserts new details in to table
				PreparedStatement = Connection.prepareStatement(addCustomerNameAndOrderNUmber );
				PreparedStatement.setString(1, customerName);
				PreparedStatement.setLong(2, orderNumber);
				PreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 // Reloads updated Table and tables
		 TableLoad();
		 
		// Pop window informing that the Customer Name And Order Number has been updated.
		 JOptionPane.showMessageDialog(null, "Customer Name And Order Number updated");
	}
	/**
	 * Method updates Customer Name And Location table when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param customerLocation string value
	 * @param customerName string value
	 */
	public static void addCustomerAndLocation(String customerLocation, String customerName) {
		// SQL query statement 
		String addCustomerNameAndOrderNUmber= "INSERT INTO customer_and_location (Customer_Loction, Customer_Name) values(?,?)";
		
		 try {// Inserts new details in to table
				PreparedStatement = Connection.prepareStatement(addCustomerNameAndOrderNUmber );
				PreparedStatement.setString(1, customerLocation);
				PreparedStatement.setString(2, customerName);
				PreparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 // Reloads updated Table and tables
		 TableLoad();
		 
		// Pop window informing that the Customer Name And location table has been updated.
		JOptionPane.showMessageDialog(null, "Customer And Location updated");
	}
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 * This Class Generates invoices.
 * 
 * @author Yureeve Rambridge
 */
public class InvoiceWriter {
	/**
	 * Generates a random number and returns it to make each invoice unique when finalize button is clicked.
	 * Method called in GuiButton in addNewCustomer.
	 * 
	 * @return orderNumber	int orderNumber to be assigned to a customer.
	 */
	public static int orderNumber(){
		// ArrayList stores all invoice number to be compared to newly generated invoice numbers to prevent duplicates.
		ArrayList<Integer> orderList = new ArrayList<Integer>();
		
		Random rand = new Random();
		
		int  orderNumber = rand.nextInt(1000);
		
		// Zero added to prevent invoice number zero from being created 
		orderList.add(0);
		
		// Initializes the while loop.
		boolean loopBreaker = true;
		
		while (loopBreaker) {
			// For Loops iterates for as long as the ArrayList is long.
			for (int i = 0; i < orderList.size(); i++) {
				
				// If the new invoice number is Not equal to the invoice numbers already stored in the array list the it adds the new invoice number to the list.
				if (orderNumber != orderList.get(i)) {
					orderList.add(orderNumber);
					
					// When the above if condition is met the "loopBreaker" variable is changed to false to break the loop.
					loopBreaker=false;
				}else {
					// Hence if the two numbers equal each other a new number is generated and the check procedure is done again.
					orderNumber = rand.nextInt(1000);
				}
			}
		}
		return orderNumber;
	}
	/**
	 * Method creates new invoice when finalize button is clicked.
	 * Method called in GuiButton in finalizeButton.
	 * 
	 * @param orderNumber int value 
	 * 	gets the order number from the invoice table and creates an invoice.
	 */
	public static void invoiceGenerator(int orderNumber) {
		// Connects to the sever.
		QuickFoodMS2.connect();
		
		// Hash map for the JasperPrint Function
		HashMap<String, Object> map = new HashMap<String, Object>(1);
		
		// Order number is added to the hash map
		map.put("Order_Number", orderNumber);
		
		// Pop up show user invoice is being created.
		JOptionPane.showMessageDialog(null, "Generating Invoice");
		try {
			// SQL query statement
			String sql = "Select  Order_Number,  Customer_Name,  customer_Number, Customer_Email, Customer_Loction, Customer_Adress,  Total_Amount "
						+ "FROM  invoice_table WHERE Order_Number = "+orderNumber+";";
			
			// File location of the JRXml file
			JasperDesign invoice = JRXmlLoader.load("src//Invoice.jrxml");
			
			// Runs the sql statement
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			
			// Results are added to the file.
			invoice.setQuery(updateQuery);
			
			// Report is generated 
			JasperReport Jreport = JasperCompileManager.compileReport(invoice);
			JasperPrint JasperPrint = JasperFillManager.fillReport(Jreport, map, QuickFoodMS2.Connection);
			
			// Shows the report
			JasperViewer.viewReport(JasperPrint);
			
		}catch(Exception e2){
			System.err.println("Invoice report error/n" + e2);
			JOptionPane.showMessageDialog(null, e2);
		}
	}
}
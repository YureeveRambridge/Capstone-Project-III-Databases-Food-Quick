import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import org.apache.log4j.BasicConfigurator;
/**
 * @author Yureeve Rambridge
 *	This application program helps Food Quick keep track of the orders and distributes accordingly.
 */
public class FoodQuickGUI {
	/**
	 * The JFrame for the window
	 */
	private JFrame frame;
	/**
	 * JTextFields 
	 */
	static JTextField txtCustomerName;
	static JTextField txtCustomerNumber;
	static JTextField txtCustomerLocation;
	static JTextField txtCustomerAddress;
	static JTextField txtCustomerEmail;
	static JTextField txtRestaurantName;
	static JTextField txtRestaurantNumber;
	static JTextField txtRestaurantLocation;
	static JTextField txtEnterOrder;
	static JTextField txtPriceOfOrder;
	static JTextField txtQuantityofOrder;
	static JTextField txtSearch;
	static JTextField txtInstructions;
	/**
	 * JTable Field 
	 */
	static JTable customerDetailsTable;
	static JTable restaurantDetailsTable;
	static JTable driverDeatilsTable;
	static JTable customerAndLocationTable;
	static JTable customerAndOrderNumberTable;
	static JTable invoiceTable;
	static JTable incompletedTable;
	/**
	 * Main Method
	 * 
	 * @param args string array
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodQuickGUI window = new FoodQuickGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Creates the application.
	 */
	public FoodQuickGUI() {
		initialize();
		QuickFoodMS2.connect();
		QuickFoodMS2.TableLoad();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1349, 844);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Food Quick JLabel
		JLabel lblNewLabel = new JLabel("Food Quick ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(24, 11, 94, 14);
		frame.getContentPane().add(lblNewLabel);
		
		// Customer Details JPanel
		JPanel customerDetailsPanel = new JPanel();
		customerDetailsPanel.setBorder(new TitledBorder(null, "Customer Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		customerDetailsPanel.setBounds(24, 36, 368, 181);
		frame.getContentPane().add(customerDetailsPanel);
		customerDetailsPanel.setLayout(null);
		
		// Customer Name JLabel 
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 24, 112, 14);
		customerDetailsPanel.add(lblNewLabel_1);
		
		// Customer Name Text field 
		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(154, 21, 198, 20);
		customerDetailsPanel.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		// Customer Number Text field
		txtCustomerNumber = new JTextField();
		txtCustomerNumber.setColumns(10);
		txtCustomerNumber.setBounds(154, 49, 198, 20);
		customerDetailsPanel.add(txtCustomerNumber);
		
		// Customer Number JLabel 
		JLabel lblNewLabel_1_1 = new JLabel("Customer Number");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 52, 125, 14);
		customerDetailsPanel.add(lblNewLabel_1_1);
		
		// Customer Location Text field
		txtCustomerLocation = new JTextField();
		txtCustomerLocation.setColumns(10);
		txtCustomerLocation.setBounds(154, 80, 198, 20);
		customerDetailsPanel.add(txtCustomerLocation);
		
		// Customer Location JLabel 
		JLabel lblNewLabel_1_2 = new JLabel("Customer Location");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 83, 125, 14);
		customerDetailsPanel.add(lblNewLabel_1_2);
		
		// Customer Address Text field
		txtCustomerAddress = new JTextField();
		txtCustomerAddress.setColumns(10);
		txtCustomerAddress.setBounds(154, 111, 198, 20);
		customerDetailsPanel.add(txtCustomerAddress);
		
		// Customer Address JLabel 
		JLabel lblNewLabel_1_3 = new JLabel("Customer Address");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 114, 125, 14);
		customerDetailsPanel.add(lblNewLabel_1_3);
		
		// Customer Email Text field
		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setColumns(10);
		txtCustomerEmail.setBounds(154, 142, 198, 20);
		customerDetailsPanel.add(txtCustomerEmail);
		
		// Customer Email JLabel 
		JLabel lblNewLabel_1_4 = new JLabel("Customer Email");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(10, 145, 112, 14);
		customerDetailsPanel.add(lblNewLabel_1_4);
		
		// Restaurant Details JPanel
		JPanel restaurantDetailsPanel = new JPanel();
		restaurantDetailsPanel.setLayout(null);
		restaurantDetailsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Restaurant Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		restaurantDetailsPanel.setBounds(24, 228, 368, 114);
		frame.getContentPane().add(restaurantDetailsPanel);
		
		// Restaurant Name JLabel
		JLabel lblNewLabel_1_5 = new JLabel("Restaurant Name");
		lblNewLabel_1_5.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(10, 25, 112, 14);
		restaurantDetailsPanel.add(lblNewLabel_1_5);
		
		// Restaurant Name text field
		txtRestaurantName = new JTextField();
		txtRestaurantName.setColumns(10);
		txtRestaurantName.setBounds(154, 22, 198, 20);
		restaurantDetailsPanel.add(txtRestaurantName);
		
		// Restaurant Number text field
		txtRestaurantNumber = new JTextField();
		txtRestaurantNumber.setColumns(10);
		txtRestaurantNumber.setBounds(154, 83, 198, 20);
		restaurantDetailsPanel.add(txtRestaurantNumber);
		
		// Restaurant Number JLabel
		JLabel lblNewLabel_1_1_1 = new JLabel("Restaurant Number");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 85, 125, 14);
		restaurantDetailsPanel.add(lblNewLabel_1_1_1);
		
		// Restaurant Location text field
		txtRestaurantLocation = new JTextField();
		txtRestaurantLocation.setColumns(10);
		txtRestaurantLocation.setBounds(154, 52, 198, 20);
		restaurantDetailsPanel.add(txtRestaurantLocation);
		
		// Restaurant Location JLabel
		JLabel lblNewLabel_1_2_1 = new JLabel("Restaurant Location");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(10, 54, 134, 14);
		restaurantDetailsPanel.add(lblNewLabel_1_2_1);
		
		// Finalize JButton
		JButton addNewButton = new JButton("Finalize");
		addNewButton.addActionListener(new ActionListener() {
			/**
			 * Finalize JButton
			 */
			public void actionPerformed(ActionEvent e) {
				// finalizeButton Method
				GuiButtons.finalizeButton();
			}
		});
		addNewButton.setBounds(422, 511, 130, 23);
		frame.getContentPane().add(addNewButton);
		
		// Order Panel
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(null);
		orderPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Order Input", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		orderPanel.setBounds(24, 353, 368, 181);
		frame.getContentPane().add(orderPanel);
		
		// Enter Order JLabel
		JLabel enterOrderLabel = new JLabel("Enter Order");
		enterOrderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		enterOrderLabel.setBounds(10, 36, 89, 14);
		orderPanel.add(enterOrderLabel);
		
		// Enter Order text field
		txtEnterOrder = new JTextField();
		txtEnterOrder.setColumns(10);
		txtEnterOrder.setBounds(139, 22, 198, 28);
		orderPanel.add(txtEnterOrder);
		
		// Price Of Order text field
		txtPriceOfOrder = new JTextField();
		txtPriceOfOrder.setColumns(10);
		txtPriceOfOrder.setBounds(139, 61, 47, 20);
		orderPanel.add(txtPriceOfOrder);
		
		// Price Of Order JLabel
		JLabel riceOfOrderLabel = new JLabel("Price Of Order");
		riceOfOrderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		riceOfOrderLabel.setBounds(10, 67, 99, 14);
		orderPanel.add(riceOfOrderLabel);
		
		// Quantity of Order JLabel
		JLabel quantityofOrderLabel = new JLabel("Quantity of Order");
		quantityofOrderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		quantityofOrderLabel.setBounds(10, 98, 112, 14);
		orderPanel.add(quantityofOrderLabel);
		
		// Quantity of Order text field
		txtQuantityofOrder = new JTextField();
		txtQuantityofOrder.setColumns(10);
		txtQuantityofOrder.setBounds(139, 92, 47, 20);
		orderPanel.add(txtQuantityofOrder);
		
		// Instructions text field
		txtInstructions = new JTextField();
		txtInstructions.setColumns(10);
		txtInstructions.setBounds(139, 123, 198, 44);
		orderPanel.add(txtInstructions);
		
		// Instructions JLabel
		JLabel InstructionsLable = new JLabel("Instructions");
		InstructionsLable.setFont(new Font("Arial", Font.PLAIN, 14));
		InstructionsLable.setBounds(10, 137, 89, 14);
		orderPanel.add(InstructionsLable);
		
		// Clear Text Fields JButton
		JButton clearTextFiledsButton = new JButton("Clear Text Fileds");
		clearTextFiledsButton.addActionListener(new ActionListener() {
			/**
			 * Clear Button
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				GuiButtons.clearButton();				
			}
		});
		clearTextFiledsButton.setBounds(562, 509, 130, 26);
		frame.getContentPane().add(clearTextFiledsButton);
		
		// Update Existing Customer JButton
		JButton updateButton = new JButton("Update Existing Customer");
		updateButton.addActionListener(new ActionListener() {
			/**
			 * Update Button
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				GuiButtons.updateCustomerButton();
			}
		});
		updateButton.setBounds(560, 476, 157, 23);
		frame.getContentPane().add(updateButton);
		
		// Search JPanel
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(834, 324, 438, 132);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		// Search text field
		txtSearch = new JTextField();
		txtSearch.setBounds(120, 78, 207, 20);
		searchPanel.add(txtSearch);
		txtSearch.addKeyListener(new KeyAdapter() {
			/**
			 * Search Function
			 * 
			 * @param e
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				GuiButtons.searchFunction();
			}
		});
		txtSearch.setColumns(10);
		
		// Search JLabel
		JLabel searchLable = new JLabel("Search Customer By Cell Number, Order Number or Name:");
		searchLable.setBounds(33, 21, 381, 46);
		searchPanel.add(searchLable);
		searchLable.setFont(new Font("Arial", Font.PLAIN, 14));
		
		// Invoice JPanel
		JPanel InviocePanel = new JPanel();
		InviocePanel.setBorder(new TitledBorder(null, "Invioce Table", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		InviocePanel.setBounds(10, 532, 1313, 132);
		frame.getContentPane().add(InviocePanel);
		InviocePanel.setLayout(null);
		
		// Invoice JScrollPane
		JScrollPane invoiceScrollPane = new JScrollPane();
		invoiceScrollPane.setBounds(10, 21, 1293, 100);
		InviocePanel.add(invoiceScrollPane);
		
		// Invoice Table
		invoiceTable = new JTable();
		invoiceScrollPane.setViewportView(invoiceTable);
		
		// Customer And Location JPanel
		JPanel customerAndLocationPanel = new JPanel();
		customerAndLocationPanel.setBorder(new TitledBorder(null, "Customer And Location", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		customerAndLocationPanel.setBounds(1045, 36, 267, 127);
		frame.getContentPane().add(customerAndLocationPanel);
		customerAndLocationPanel.setLayout(null);
		
		// Customer And Location Scroll Pane
		JScrollPane customerAndLocationScrollPane = new JScrollPane();
		customerAndLocationScrollPane.setBounds(10, 15, 245, 101);
		customerAndLocationPanel.add(customerAndLocationScrollPane);
		
		// Customer And Location Table
		customerAndLocationTable = new JTable();
		customerAndLocationScrollPane.setViewportView(customerAndLocationTable);
		
		// Customer And Order Number Panel
		JPanel CustomerAndOrderNumberPanel = new JPanel();
		CustomerAndOrderNumberPanel.setBorder(new TitledBorder(null, "Customer And Order Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		CustomerAndOrderNumberPanel.setBounds(423, 311, 352, 127);
		frame.getContentPane().add(CustomerAndOrderNumberPanel);
		CustomerAndOrderNumberPanel.setLayout(null);
		
		// Customer And Order Number Scroll Pane
		JScrollPane customerAndOrderNumberScrollPane = new JScrollPane();
		customerAndOrderNumberScrollPane.setBounds(10, 14, 329, 102);
		CustomerAndOrderNumberPanel.add(customerAndOrderNumberScrollPane);
		
		// Customer And Order Number Table
		customerAndOrderNumberTable = new JTable();
		customerAndOrderNumberScrollPane.setViewportView(customerAndOrderNumberTable);
		
		// Driver JPanel
		JPanel DriverPanel = new JPanel();
		DriverPanel.setBorder(new TitledBorder(null, "Drivers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		DriverPanel.setBounds(891, 170, 432, 127);
		frame.getContentPane().add(DriverPanel);
		DriverPanel.setLayout(null);
		
		// Driver Details Scroll Pane
		JScrollPane driverDetailsScrollPane = new JScrollPane();
		driverDetailsScrollPane.setBounds(10, 21, 410, 95);
		DriverPanel.add(driverDetailsScrollPane);
		
		// Driver Details Table
		driverDeatilsTable = new JTable();
		driverDetailsScrollPane.setViewportView(driverDeatilsTable);
		
		// Restaurant JPanel
		JPanel ResturantPanel = new JPanel();
		ResturantPanel.setBorder(new TitledBorder(null, "Resturants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ResturantPanel.setBounds(424, 170, 432, 127);
		frame.getContentPane().add(ResturantPanel);
		ResturantPanel.setLayout(null);
		
		// Restaurant Details Scroll Pane
		JScrollPane restaurantDetailsScrollPane = new JScrollPane();
		restaurantDetailsScrollPane.setBounds(10, 15, 410, 101);
		ResturantPanel.add(restaurantDetailsScrollPane);
		
		// Restaurant Details Table
		restaurantDetailsTable = new JTable();
		restaurantDetailsScrollPane.setViewportView(restaurantDetailsTable);
		
		// addOrderButton JButton
		JButton addOrderButton = new JButton("Add Order");
		addOrderButton.setBounds(422, 474, 128, 26);
		frame.getContentPane().add(addOrderButton);
		
		// Customer JPanel 
		JPanel CustomerPanel = new JPanel();
		CustomerPanel.setBorder(new TitledBorder(null, "Customers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		CustomerPanel.setBounds(423, 36, 594, 132);
		frame.getContentPane().add(CustomerPanel);
		CustomerPanel.setLayout(null);
		
		// Customer Details Scroll Pane
		JScrollPane customerDetailsScrollPane = new JScrollPane();
		customerDetailsScrollPane.setBounds(10, 20, 574, 101);
		CustomerPanel.add(customerDetailsScrollPane);
		
		// Customer Details Table
		customerDetailsTable = new JTable();
		customerDetailsScrollPane.setViewportView(customerDetailsTable);
		
		// Incomplete JPanel 
		JPanel incompletedPanel = new JPanel();
		incompletedPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Incompleted Orders", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		incompletedPanel.setBounds(10, 664, 1311, 130);
		frame.getContentPane().add(incompletedPanel);
		incompletedPanel.setLayout(null);
		
		// Incomplete Scroll Pane
		JScrollPane incompletedscrollPane = new JScrollPane();
		incompletedscrollPane.setBounds(6, 16, 1295, 108);
		incompletedPanel.add(incompletedscrollPane);
		
		// Incomplete Table
		incompletedTable = new JTable();
		incompletedscrollPane.setViewportView(incompletedTable);
		
		JButton addIncompletedOrder = new JButton("Add Incompleted Order");
		addIncompletedOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiButtons.addIncompletedOrders();
			}
		});
		addIncompletedOrder.setBounds(422, 440, 167, 23);
		frame.getContentPane().add(addIncompletedOrder);
		
		// Add order Button
		addOrderButton.addActionListener(new ActionListener() {
			/**
			 * Add order button
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				// Add order Method
				GuiButtons.orderButton();
			}
		});
	}
}

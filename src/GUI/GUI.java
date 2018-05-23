/**
 * 
 */
package GUI;

import java.awt.GraphicsConfiguration;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import CSV.CSVFormatException;
import CSV.ExportManifest;
import CSV.InitializeItems;
import CSV.LoadManifest;
import CSV.LoadSales;
import Delivery.DeliveryException;
import Stock.Item;
import Stock.Stock;
import Stock.StockException;
import Stock.Store;

import java.awt.*;

/**
 * The 'GUI' class holds the code neccessary to create a GUI to display the combined information in the Item, Stock, Store, and Truck classes.
 * 
 * @author Mary Millar
 *
 */
public class GUI extends JFrame implements ActionListener, Runnable {
	
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	
	//declaring the panels
	private JPanel panelDisplay;
	private JPanel panelTwo;
	private JPanel panelBtn;
	private JPanel panelFour;
	private JPanel panelFive;
	
	private JPanel panelStoreCap;
	
	//declaring the buttons
	private JButton buttonStoreInv;
	private JButton buttonExpMan;
	private JButton buttonLoadMan;
	private JButton buttonLoadSales;
	private JButton buttonLoadItemProp;
	
	//declaring the labels
	private JLabel labelStoreCap;
	
	//declaring the tables
	JTable storeInvTable;

	//text area
	JTextArea areaDisplay;
	
	private static final int DEFAULT_TEXT_SIZE = 24;

	//Inventory table variables
	DefaultTableModel model;
	
	//Declaring back-end variables;
	Stock inventory = new Stock();
	Item[] inventoryArray;
	
	
	public GUI(String message) {
		super(message);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI();	
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//Get event source 
		Object src = arg0.getSource(); 
		
		//If the 'view store inventory' button is pressed, view the current inventory
		if (src == buttonStoreInv) { 
			createInvTable();
		} 
		
		//If the 'export manifest' button is pressed, prompt the user to save generated manifest
		else if (src == buttonExpMan){
			exportManifest();
		}
		
		//If the 'load sales log' button is pressed, prompt the user to load a sales log. Also update the capital.
		else if (src == buttonLoadSales){
			loadSalesLog();
			createInvTable();
			labelStoreCap.setText("Store Capital: " + Store.getInstance().getCapitalString());
		}
		
		//If the 'load manifest' button is pressed, prompt the user to load a manifest. Also update the capital.
		else if (src == buttonLoadMan){
			loadManifest();
			createInvTable();
			labelStoreCap.setText("Store Capital: " + Store.getInstance().getCapitalString());
			
		}
		
		//If the 'load item properties document' button is pressed, prompt the user to load document that holds that information.
		else if (src == buttonLoadItemProp) {
			initItemPropDoc();
		}
	}

	/**
	 * This method initialises a panel to be inserted into the GUI.
	 * 
	 * @param c The background colour of the panel.
	 * 
	 * @return A JPanel object that becomes a panel that can be inserted into the GUI.
	 */
	private JPanel createPanel(Color c) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(c);
		return tempPanel;
	}

	/**
	 * This method initialises a button to be inserted into the GUI.
	 * 
	 * @param string The text to be shown on top of the button.
	 * 
	 * @return A JButton object that becomes a button that can be inserted into the GUI.
	 */
	private JButton createButton(String string) {
		JButton tempButton = new JButton();
		tempButton.setText(string);
		tempButton.addActionListener(this);
		return tempButton;
	}
	
	/**
	 * This method initialises a label to be inserted into the GUI.
	 * 
	 * @param string The text that is to make up the label.
	 * 
	 * @return A JLabel object that becomes a label that can be inserted into the GUI.
	 */
	private JLabel createLabel(String string) {
		JLabel tempLabel = new JLabel();
		tempLabel.setText(string);
		return tempLabel;
	}
	
	/**
	 * This method initialises an area that can be filled with text.
	 * 
	 * @param fontSize The size of the text that can be displayed.
	 * 
	 * @return A JTextArea object that becomes a text-fillable area that can be inserted into the GUI.
	 */
	private JTextArea createTextArea(int fontSize)
	{
		JTextArea newDisplay = new JTextArea(); 
		
		newDisplay.setEditable(false); 
		newDisplay.setLineWrap(true);
		
		newDisplay.setFont(new Font( "Arial", Font.BOLD, fontSize ) ); 
		newDisplay.setBorder(BorderFactory.createEtchedBorder());
		
		return newDisplay;
	}
	
	/** *
	* 
	* This method gives the ability to simply add a component to given grid bag layout locations.
	* 
	* @param c the component to add
	* @param constraints the grid bag constraints to use
	* @param x the x grid position
	* @param y the y grid position
	* @param w the grid width of the component
	* @param h the grid height of the component
	*/
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x; 
		constraints.gridy = y; 
		constraints.gridwidth = w; 
		constraints.gridheight = h; 
		jp.add(c, constraints);
	}
	
	/**
	 * This method 
	 * 
	 */
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		panelBtn.setLayout(layout);
		
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		
		//defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 50;
		constraints.weighty = 50;
		
		//add buttons to panel
		addToPanel(panelBtn, buttonStoreInv, constraints, 2, 0, 2, 1); 
		addToPanel(panelBtn, buttonLoadItemProp, constraints, 4, 0, 2, 1); 
		
		addToPanel(panelBtn, buttonLoadMan, constraints, 1, 2, 2, 1); 
		addToPanel(panelBtn, buttonLoadSales, constraints, 3, 2, 2, 1);
		addToPanel(panelBtn, buttonExpMan, constraints, 5, 2, 2, 1);
		
		//add labels to panel
		addToPanel(panelStoreCap, labelStoreCap, constraints, 0, 0, 2, 1);
		
	}
	
	private void layoutCentrePanel(){	
		//Set layout
		panelDisplay.setLayout(new BorderLayout());
		
		//add relevant items
		panelDisplay.add(areaDisplay, BorderLayout.CENTER);
	}

	
	/**
	 * The method to initialse and create all panels and and JFrame size
	 */
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Create panels
		panelDisplay = createPanel(Color.WHITE);
		panelTwo = createPanel(Color.LIGHT_GRAY);
		panelBtn = createPanel(Color.LIGHT_GRAY);
		panelFour = createPanel(Color.LIGHT_GRAY);
		panelFive = createPanel(Color.LIGHT_GRAY);
		panelStoreCap = createPanel(Color.LIGHT_GRAY);
		
		//Create buttons
		buttonStoreInv = createButton("View Store Inventory");
		buttonExpMan = createButton("Export Manifest");
		buttonLoadMan = createButton("Load Manifest");
		buttonLoadSales = createButton("Load Sales Log");
		buttonLoadItemProp = createButton("Load Item Properties Document");
		
		//Create Labels
		labelStoreCap = createLabel("Store Capital: " + Store.getInstance().getCapitalString());
		
		// Create text area
		areaDisplay = createTextArea(DEFAULT_TEXT_SIZE);

		//layout buttons
		layoutButtonPanel();
		
		layoutCentrePanel();
		
		this.getContentPane().add(panelDisplay, BorderLayout.CENTER);
		this.getContentPane().add(panelTwo, BorderLayout.NORTH);
		this.getContentPane().add(panelBtn, BorderLayout.SOUTH);
		this.getContentPane().add(panelFour, BorderLayout.EAST);
		this.getContentPane().add(panelFive, BorderLayout.WEST);
		this.getContentPane().add(panelStoreCap, BorderLayout.NORTH);
		
		repaint();
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new GUI(Store.getInstance().getName() + "'s Inventory Management Application"));

	}
	
	
	//CREATING ITEM TABLES - STORE INVENTORY
	public void createInvTable() {
		//getting the 'current' inventory from the Store class
		inventory = Store.getInstance().getInventory();
		//initialising length of inventoryArray
		if (inventory.getManifestPrintStyle() != "") {
			inventoryArray = new Item[inventory.getItems().length];
			
			//assigning items in 'inventory' to 'inventoryArray'
			inventoryArray = inventory.getItems();       
			
	        //create table with data
	        model = new DefaultTableModel();
	        model.addColumn("Item Name");
	        model.addColumn("Manufacturing Cost");
	        model.addColumn("Sell Price");
	        model.addColumn("Reorder Point");
	        model.addColumn("Reorder Amount");
	        model.addColumn("Temperature");
	        model.addColumn("Quantity");
        
        //for each item in inventory, add row to table
	        for (int i = 0; i < inventoryArray.length; i++) {
	        		Object[] rowData = {
	        				inventoryArray[i].getName(), 
	        				"$" + inventoryArray[i].getCost() + "0", 
	        				"$" + inventoryArray[i].getPrice() + "0",
	        				inventoryArray[i].getRePoint(),
	        				inventoryArray[i].getReAmount(),
	        				inventoryArray[i].getTemp(),
	        				inventory.getAmount(inventoryArray[i])
	        				};
	        		model.addRow(rowData);
	        }
	        
	        storeInvTable = new JTable(model);
	                 
	        //add the table to the frame
	        this.add(new JScrollPane(storeInvTable));
	         
	        this.setVisible(true);
        }
        else {
        		JOptionPane.showMessageDialog(this, "There are no items in the inventory.", "Notice", JOptionPane.WARNING_MESSAGE);
        }
		
	}
	
	public void initItemPropDoc() {
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open...");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    String file = dialog.getDirectory() + dialog.getFile();
	    
	    if (dialog.getFile() != null) {
		    try {
				InitializeItems.InitializeItems(file);
				JOptionPane.showMessageDialog(this, "Success! Item Properties have been initialised.", "Loaded Items Properties Document", JOptionPane.PLAIN_MESSAGE);
			} catch (CSVFormatException | StockException | IOException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Item Initialisation Failure", JOptionPane.ERROR_MESSAGE);
			}
	    }
	    else {}
	}
	
	public void exportManifest() {
		FileDialog dialog = new FileDialog((Frame)null, "Save File...");
		dialog.setMode(FileDialog.SAVE);
		dialog.setVisible(true);
		
		String directory = null;
		
		if (dialog.getFile().length() >= 4) {
			if (dialog.getFile().substring(dialog.getFile().length() - 4).equals(".csv")) {
				directory = dialog.getDirectory() + dialog.getFile();
			}
			else {
				directory = dialog.getDirectory() + dialog.getFile() + ".csv";
			}
		}
		else {
			directory = dialog.getDirectory() + dialog.getFile() + ".csv";
		}
		
		if (dialog.getFile() != null) {
			try {
				ExportManifest.ExportManifestCSV(directory);
				JOptionPane.showMessageDialog(this, "Success! Current manifest has been exported to '" + dialog.getDirectory() + "' under the name '" + dialog.getFile() + "'.", "Export Manifest", JOptionPane.PLAIN_MESSAGE);
			}
			catch (DeliveryException | StockException e){ 
				JOptionPane.showMessageDialog(this, e.getMessage(), "Manifest Export Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {}
	}
	
	public void loadManifest() {
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open...");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    String file = dialog.getDirectory() + dialog.getFile();
	    
	    if (dialog.getFile() != null) {
		    try {
		    		LoadManifest.LoadManifest(file);
		    		createInvTable();
		    		JOptionPane.showMessageDialog(this, "Success! The selected manifest has been loaded.", "Load Manifest", JOptionPane.PLAIN_MESSAGE);
		    }
		    catch (CSVFormatException | StockException | IOException | DeliveryException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Load Manifest Failure", JOptionPane.ERROR_MESSAGE);
		    }
	    }
	    else {}
	}
	
	public void loadSalesLog() {
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open...");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    String file = dialog.getDirectory() + dialog.getFile();
	    
	    if (dialog.getFile() != null) {
		    try {
		    		LoadSales.LoadSales(file);
		    		createInvTable();
		    		JOptionPane.showMessageDialog(this, "Success! The selected sales log has been loaded.", "Load Sales Log", JOptionPane.PLAIN_MESSAGE);
		    } catch (CSVFormatException | StockException | IOException e1) {
		    		JOptionPane.showMessageDialog(this, e1.getMessage(), "Load Sales Log Failure", JOptionPane.ERROR_MESSAGE);
		    }
	    }
	    else {}
	}
}

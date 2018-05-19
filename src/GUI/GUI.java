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
import CSV.InitializeItems;
import Stock.Item;
import Stock.Stock;
import Stock.StockException;
import Stock.Store;

import java.awt.*;

/**
 * @author Mez
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

	
	//Declaring back-end variables;
	Stock inventory;
	Item[] inventoryArray;
	
	
	/**
	 * @param args
	 */
	public GUI(String message) {
		super(message);
	}

	@Override
	public void run() {
		createGUI();	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//Get event source 
		Object src = arg0.getSource(); 
		
		//Consider the alternatives - not all active at once.
		if (src == buttonStoreInv) { 
			//JButton btn = ((JButton)src);
			createInvTable();
            //areaDisplay.setText(btn.getText().trim());
            panelDisplay.add(storeInvTable);
		} 
		else if (src == buttonExpMan){
			JButton btn = ((JButton)src);
			areaDisplay.setText(btn.getText().trim());
		}
		else if (src == buttonLoadSales){
			JOptionPane.showMessageDialog(this, "A Stupid Warning Message", "Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
		}
		else if (src == buttonLoadMan){
			JOptionPane.showMessageDialog(this, "A Stupid Error Message", "Wiring Class: Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (src == buttonLoadItemProp) {
			InitItemPropDoc();
		}
	}

	private JPanel createPanel(Color c) {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(c);
		return tempPanel;
	}

	private JButton createButton(String string) {
		JButton tempButton = new JButton();
		tempButton.setText(string);
		tempButton.addActionListener(this);
		return tempButton;
	}
	
	private JLabel createLabel(String string) {
		JLabel tempLabel = new JLabel();
		tempLabel.setText(string);
		return tempLabel;
	}
	
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
	* A convenience method to add a component to given grid bag
	* layout locations. *
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
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		panelBtn.setLayout(layout);
		
		//layout code
		
		//add components to grid
		GridBagConstraints constraints = new GridBagConstraints();
		
		//Defaults
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
		SwingUtilities.invokeLater(new GUI(Store.getInstance().getName() + "'s Stock Manager"));

	}
	
	
	//CREATING ITEM TABLES - STORE INVENTORY
	public void createInvTable() {
		//getting the 'current' inventory from the Store class
		inventory = Store.getInstance().getInventory();
		Store.getInstance().setInventory(inventory);
		inventoryArray = inventory.getItems();        
        
        //create table with data
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item Name");
        model.addColumn("Manufacturing Cost");
        model.addColumn("Sell Price");
        model.addColumn("Reorder Point");
        model.addColumn("Reorder Amount");
        model.addColumn("Temperature");
        model.addColumn("Quantity");
        
        //for each item in inventory, add row to table
//        if (inventoryArray.length > 0) {
//	        for (int i = 0; i < inventoryArray.length; i++) {
//	        		Object[] rowData = {
//	        				inventoryArray[i].getName(), 
//	        				inventoryArray[i].getCost(), 
//	        				inventoryArray[i].getPrice(),
//	        				inventoryArray[i].getRePoint(),
//	        				inventoryArray[i].getReAmount(),
//	        				inventoryArray[i].getTemp(),
//	        				inventory.getAmount(inventoryArray[i])
//	        				};
//	        		model.addRow(rowData);
//	        }
//	        JOptionPane.showMessageDialog(this, "Items have been added to the inventory.", "Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
//        }
//        else {
//        	JOptionPane.showMessageDialog(this, "There are no items in the inventory.", "Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
//        }
        
        JTable table = new JTable(model);
        
        JOptionPane.showMessageDialog(this, "The current inventory is being shown.", "Wiring Class: Warning", JOptionPane.WARNING_MESSAGE);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setVisible(true);
		
	}
	
	public void InitItemPropDoc() {
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    String file = dialog.getFile();
	    System.out.println(file + " chosen.");
	    try {
			InitializeItems.InitializeItems(file);
			JOptionPane.showMessageDialog(this, "Success! Item Properties have been initialised.", "Loaded Items Properties Document", JOptionPane.WARNING_MESSAGE);
		} catch (CSVFormatException e) {
			JOptionPane.showMessageDialog(this, "File is not in the correct format. Please try again.", "Item Initialisation Failure", JOptionPane.ERROR_MESSAGE);
		} catch (StockException e) {
			JOptionPane.showMessageDialog(this, "Failed adding stock to inventory. Please try again.", "Item Initialisation Failure", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "IOException error. Please try again.", "Item Initialisation Failure", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
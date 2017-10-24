package src;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.print.*;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.*;
import javax.swing.BorderFactory;

public class LoanCalculator {

	private JFrame frame;
	private JTextField CapitalAmount_textbox;
	private JTextField months_textbox;
	private JTextField APR_textbox;
	private JTextField MonthlyPayment_textbox;
	private JButton btnAddToGrid;
	private JTable table;
	boolean cap_amt_empty_txt = false;
	boolean months_empty_txt = false;
	boolean apr_rate_empty_txt = false;
	boolean monthly_payment_txt = false;
	private boolean invalid_input = false;
	
	Object[] columns  = {"Capital Amount($)", "Months", "APR(%)", "Monthly Payment($)"};
	DefaultTableModel model = new DefaultTableModel ();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoanCalculator window = new LoanCalculator();
					window.frame.setVisible(true);
	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoanCalculator() {
			initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 614, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/* Calculation button start here */
		JButton btnCalculate = new JButton("Calculate");
		ButtonModel mod = btnCalculate.getModel();
		ButtonEnable btnEnable = new ButtonEnable(mod);
		
		/* Border Line */
		Border InvalidInputBorder = BorderFactory.createLineBorder(Color.RED);
		Border ValidInputBorder = BorderFactory.createLineBorder(Color.GRAY);
		
		/* Initialize Capital Amount textbox */
		CapitalAmount_textbox = new JTextField();
		CapitalAmount_textbox.setForeground(Color.BLACK);
		CapitalAmount_textbox.addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				CapitalAmount_textbox.setToolTipText("Capital Amount cannot be $0");
			}
		});
		CapitalAmount_textbox.setBounds(244, 24, 130, 26);
		frame.getContentPane().add(CapitalAmount_textbox);
		CapitalAmount_textbox.setColumns(10);
		
		CapitalAmount_textbox.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				if(Double.parseDouble(CapitalAmount_textbox.getText()) == 0)
				{
					CapitalAmount_textbox.setBorder(InvalidInputBorder);
					invalid_input = true;
				}else
				{
					CapitalAmount_textbox.setBorder(ValidInputBorder);
					invalid_input = false;
				}
			}
		});
		
		/* Initialize months textbox */
		months_textbox = new JTextField();
		months_textbox.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(Integer.parseInt(months_textbox.getText()) >= 12 && Integer.parseInt(months_textbox.getText()) <= 72 && !months_textbox.getText().isEmpty())
				{
					months_textbox.setBorder(ValidInputBorder);
					invalid_input = false;
				}else
				{
					months_textbox.setBorder(InvalidInputBorder);
					invalid_input = true;
				}
			}
		});
		months_textbox.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				months_textbox.setToolTipText("Months must be between 12-72 months");
			}
		});
		months_textbox.setBounds(244, 62, 130, 26);
		frame.getContentPane().add(months_textbox);
		months_textbox.setColumns(10);
		
		/* Initialize APR textbox */
		APR_textbox = new JTextField();
		APR_textbox.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(Integer.parseInt(APR_textbox.getText()) >= 0 && Integer.parseInt(APR_textbox.getText()) <= 75)
				{
					APR_textbox.setBorder(ValidInputBorder);
					invalid_input = false;
				}else
				{
					APR_textbox.setBorder(InvalidInputBorder);
					invalid_input = true;
				}
			}
		});
		APR_textbox.addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				APR_textbox.setToolTipText("APR must be between 0-72%");
			}
		});
		APR_textbox.setBounds(244, 100, 130, 26);
		frame.getContentPane().add(APR_textbox);
		APR_textbox.setColumns(10);
		
		/* Initialize monthly payment textbox */
		MonthlyPayment_textbox = new JTextField();
		MonthlyPayment_textbox.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(Double.parseDouble(MonthlyPayment_textbox.getText()) == 0)
				{
					MonthlyPayment_textbox.setBorder(InvalidInputBorder);
					invalid_input = true;
				}else
				{
					MonthlyPayment_textbox.setBorder(ValidInputBorder);
					invalid_input = false;
				}
			}
		});
		MonthlyPayment_textbox.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				MonthlyPayment_textbox.setToolTipText("Monthly payment amount");
			}
		});
		MonthlyPayment_textbox.setBounds(244, 138, 130, 26);
		frame.getContentPane().add(MonthlyPayment_textbox);
		MonthlyPayment_textbox.setColumns(10);
		
		JLabel lblCapitalAmount = new JLabel("Capital Amount");
		lblCapitalAmount.setBounds(103, 29, 112, 16);
		frame.getContentPane().add(lblCapitalAmount);
		
		JLabel lblOfMonths = new JLabel("Months");
		lblOfMonths.setBounds(103, 67, 57, 16);
		frame.getContentPane().add(lblOfMonths);
		
		JLabel lblApr = new JLabel("APR");
		lblApr.setBounds(103, 105, 33, 16);
		frame.getContentPane().add(lblApr);
		
		JLabel lblMonthlyPayment = new JLabel("Monthly Payment");
		lblMonthlyPayment.setBounds(103, 143, 112, 16);
		frame.getContentPane().add(lblMonthlyPayment);
		
		/* Verify at least 3 input */
		Document doc1 = CapitalAmount_textbox.getDocument();
		Document doc2 = months_textbox.getDocument();
		Document doc3 = APR_textbox.getDocument();
		Document doc4 = MonthlyPayment_textbox.getDocument();
		
		btnEnable.addDocument(doc1);
		btnEnable.addDocument(doc2);
		btnEnable.addDocument(doc3);
		btnEnable.addDocument(doc4);
		btnEnable.documentChanged();
		
		btnCalculate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String months;
				String capital_amount;
				String apr;
				String monthly_payment;
				
				try
				{
					months = months_textbox.getText();
					capital_amount = CapitalAmount_textbox.getText();
					apr = APR_textbox.getText();
					monthly_payment = MonthlyPayment_textbox.getText();					
					
					if(monthly_payment.isEmpty())
					{
						Finance payment = new Finance(capital_amount, months, apr, "");
						MonthlyPayment_textbox.setText(payment.toString());
					}else if(months.isEmpty())
					{
						Finance mps = new Finance(capital_amount, "", apr,monthly_payment);
						months_textbox.setText(mps.monthtoString());
					}else if(capital_amount.isEmpty())
					{
						Finance principle_amount = new Finance("", months, apr, monthly_payment);
						CapitalAmount_textbox.setText(principle_amount.toString());
								
					}else if(apr.isEmpty())
					{
						Finance apr_rate = new Finance(capital_amount, months, "", monthly_payment);
						APR_textbox.setText(apr_rate.toString());
					}
					
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
				}
			}
			
			
		});
		btnCalculate.setBounds(491, 226, 117, 29);
		frame.getContentPane().add(btnCalculate);
		
		
		/* Add to grid button starts here. */ 
		btnAddToGrid = new JButton("Add to Grid");
		btnAddToGrid.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					model.addRow(new Object[] {CapitalAmount_textbox.getText(), months_textbox.getText(), APR_textbox.getText(), MonthlyPayment_textbox.getText()});
					
				}catch(Exception e)
				{
					System.out.println("Invalid Data to Add into Grid");
				}
			}
		});
		btnAddToGrid.setBounds(372, 226, 117, 29);
		frame.getContentPane().add(btnAddToGrid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 267, 602, 199);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		
		/* Print button starts here */
		JButton btnPrin = new JButton("Print");
		btnPrin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				MessageFormat header = new MessageFormat("Loan Calculator Print");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}") ;
				try
				{
					table.print(JTable.PrintMode.NORMAL, header, footer);
					
				}catch(java.awt.print.PrinterException e)
				{
					System.err.format("Cannot print.", e.getMessage());
				}
			}
		});
		
		btnPrin.setBounds(491, 465, 117, 29);
		frame.getContentPane().add(btnPrin);
		
		JLabel lblNewLabel = new JLabel("$");
		lblNewLabel.setBounds(377, 29, 13, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("$");
		label.setBounds(377, 143, 13, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setBounds(377, 105, 13, 16);
		frame.getContentPane().add(label_1);
		
		JLabel lblMonth = new JLabel("month");
		lblMonth.setBounds(376, 67, 41, 16);
		frame.getContentPane().add(lblMonth);
		
	}
	
	/* Check all the text field is filled with at least 3 textboxes. */
	public class ButtonEnable implements DocumentListener
	{
		private ButtonModel btnMod;
		private List<Document> documents  = new ArrayList<Document>();
		
		public ButtonEnable(ButtonModel btnMod)
		{
			this.btnMod = btnMod;
		}
		
		public void addDocument(Document doc)
		{
			doc.addDocumentListener(this);
			this.documents.add(doc);
			//documentChanged();
		}
		
		public void documentChanged()
		{
			boolean btnEnable = false;

			if((documents.get(0).getLength() >0 && documents.get(1).getLength() > 0 && documents.get(2).getLength() > 0) || 
			   (documents.get(0).getLength() >0 && documents.get(1).getLength() > 0 && documents.get(3).getLength() > 0) ||
			   (documents.get(0).getLength() >0 && documents.get(2).getLength() > 0 && documents.get(3).getLength() > 0) || 
			   (documents.get(1).getLength() >0 && documents.get(2).getLength() > 0 && documents.get(3).getLength() > 0))
			{
				if(invalid_input == false)
				{
					btnEnable = true;	
				}
						
			}
			btnMod.setEnabled(btnEnable);
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			documentChanged();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			documentChanged();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			documentChanged();
		}
		
	}
}

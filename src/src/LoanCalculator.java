package src;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class LoanCalculator {

	private JFrame frame;
	private JTextField CapitalAmount_textbox;
	private JTextField months_textbox;
	private JTextField APR_textbox;
	private JTextField MonthlyPayment_textbox;
	private JButton btnAddToGrid;
	private JTable table;
	
	Object[] columns  = {"Capital Amount", "Months", "APR", "Monthly Payment"};
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
		frame.setBounds(100, 100, 614, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		CapitalAmount_textbox = new JTextField();
		CapitalAmount_textbox.setBounds(244, 24, 130, 26);
		frame.getContentPane().add(CapitalAmount_textbox);
		CapitalAmount_textbox.setColumns(10);
		
		months_textbox = new JTextField();
		months_textbox.setBounds(244, 62, 130, 26);
		frame.getContentPane().add(months_textbox);
		months_textbox.setColumns(10);
		
		APR_textbox = new JTextField();
		APR_textbox.setBounds(244, 100, 130, 26);
		frame.getContentPane().add(APR_textbox);
		APR_textbox.setColumns(10);
		
		MonthlyPayment_textbox = new JTextField();
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
		
		/**/
		JButton btnCalculate = new JButton("Calculate");
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
						finance payment = new finance(capital_amount, months, apr, "");
						MonthlyPayment_textbox.setText(payment.toString());
					}else if(months.isEmpty())
					{
						finance mps = new finance(capital_amount, "", apr,monthly_payment);
						months_textbox.setText(mps.monthtoString());
					}else if(capital_amount.isEmpty())
					{
						finance principle_amount = new finance("", months, apr, monthly_payment);
						CapitalAmount_textbox.setText(principle_amount.toString());
								
					}else if(apr.isEmpty())
					{
						finance apr_rate = new finance(capital_amount, months, "", monthly_payment);
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
		
		btnAddToGrid = new JButton("Add to Grid");
		btnAddToGrid.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					model.addRow(new Object[] {CapitalAmount_textbox.getText(), months_textbox.getText(),APR_textbox.getText(), MonthlyPayment_textbox.getText()});
					
				}catch(Exception e)
				{
					
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
		
	}
}

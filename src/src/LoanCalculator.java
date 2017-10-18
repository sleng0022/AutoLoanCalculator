package src;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoanCalculator {

	private JFrame frame;
	private JTextField CapitalAmount_textbox;
	private JTextField months_textbox;
	private JTextField APR_textbox;
	private JTextField MonthlyPayment_textbox;

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
		frame.setBounds(100, 100, 450, 300);
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
				int months;
				double capital_amount;
				double apr;
				double monthly_payment;
				try
				{
					months = Integer.parseInt(months_textbox.getText());
					capital_amount = Double.parseDouble(CapitalAmount_textbox.getText());
					apr = Double.parseDouble(APR_textbox.getText());
					//monthly_payment = Double.parseDouble(MonthlyPayment_textbox.getText());
					finance payment = new finance();
					payment.calculateMonthlyPayment(capital_amount, months, apr);
					MonthlyPayment_textbox.setText(payment.toString());
					
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
				}
			}
		});
		btnCalculate.setBounds(327, 210, 117, 29);
		frame.getContentPane().add(btnCalculate);
	}
}

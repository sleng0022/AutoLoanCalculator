import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class LoanCalculator {

	private JFrame frame;
	private JTextField CapitalAmount;
	private JTextField months;
	private JTextField APR;
	private JTextField MonthlyPayment;

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
		
		CapitalAmount = new JTextField();
		CapitalAmount.setBounds(244, 24, 130, 26);
		frame.getContentPane().add(CapitalAmount);
		CapitalAmount.setColumns(10);
		
		months = new JTextField();
		months.setBounds(244, 62, 130, 26);
		frame.getContentPane().add(months);
		months.setColumns(10);
		
		APR = new JTextField();
		APR.setBounds(244, 100, 130, 26);
		frame.getContentPane().add(APR);
		APR.setColumns(10);
		
		MonthlyPayment = new JTextField();
		MonthlyPayment.setBounds(244, 138, 130, 26);
		frame.getContentPane().add(MonthlyPayment);
		MonthlyPayment.setColumns(10);
		
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
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(327, 210, 117, 29);
		frame.getContentPane().add(btnCalculate);
	}
}

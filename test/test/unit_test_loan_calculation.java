package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.Loan;

class unit_test_loan_calculation 
{

	@Test
	final void test() 
	{
		/* Test monthly payment */
		double payment = 0;
		payment = Loan.calculateMonthlyPayment(10000, 60, 3);
		assertEquals(179.69, payment, 0.01); // TODO
		
		/* Test monthly payment with 0% APR*/
		double new_payment = 0;
		new_payment = Loan.calculateMonthlyPayment(10000, 60, 0);
		assertEquals(166.67, new_payment, 0.01); // TODO
		
		/* Test months */
		int months;
		months = Loan.calculateMonths(10000, 3, 179.69);
		assertEquals(60, months); // TODO
		
		/* Test capital amount*/
		double cap_amount = 0;
		cap_amount = Loan.calculateCapitalAmount(60, 3, 179.69);
		assertEquals(10000.00, cap_amount, 1); // TODO
		
		/* Calculate APR value*/
		double apr_rate = 0;
		apr_rate = Loan.calculateAPR(10000, 60, 179.69);
		assertEquals(3, apr_rate, 1); // TODO
		
		/* Test negative number with apr */
		double apr_rate_neg = 0;
		apr_rate_neg = Loan.calculateAPR(10000, 12,500);
		assertEquals(-1, apr_rate_neg, 1); // TODO
		
		/* Test 75% apr */
		double apr_rate_75 = 0;
		apr_rate_75 = Loan.calculateAPR(10000, 12, 1209.17);
		assertEquals(75, apr_rate_75, 1); // TODO
		
		/* Test 0% apr */
		double apr_rate_0 = 0;
		apr_rate_0 = Loan.calculateAPR(10000, 12, 833.33);
		assertEquals(0, apr_rate_0, 1); // TODO
		
		/* Test final payment */
		double final_pay = 0;
		final_pay = Loan.calculateFinalPayment(10000, 12, 0, 833.33);
		assertEquals(833.34, final_pay, 1); // TODO
		
		/* Test final pay with 12% APR*/
		double final_pay_12 = 0;
		final_pay_12 = Loan.calculateFinalPayment(10000, 12, 12, 888.49);
		assertEquals(888.49, final_pay_12, 1); // TODO
	}

}

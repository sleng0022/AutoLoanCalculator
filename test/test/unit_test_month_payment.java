package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.finance;

class unit_test_month_payment 
{

	@Test
	final void test() 
	{
		/* Test monthly payment */
		double payment = 0;
		finance month_payment = new finance("10000", "60", "3", "");
		payment = month_payment.getPayment();
		assertEquals(179.69, payment, 0.01); // TODO
		
		/* Test months */
		int months;
		finance mp = new finance("10000", "", "3", "179.69");
		months = mp.getMonths();
		assertEquals(60, months); // TODO
		
		/* Test capital amount*/
		double cap_amount = 0;
		finance principle_amount = new finance("", "60", "3", "179.69");
		cap_amount = principle_amount.getPayment();
		assertEquals(10000.00, cap_amount, 1); // TODO
		
		/* Calculate APR value*/
		double apr_rate = 0;
		finance rate = new finance("10000", "60", "", "179.69");
		apr_rate = rate.getPayment();
		assertEquals(3.0, apr_rate, 1); // TODO
	}

}

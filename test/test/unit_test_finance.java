package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import src.Finance;

class unit_test_finance {

	@Test
	final void test() 
	{
		/* Test monthly payment */
		double payment = 0;
		Finance month_payment = new Finance("10000", "60", "3", "");
		payment = month_payment.getPayment();
		assertEquals(179.69, payment, 0.01); // TODO
		
		/* Test months */
		int months;
		Finance mp = new Finance("10000", "", "3", "179.69");
		months = mp.getMonths();
		assertEquals(60, months); // TODO
		
		/* Test capital amount*/
		double cap_amount = 0;
		Finance principle_amount = new Finance("", "60", "3", "179.69");
		cap_amount = principle_amount.getPayment();
		assertEquals(10000.00, cap_amount, 1); // TODO
		
		/* Calculate APR value*/
		double apr_rate = 0;
		Finance rate = new Finance("10000", "60", "", "179.69");
		apr_rate = rate.getPayment();
		assertEquals(3.0, apr_rate, 1); // TODO
		
		
	}

}

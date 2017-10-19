package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.finance;

class unit_test_month_payment 
{

	@Test
	final void test() 
	{
		double payment = 0;
		finance month_payment = new finance("10000", "60", "3", "");
		payment = month_payment.getPayment();
		assertEquals(179.69, payment, 0.01); // TODO
		
		int months;
		finance mp = new finance("10000", "", "3", "179.69");
		months = mp.getMonths();
		assertEquals(60, months); // TODO
	}

}

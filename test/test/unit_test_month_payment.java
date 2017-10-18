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
		finance month_payment = new finance();
		month_payment.calculateMonthlyPayment(10000, 60, 3);
		payment = month_payment.getPayment();
		assertEquals(179.69, payment, 0.01); // TODO
	}

}

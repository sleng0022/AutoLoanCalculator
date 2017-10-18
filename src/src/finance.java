package src;

import java.text.DecimalFormat;

public class finance
{
	private double month_payment;
	DecimalFormat df = new DecimalFormat("#.##");
	
	
	public void calculateMonthlyPayment(double capital_amount, int num_months, double apr)
	{
		double yearly_interest_rate;
		
		yearly_interest_rate = (apr/100)/12;
		month_payment = (yearly_interest_rate * capital_amount)/(1-(Math.pow(1+yearly_interest_rate,-num_months)));
		df.format(month_payment);
	}
	
	public double getPayment()
	{
		return month_payment;
	}
	
	public String toString()
	{	
		return df.format(month_payment);
	}
}

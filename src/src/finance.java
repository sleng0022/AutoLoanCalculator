package src;

import java.text.DecimalFormat;

public class finance
{
	private double interest_rate;
	private double amount;
	private int months;
	private double payment;
	private double value;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	public finance(String capital_amount, String num_months, String apr, String monthly_payment)
	{
		
		if(capital_amount == "")
		{
			
		}else if(num_months == "")
		{
			
		}else if (apr == "")
		{
			payment = Double.parseDouble(monthly_payment);
		}else if (monthly_payment == "")
		{
			amount = Double.parseDouble(capital_amount);
			months = Integer.parseInt(num_months);
			interest_rate = Double.parseDouble(apr);
			this.calculateMonthlyPayment(amount, months, interest_rate);
		}
	}
	
	public void calculateMonthlyPayment(double capital_amount, int num_months, double apr)
	{
		double yearly_interest_rate;
		
		yearly_interest_rate = (apr/100)/12;
		value = (yearly_interest_rate * capital_amount)/(1-(Math.pow(1+yearly_interest_rate,-num_months)));
		df.format(value);
	}
	
	public double getPayment()
	{
		return value;
	}
	
	public String toString()
	{	
		return df.format(value);
	}
}

package src;

import java.text.DecimalFormat;

public class finance
{
	private double interest_rate;
	private double amount;
	private int months;
	private double payment;
	
	private double value;
	private int monthly;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	public finance(String capital_amount, String num_months, String apr, String monthly_payment)
	{
		
		if(capital_amount == "")
		{
			amount = Double.parseDouble(monthly_payment);
			months = Integer.parseInt(num_months);
			interest_rate = Double.parseDouble(apr);
			this.calculateCapitalAmount(months, interest_rate, amount);
		}else if(num_months == "")
		{
			payment = Double.parseDouble(monthly_payment);
			amount = Double.parseDouble(capital_amount);
			interest_rate = Double.parseDouble(apr);
			this.calculateMonths(amount,interest_rate,payment);
		}else if (apr == "")
		{
			
			
		}else if (monthly_payment == "")
		{
			amount = Double.parseDouble(capital_amount);
			months = Integer.parseInt(num_months);
			interest_rate = Double.parseDouble(apr);
			this.calculateMonthlyPayment(amount, months, interest_rate);
		}
	}
	
	public void calculateMonths(double capital_amount, double apr, double monthly_payment)
	{
		double yearly_interest_rate;
		yearly_interest_rate = (apr/100)/12;
		double payment_ratio;
		payment_ratio = (yearly_interest_rate * capital_amount)/(monthly_payment);
		
		monthly = (int)Math.round((-Math.log(1-payment_ratio))/Math.log(1+yearly_interest_rate));
	}
	
	public void calculateMonthlyPayment(double capital_amount, int num_months, double apr)
	{
		double yearly_interest_rate;
		
		yearly_interest_rate = (apr/100)/12;
		value = (yearly_interest_rate * capital_amount)/(1-(Math.pow(1+yearly_interest_rate,-num_months)));
		df.format(value);
	}
	
	public void calculateCapitalAmount(int months, double apr, double amount)
	{
		double yearly_interest_rate;
		yearly_interest_rate = (apr/100)/12;
		
		value = (amount/yearly_interest_rate)*(1-(Math.pow(1+yearly_interest_rate,-months)));
		df.format(value); 
	}
	
	public double getPayment()
	{
		return value;
	}
	
	public int getMonths()
	{
		return monthly;
	}
	
	public String toString()
	{	
		return df.format(value);
	}
	
	public String monthtoString()
	{
		return Integer.toUnsignedString(monthly);
	}
}

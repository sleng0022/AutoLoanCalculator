package src;

import java.text.DecimalFormat;

public class Finance 
{
	private double interest_rate;
	private double amount;
	private int months;
	private double payment;
	
	private double value;
	private int monthly;
	
	private double fix_rate = 2.6;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	public Finance(String capital_amount, String num_months, String apr, String monthly_payment)
	{	
		if(capital_amount == "")
		{
			amount = Double.parseDouble(monthly_payment);
			months = Integer.parseInt(num_months);
			interest_rate = Double.parseDouble(apr);
			value = Loan.calculateCapitalAmount(months, interest_rate, amount);
		}else if(num_months == "")
		{
			payment = Double.parseDouble(monthly_payment);
			amount = Double.parseDouble(capital_amount);
			interest_rate = Double.parseDouble(apr);
			monthly = Loan.calculateMonths(amount,interest_rate,payment);
		}else if (apr == "")
		{
			amount = Double.parseDouble(capital_amount);
			months = Integer.parseInt(num_months);
			payment = Double.parseDouble(monthly_payment);
			value = Loan.calculateAPR(amount, months, fix_rate ,payment);
			
		}else if (monthly_payment == "")
		{
			amount = Double.parseDouble(capital_amount);
			months = Integer.parseInt(num_months);
			interest_rate = Double.parseDouble(apr);
			value = Loan.calculateMonthlyPayment(amount, months, interest_rate);
		}
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
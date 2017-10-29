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
	private boolean invalid_num = false;
	
	DecimalFormat df = new DecimalFormat("#.##");
	
	public Finance(String capital_amount, String num_months, String apr, String monthly_payment)
	{	
		try
		{
			if(capital_amount == "")
			{
				amount = Double.parseDouble(monthly_payment);
				months = Integer.parseInt(num_months);
				interest_rate = Double.parseDouble(apr);
				value = Loan.calculateCapitalAmount(months, interest_rate, amount);
				if(value < 0)
				{
					invalid_num = true;
				}else
				{
					invalid_num = false;
				}
			}else if(num_months == "")
			{
				payment = Double.parseDouble(monthly_payment);
				amount = Double.parseDouble(capital_amount);
				interest_rate = Double.parseDouble(apr);
				monthly = Loan.calculateMonths(amount,interest_rate,payment);
				if(monthly < -0.001 || monthly > 72)
				{
					invalid_num = true;
				}else
				{
					invalid_num = false;
				}
			}else if (apr == "")
			{
				amount = Double.parseDouble(capital_amount);
				months = Integer.parseInt(num_months);
				payment = Double.parseDouble(monthly_payment);
				if(amount > payment)
				{
					value = Loan.calculateAPR(amount, months, payment);
					if(value < -1 || value > 75)
					{
						invalid_num = true;
					}else
					{
						invalid_num = false;
					}
				}else
				{
					invalid_num = true;
				}
			}else if (monthly_payment == "")
			{
				amount = Double.parseDouble(capital_amount);
				months = Integer.parseInt(num_months);
				interest_rate = Double.parseDouble(apr);
				value = Loan.calculateMonthlyPayment(amount, months, interest_rate);
				if(value < 0)
				{
					invalid_num = true;
				}else
				{
					invalid_num = false;
				}
			}else
			{
				amount = Double.parseDouble(capital_amount);
				months = Integer.parseInt(num_months);
				interest_rate = Double.parseDouble(apr);
				payment =  Double.parseDouble(monthly_payment);
				value = Loan.calculateFinalPayment(amount, months, interest_rate, payment);
			}
		}catch(Exception e)
		{
			invalid_num = true;
		}
	}
	public boolean CheckInValidNumber()
	{
		return invalid_num;
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
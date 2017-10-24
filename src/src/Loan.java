package src;

public final class Loan
{	
	private static double fix_rate = 5.0;
	private Loan() {}
	
	public static int calculateMonths(double capital_amount, double apr, double monthly_payment)
	{
		double yearly_interest_rate;
		yearly_interest_rate = (apr/100)/12;
		double payment_ratio;
		int monthly;
		
		if(apr == 0)
		{
			monthly = (int)Math.round(capital_amount/monthly_payment);
		}else
		{
			payment_ratio = (yearly_interest_rate * capital_amount)/(monthly_payment);
			
			monthly = (int)Math.round((-Math.log(1-payment_ratio))/Math.log(1+yearly_interest_rate));
		}
		
		
		
		return monthly;
	}
	
	public static double calculateMonthlyPayment(double capital_amount, int num_months, double apr)
	{
		double yearly_interest_rate;
		double value;
		
		if(apr == 0)
		{
			value = (capital_amount)/(num_months);
		}else
		{
			yearly_interest_rate = (apr/100)/12;
			value = (yearly_interest_rate * capital_amount)/(1-(Math.pow(1+yearly_interest_rate,-num_months)));
		}
		
		return value;
	}
	
	public static double calculateCapitalAmount(int months, double apr, double amount)
	{
		double yearly_interest_rate;
		double value;
		
		yearly_interest_rate = (apr/100)/12;
		
		if(apr == 0)
		{
			value = amount * months;
		}else
		{
			value = (amount/yearly_interest_rate)*(1-(Math.pow(1+yearly_interest_rate,-months)));
		}
		return value;
	}
	
	public static double calculateAPR(double capital_amount, int monthly, double apr, double monthly_payment)
	{
		double calculate_monthly_payment;
		double yearly_interest_rate;
		double final_APR;
		
		yearly_interest_rate = (apr/100)/12;
		
		calculate_monthly_payment = (yearly_interest_rate * capital_amount)/(1-(Math.pow(1+yearly_interest_rate,-monthly)));
		
		if(Math.abs((calculate_monthly_payment - monthly_payment)) <= 0.02)
		{
			final_APR = yearly_interest_rate * 12 * 100;
			
		}else if(calculate_monthly_payment < monthly_payment)
		{
			final_APR = calculateAPR(capital_amount, monthly, fix_rate+=.02, monthly_payment);
		}else
		{
			final_APR = calculateAPR(capital_amount, monthly, fix_rate-=.02, monthly_payment);
		}
		
		return final_APR;
	}
}

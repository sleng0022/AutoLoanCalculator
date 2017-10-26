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
		
		if(monthly_payment > 0)
		{
			payment_ratio = (yearly_interest_rate * capital_amount)/(monthly_payment);
		}else
		{
			payment_ratio = -1;
		}
		
		if(Math.log(1+yearly_interest_rate) > 0)
		{
			monthly = (int)Math.round((-Math.log(1-payment_ratio))/Math.log(1+yearly_interest_rate));
		}else
		{
			/* Error */
			monthly = -1;
		}
		return monthly;
	}
	
	public static double calculateMonthlyPayment(double capital_amount, int num_months, double apr)
	{
		double yearly_interest_rate;
		double value;
		
		if(num_months > 0)
		{
			if(apr == 0)
			{
				value = (capital_amount)/(num_months);
			}else
			{
				yearly_interest_rate = (apr/100)/12;
				if((1-(Math.pow(1+yearly_interest_rate,-num_months))) > 0)
				{
					value = (yearly_interest_rate * capital_amount)/(1-(Math.pow(1+yearly_interest_rate,-num_months)));
				}else
				{
					value = -1;
				}
			}
		}else
		{
			value = -1;
		}
		return value;
	}
	
	public static double calculateCapitalAmount(int months, double apr, double amount)
	{
		double yearly_interest_rate;
		double value;
		
		yearly_interest_rate = (apr/100)/12;
		
		value = (amount/yearly_interest_rate)*(1-(Math.pow(1+yearly_interest_rate,-months)));
		
		return value;
	}
	
	public static double calculateAPR(double capital_amount, int monthly, double apr, double monthly_payment)
	{
		double calculate_monthly_payment;
		double yearly_interest_rate;
		double final_APR;
		double delta;
		
		yearly_interest_rate = (apr/100)/12;
		
		if(((monthly_payment * monthly) - capital_amount) < -0.001)
		{
			final_APR = -1;
			return final_APR;
		}
		
		calculate_monthly_payment = calculateMonthlyPayment(capital_amount, monthly, apr);
		delta = calculate_monthly_payment - monthly_payment;
		
		if(Math.abs((calculate_monthly_payment - monthly_payment)) <= 0.02)
		{
			final_APR = yearly_interest_rate * 12 * 100;
			
		}else if(calculate_monthly_payment < monthly_payment)
		{
			final_APR = calculateAPR(capital_amount, monthly, fix_rate += 0.01, monthly_payment);
		}else if(calculate_monthly_payment > monthly_payment)
		{
			final_APR = calculateAPR(capital_amount, monthly, fix_rate -= 0.01, monthly_payment);
		}else
		{
			final_APR = -1;
		}
		
		return final_APR;
	}
}

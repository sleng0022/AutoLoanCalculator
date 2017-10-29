package src;

public final class Loan
{	
	private static double fix_rate = 5.0;
	private Loan() {}
	
	public static int calculateMonths(double capital_amount, double apr, double monthly_payment)
	{
		double yearly_interest_rate;
		double payment_ratio;
		int monthly;
		
		if(apr == 0)
		{
			monthly =  (int)Math.round(capital_amount / monthly_payment);
		}else
		{
			yearly_interest_rate = (apr/100)/12;
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
		
		if(apr == 0)
		{
			value = (amount * months);
			
		}else
		{
			yearly_interest_rate = (apr/100)/12;
			
			value = (amount/yearly_interest_rate)*(1-(Math.pow(1+yearly_interest_rate,-months)));
		}
		
		return value;
	}
	
	public static double calculateFinalPayment(double capital_amount, int months, double apr,double monthly_payment)
	{
		double value;
		
		if(apr == 0)
		{
			value = (capital_amount - (monthly_payment * months)) + (monthly_payment);
			
		}else
		{
			value = (calculateMonthlyPayment(capital_amount, months, apr) * months) - (monthly_payment * (months-1));
		}
		
		return value;
	}
	
	public static double calculateAPR(double capital_amount, int monthly, double monthly_payment)
	{
		double calculate_monthly_payment;
		double final_APR = fix_rate;
		double delta;
		double start=0;
		double end = 0;
		double temp_monthly_payment;
		
		if((Math.round(monthly_payment * monthly) - capital_amount) < -0.06)
		{
			final_APR = -1;
			return final_APR;
		}
		
		/* First time run through if calculation amount with fix rate < actual amount, we start from 0 to fix rate.
		 * Otherwise, we keep finding the end point when the calculation is > the actual amount. */
		calculate_monthly_payment = calculateMonthlyPayment(capital_amount, monthly, final_APR);
		if(calculate_monthly_payment >= monthly_payment)
		{
			start = 0;
			end = fix_rate;
		}else
		{
			temp_monthly_payment = monthly_payment;
			start = fix_rate;
			end = 1;
			while(temp_monthly_payment > calculate_monthly_payment)
			{
				end = fix_rate * end;
				calculate_monthly_payment = calculateMonthlyPayment(capital_amount, monthly, end);
			}
		}
		
		delta = Math.abs(calculate_monthly_payment - monthly_payment);
		
		while(Math.abs(delta) > .01)
		{
			double mid = (start + end)/2;
			calculate_monthly_payment = calculateMonthlyPayment(capital_amount, monthly, mid);
			delta = Math.abs(calculate_monthly_payment - monthly_payment);
			if(delta < 0.01)
			{
				final_APR = Math.round(mid);
				break;
			}
			
			if(monthly_payment < calculate_monthly_payment)
			{
				end = mid - 1;
			}else
			{
				start = mid + 1;
			}
		}
		
		return final_APR;
	}
}

package net.aesircraft.TrueEconomy.Data;

import java.text.DecimalFormat;


public class Form
{
	private static DecimalFormat df = new DecimalFormat("###0.00");
	private static DecimalFormat df2 = new DecimalFormat("#,##0");
	private static DecimalFormat df3 = new DecimalFormat("#,##0.00");

	public static String form(double amount)
	{
		if (amount==0){
			return "Nothing";
		}
		String d = df.format(amount);
		double decimalValue = Double.parseDouble(d);
		long a1 = (long)decimalValue;
		long a2 = (long)((decimalValue * 100) - (((long)decimalValue) * 100));
		String a3=df2.format(a1);
		String a4=df2.format(a2);
		String retrn1 = (a1 > 0 ? a3 + " " + (a1 > 1 ? "Eyrir" : "Eyrie") : "");
		String retrn2 = (a2 > 0 ? a4 + " " + (a2 > 1 ? "Cons" :"Con") : "");
		String retrn3="";
		if (!retrn1.equals("")&&!retrn2.equals(""))
			retrn3=" and ";
		String retrn = (retrn1.equals("") ? "" : retrn1) + retrn3+(retrn2.equals("") ? "" :retrn2);		
		return retrn;	
	}
		public static String form2(double amount)
	{
		String d = df3.format(amount);				
		return d;	
	}
		public static String form3(double amount)
	{
		String d = df.format(amount);				
		return d;	
	}
}

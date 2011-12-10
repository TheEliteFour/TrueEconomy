package net.aesircraft.TrueEconomy.Data;


public class Page
{
	public static int start(int page)
	{
		return (page * 5) - 5;
	}
}

package net.aesircraft.TrueEconomy.Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import net.aesircraft.TrueEconomy.TrueEconomy;
import org.bukkit.util.config.Configuration;


public class Iron
{
	public static double value(){
		double r=ratio();		
		if (r<1){
			r=2-r;
		}
		if (r>1){
			r=1-(r-1);
		}	
		return 2000*r;
	}
	public static void add(int amount){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("a");
		amount=amount+Integer.parseInt(a);
		config.setProperty("a", amount);
		config.save();
		compRatio(amount,config);
		double r=ratio();
		if (r>.05)
			unlock();
	}
	public static void sub(int amount){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("a");
		amount=Integer.parseInt(a)-amount;
		config.setProperty("a", amount);
		config.save();
		compRatio(amount,config);
		double r=ratio();
		if (r<=.01)
			lock();
	}
	public static int get(){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("a");
		int amount=Integer.parseInt(a);
		return amount;
	}
	public static double ratio(){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("r");
		double amount=Double.parseDouble(a);
		return amount;
	}
	public static boolean locked(){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);
		config.load();
		String a =config.getString("l");
		return Boolean.parseBoolean(a);
	}
	public static void lock(){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);	
		config.load();
		config.setProperty("l", "true");	
		config.save();
	}
	public static void unlock(){
		write();
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		Configuration config = new Configuration(db);	
		config.load();
		config.setProperty("l", "false");	
		config.save();
	}
	public static void compRatio(int amount, Configuration config){
		BigDecimal base=BigDecimal.valueOf(100000);
		BigDecimal div=BigDecimal.valueOf(amount);
		double ratio = div.divide(base,2,RoundingMode.HALF_UP).doubleValue();
		config.load();
		config.setProperty("r", ratio);
		config.save();
	}
	public static void write(){
		File db = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores" +File.separator +"iron"+".efd");
		File db1 = new File(TrueEconomy.getStatic().getDataFolder().toString());
		File db2 = new File(TrueEconomy.getStatic().getDataFolder().toString() + File.separator + "ores");
		if (!db1.exists())
			db1.mkdir();
		if (!db2.exists())
			db2.mkdir();
		if (db.exists())
			return;
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try
		{
			fileWriter = new FileWriter(db);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.append("a: 100000");
			bufferWriter.newLine();
			bufferWriter.append("r: 1.00");
			bufferWriter.newLine();
			bufferWriter.append("l: false");
			bufferWriter.flush();
			bufferWriter.close();
			fileWriter.close();
		}
		catch (IOException ex)
		{
		}				
	}
}
